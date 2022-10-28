package edu.ouc.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author: Sihang Xie
 * @Description: 订单的实体类
 * @Date: 2022/10/26 21:42
 * @Version: 0.0.1
 * @Modified By:
 */
@Data
public class Orders {

    private static final Long serialVersionUID = 5L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    // 订单号
    private String number;

    // 订单状态
    private Integer status;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long addressBookId;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime orderTime;

    // 支付时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime checkoutTime;

    private Integer payMethod;

    private BigDecimal amount;

    private String remark;

    private String phone;

    private String address;

    private String userName;

    // 收获联系人
    private String consignee;
}
