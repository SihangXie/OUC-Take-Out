package edu.ouc.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: Sihang Xie
 * @Description: 订单明细实体类
 * @Date: 2022/10/27 10:14
 * @Version: 0.0.1
 * @Modified By:
 */
@Data
public class OrderDetail {

    private static final Long serialVersionUID = 8L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String name;

    private String image;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long orderId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long dishId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long setmealId;

    private String dishFlavor;

    private Integer number;

    private BigDecimal amount;
}
