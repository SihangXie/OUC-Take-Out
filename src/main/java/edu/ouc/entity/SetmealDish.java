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
 * @Description: 套餐菜品关联表的实体类
 * @Date: 2022/10/12 11:36
 * @Version: 0.0.1
 * @Modified By:
 */
@Data
public class SetmealDish {
    private static final Long serialVersionUID = 38L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long setmealId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long dishId;

    // 菜品名称（冗余字段）
    private String name;
    // 菜品原价
    private BigDecimal price;
    // 菜品份数
    private Integer copies;
    // 排序
    private Integer sort;
    private Integer isDeleted;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;
}
