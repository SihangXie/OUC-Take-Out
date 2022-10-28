package edu.ouc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.ouc.common.BaseContext;
import edu.ouc.common.CustomException;
import edu.ouc.dto.OrderDto;
import edu.ouc.entity.*;
import edu.ouc.mapper.OrderMapper;
import edu.ouc.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @Author: Sihang Xie
 * @Description: 订单表orders业务层接口实现类
 * @Date: 2022/10/27 10:32
 * @Version: 0.0.1
 * @Modified By:
 */
@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements IOrderService {

    @Autowired
    private OrderDetailServiceImpl orderDetailService;
    @Autowired
    private ShoppingCartServiceImpl shoppingCartService;
    @Autowired
    private AddressBookServiceImpl addressBookService;
    @Autowired
    private UserServiceImpl userService;

    // 提交(添加)订单
    @Override
    @Transactional  // 涉及到两张表的插入操作需要打开事务控制
    public Boolean submit(Orders orders) {

        // 1.获取当前登录用户ID
        Long userId = BaseContext.getCurrentUserId();


        // 2.调用购物车ShoppingCart业务层的获取购物车信息
        LambdaQueryWrapper<ShoppingCart> shoppingCartLqw = new LambdaQueryWrapper<>();
        shoppingCartLqw.eq(userId != null, ShoppingCart::getUserId, userId);
        List<ShoppingCart> shoppingCarts = shoppingCartService.list(shoppingCartLqw);
        // 如果购物车为空，则抛出业务异常
        if (shoppingCarts.isEmpty()) {
            throw new CustomException("购物车为空，无法结算");
        }


        // 3.调用地址簿AddressBook业务层获取当前派送的地址信息
        AddressBook addressBook = addressBookService.getById(orders.getAddressBookId());
        // 如果地址信息为空，则抛出业务异常
        if (addressBook == null) {
            throw new CustomException("地址信息为空，无法下单");
        }


        // 4.调用用户业务层user表获取用户信息
        User user = userService.getById(userId);


        // 5.为订单对象的属性一一赋值
        long orderId = IdWorker.getId();
        // 使用AtomicInteger计算商品总金额，保证高并发下的线程安全
        AtomicInteger amount = new AtomicInteger(0);

        // 6.新增订单明细，用购物车的stream流复制
        List<OrderDetail> orderDetails = shoppingCarts.stream().map(shoppingCart -> {
            OrderDetail orderDetail = new OrderDetail();
            // 复制属性值
            orderDetail.setOrderId(orderId);
            orderDetail.setNumber(shoppingCart.getNumber());
            orderDetail.setDishFlavor(shoppingCart.getDishFlavor());
            orderDetail.setDishId(shoppingCart.getDishId());
            orderDetail.setSetmealId(shoppingCart.getSetmealId());
            orderDetail.setName(shoppingCart.getName());
            orderDetail.setImage(shoppingCart.getImage());
            orderDetail.setAmount(shoppingCart.getAmount());
            // 计算订单总金额
            amount.addAndGet(shoppingCart.getAmount().multiply(new BigDecimal(shoppingCart.getNumber())).intValue());
            return orderDetail;
        }).collect(Collectors.toList());

        // 生成并设置订单号
        orders.setNumber(String.valueOf(orderId));
        // 设置下单用户ID
        orders.setUserId(userId);
        // 设置订单状态为待派送
        orders.setStatus(2);
        // 设置商品总金额
        orders.setAmount(new BigDecimal(amount.get()));
        // 设置订单客户手机号
        orders.setPhone(addressBook.getPhone());
        // 设置收货人姓名
        orders.setConsignee(addressBook.getConsignee());
        // 设置用户名
        orders.setUserName(user.getName());
        // 设置地址详情，包含省市区
        orders.setAddress((addressBook.getProvinceName() == null ? "" : addressBook.getProvinceName())
                + (addressBook.getCityName() == null ? "" : addressBook.getCityName())
                + (addressBook.getDistrictName() == null ? "" : addressBook.getDistrictName())
                + addressBook.getDetail());

        // 7.调用订单数据层新增订单
        this.save(orders);


        // 8.批量新增订单明细
        orderDetailService.saveBatch(orderDetails);

        // 9.下单完成后清空购物车数据
        return shoppingCartService.remove(shoppingCartLqw);
    }

    // 获取订单分页展示
    @Override
    public Page<OrderDto> getPage(Long page, Long pageSize) {
        // 1.创建分页封装器
        Page<Orders> ordersPage = new Page<>(page, pageSize);
        // 2.创建OrderDto的分页封装器
        Page<OrderDto> dtoPage = new Page<>();

        // 3.创建Orders的查询条件封装器
        LambdaQueryWrapper<Orders> lqw = new LambdaQueryWrapper<>();
        // 3.1 添加查询条件：按下单时间降序排列
        lqw.orderByDesc(Orders::getOrderTime);
        // 3.2 条件查询条件：按当前用户ID查询
        Long userId = BaseContext.getCurrentUserId();
        lqw.eq(userId != null, Orders::getUserId, userId);
        // 4.Orders分页查询
        this.page(ordersPage, lqw);

        // 5.除了Record都复制
        BeanUtils.copyProperties(ordersPage, dtoPage, "records");

        // 6.获取当前用户所有的order对象
        List<Orders> orders = this.list(lqw);
        // 7.通过stream流逐一包装成OrderDto对象
        List<OrderDto> orderDtos = orders.stream().map(order -> {
            // 7.1 创建OrderDto对象
            OrderDto orderDto = new OrderDto();
            // 7.2 拷贝属性
            BeanUtils.copyProperties(order, orderDto);
            // 7.3 调用OrderDetail业务层获取订单明细集合
            LambdaQueryWrapper<OrderDetail> orderDetailLqw = new LambdaQueryWrapper<>();
            orderDetailLqw.eq(OrderDetail::getOrderId, order.getNumber());
            List<OrderDetail> orderDetails = orderDetailService.list(orderDetailLqw);
            // 7.4 设置orderDto的订单明细属性
            orderDto.setOrderDetails(orderDetails);
            // 7.5 返回orderDto
            return orderDto;
        }).collect(Collectors.toList());

        // 8.设置dtoPage的records属性
        dtoPage.setRecords(orderDtos);
        return dtoPage;
    }

    // 后台管理端获取订单分页展示
    @Override
    public Page<OrderDto> getAllPage(Long page, Long pageSize, String number, String beginTime, String endTime) {
        // 1.创建分页封装器
        Page<Orders> ordersPage = new Page<>(page, pageSize);
        // 2.创建OrderDto的分页封装器
        Page<OrderDto> dtoPage = new Page<>();

        // 3.创建Orders的查询条件封装器
        LambdaQueryWrapper<Orders> lqw = new LambdaQueryWrapper<>();
        // 3.1 添加查询条件：按下单时间降序排列
        lqw.orderByDesc(Orders::getOrderTime);
        // 3.2 添加查询条件：按订单号查询
        lqw.like(number != null, Orders::getNumber, number);
        // 3.3 添加查询条件：  动态SQL-字符串使用StringUtils.isNotEmpty这个方法来判断
        lqw.gt(StringUtils.isNotEmpty(beginTime), Orders::getOrderTime, beginTime);
        lqw.lt(StringUtils.isNotEmpty(endTime), Orders::getOrderTime, endTime);
        // 4.Orders分页查询
        this.page(ordersPage, lqw);

        // 5.除了Record都复制
        BeanUtils.copyProperties(ordersPage, dtoPage, "records");

        // 6.获取当前用户所有的order对象
        List<Orders> orders = this.list(lqw);
        // 7.通过stream流逐一包装成OrderDto对象
        List<OrderDto> orderDtos = orders.stream().map(order -> {
            // 7.1 创建OrderDto对象
            OrderDto orderDto = new OrderDto();
            // 7.2 拷贝属性
            BeanUtils.copyProperties(order, orderDto);
            // 7.3 调用OrderDetail业务层获取订单明细集合
            LambdaQueryWrapper<OrderDetail> orderDetailLqw = new LambdaQueryWrapper<>();
            orderDetailLqw.eq(OrderDetail::getOrderId, order.getNumber());
            List<OrderDetail> orderDetails = orderDetailService.list(orderDetailLqw);
            // 7.4 设置orderDto的订单明细属性
            orderDto.setOrderDetails(orderDetails);
            // 7.5 返回orderDto
            return orderDto;
        }).collect(Collectors.toList());

        // 8.设置dtoPage的records属性
        dtoPage.setRecords(orderDtos);
        return dtoPage;
    }

    // 修改订单状态
    @Override
    public Boolean update(Orders order) {
        return this.updateById(order);
    }
}
