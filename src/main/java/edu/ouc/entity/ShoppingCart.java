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
 * @Description: 购物车实体类
 * @Date: 2022/10/24 14:58
 * @Version: 0.0.1
 * @Modified By:
 */
@Data
public class ShoppingCart {
    private static final Long serialVersionUID = 4L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String name;

    private String image;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long dishId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long setmealId;

    private String dishFlavor;

    private Integer number;

    private BigDecimal amount;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
