package edu.ouc.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.ouc.dto.OrderDto;
import edu.ouc.entity.Orders;

/**
 * @Author: Sihang Xie
 * @Description: 订单服务层接口
 * @Date: 2022/10/27 10:29
 * @Version: 0.0.1
 * @Modified By:
 */
public interface IOrderService extends IService<Orders> {

    // 提交(添加)订单
    Boolean submit(Orders orders);

    // 获取订单分页展示
    Page<OrderDto> getPage(Long page, Long pageSize);
}
