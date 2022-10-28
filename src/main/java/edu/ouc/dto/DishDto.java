package edu.ouc.dto;

import edu.ouc.entity.Dish;
import edu.ouc.entity.DishFlavor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Sihang Xie
 * @Description: 新增菜品的数据传输对象
 * @Date: 2022/10/3 18:29
 * @Version: 0.0.1
 * @Modified By:
 */
@Data
public class DishDto extends Dish {// 继承Dish，就拥有了Dish的全部属性和方法

    // 封装菜品口味
    private List<DishFlavor> flavors = new ArrayList<>();

    // 菜品分类名称
    private String categoryName;

    // 副本
    private Integer copies;
}
