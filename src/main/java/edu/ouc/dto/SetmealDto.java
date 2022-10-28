package edu.ouc.dto;

import edu.ouc.entity.Setmeal;
import edu.ouc.entity.SetmealDish;
import lombok.Data;

import java.util.List;

/**
 * @Author: Sihang Xie
 * @Description: 套餐表和套餐菜品关联表的数据传输对象
 * @Date: 2022/10/12 11:34
 * @Version: 0.0.1
 * @Modified By:
 */
@Data
public class SetmealDto extends Setmeal {
    private List<SetmealDish> setmealDishes;
    private String categoryName;
}
