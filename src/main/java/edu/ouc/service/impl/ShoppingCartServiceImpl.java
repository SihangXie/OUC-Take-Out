package edu.ouc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.ouc.common.BaseContext;
import edu.ouc.entity.ShoppingCart;
import edu.ouc.mapper.ShoppingCartMapper;
import edu.ouc.service.IShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Sihang Xie
 * @Description: 购物车接口实现类
 * @Date: 2022/10/24 15:07
 * @Version: 0.0.1
 * @Modified By:
 */
@Slf4j
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements IShoppingCartService {

    // 添加菜品到购物车
    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        // 获取当前登录用户的 ID
        Long userId = BaseContext.getCurrentUserId();
        // 给传入的购物车菜品设置用户ID
        shoppingCart.setUserId(userId);

        // 查询一下是否是首次添加
        LambdaQueryWrapper<ShoppingCart> lqw = new LambdaQueryWrapper<>();
        // 添加查询条件：根据用户ID查询
        lqw.eq(ShoppingCart::getUserId, userId);
        // 判断用户添加的是菜品还是套餐
        if (shoppingCart.getDishId() != null) {
            // 用户添加的是菜品，添加菜品ID作为查询条件
            lqw.eq(ShoppingCart::getDishId, shoppingCart.getDishId());
        } else {
            // 否则，用户添加的是套餐，添加套餐ID作为查询条件
            lqw.eq(ShoppingCart::getSetmealId, shoppingCart.getSetmealId());
        }
        // 调用数据层查询购物车
        ShoppingCart shoppingCartSel = this.getOne(lqw);

        // 如果查询结果为空，则是第一次添加，把number字段设为1，插入
        if (shoppingCartSel == null) {
            shoppingCart.setNumber(1);
            // 调用数据层插入购物车数据
            this.save(shoppingCart);
            // 返回已经写入用户ID的对象
            return shoppingCart;
        }

        // 否则就不是第一次加入购物车，就直接在number字段上加1，更新
        shoppingCartSel.setNumber(shoppingCartSel.getNumber() + 1);
        // 更新
        this.updateById(shoppingCartSel);
        return shoppingCartSel;
    }

    // 查询当前用户的购物车中所有信息
    @Override
    public List<ShoppingCart> getUserList() {
        // 获取当前用户ID
        Long userId = BaseContext.getCurrentUserId();
        // 创建查询条件封装器
        LambdaQueryWrapper<ShoppingCart> lqw = new LambdaQueryWrapper<>();
        lqw.eq(userId != null, ShoppingCart::getUserId, userId);
        // 按时间升序排
        lqw.orderByAsc(ShoppingCart::getCreateTime);
        // 查询当前用户的所有购物车信息
        return this.list(lqw);
    }

    // 清空购物车
    @Override
    public Boolean clean() {
        // 获取当前用户ID
        Long userId = BaseContext.getCurrentUserId();
        // 创建查询条件封装器
        LambdaQueryWrapper<ShoppingCart> lqw = new LambdaQueryWrapper<>();
        lqw.eq(userId != null, ShoppingCart::getUserId, userId);
        // 按用户ID删除所有记录
        return this.remove(lqw);
    }

    // 购物车商品减一
    @Override
    public Boolean sub(ShoppingCart shoppingCart) {
        // 1.获取当前用户ID
        Long userId = BaseContext.getCurrentUserId();
        // 2.创建查询条件封装器
        LambdaQueryWrapper<ShoppingCart> lqw = new LambdaQueryWrapper<>();
        // 2.1 添加查询添加：按用户ID查询
        lqw.eq(userId != null, ShoppingCart::getUserId, userId);

        // 3.判断传来的是菜品还是套餐
        if (shoppingCart.getDishId() != null) {
            // 3.1 删除的是菜品
            lqw.eq(ShoppingCart::getDishId, shoppingCart.getDishId());
        } else {
            // 3.2 删除的是套餐
            lqw.eq(ShoppingCart::getSetmealId, shoppingCart.getSetmealId());
        }

        // 4.查询当前购物车里该菜品/套餐的数量
        ShoppingCart shoppingCartSel = this.getOne(lqw);
        if (1 < shoppingCartSel.getNumber()) {
            // 4.1 数量大于一，直接减一
            shoppingCartSel.setNumber(shoppingCartSel.getNumber() - 1);
            // 4.2更新到数据库
            return this.updateById(shoppingCartSel);
        }

        // 5.其他情况直接从购物车删除此商品
        return this.remove(lqw);
    }
}
