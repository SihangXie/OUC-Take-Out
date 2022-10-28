package edu.ouc.dto;

import edu.ouc.entity.OrderDetail;
import edu.ouc.entity.Orders;
import lombok.Data;

import java.util.List;

/**
 * @Author: Sihang Xie
 * @Description: 订单的DTO类
 * @Date: 2022/10/27 16:34
 * @Version: 0.0.1
 * @Modified By:
 */
@Data
public class OrderDto extends Orders {
    private List<OrderDetail> orderDetails;
}
