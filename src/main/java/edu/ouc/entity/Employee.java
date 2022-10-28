package edu.ouc.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: Sihang Xie
 * @Description: 数据表employee的实体类
 * @Date: 2022/9/29 12:38
 * @Version: 0.0.1
 * @Modified By:
 */
@Data
public class Employee {
    private static final Long serialVersionUID = 1L;// 序列化ID
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String name;
    private String username;
    private String password;
    private String phone;
    private String sex;
    private String idNumber;
    private Integer status;

    @TableField(fill = FieldFill.INSERT)    // 插入时填充字段
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE) // 插入和更新时填充字段
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)    // 插入时填充字段
    private Long createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)  // 插入和更新时填充字段
    private Long updateUser;
}
