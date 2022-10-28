package edu.ouc.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Sihang Xie
 * @Description: 用户实体类User
 * @Date: 2022/10/22 10:10
 * @Version: 0.0.1
 * @Modified By:
 */
@Data
public class User implements Serializable {
    private static final Long serialVersionUID = 2L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String name;
    private String email;
    private String sex;
    private String idNumber;
    private String avatar;
    private Integer status;
}
