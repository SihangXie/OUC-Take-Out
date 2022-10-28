package edu.ouc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.ouc.entity.SetmealDish;

import java.util.List;
import java.util.Set;

/**
 * @Author: Sihang Xie
 * @Description: 套餐菜品关联表的业务层接口
 * @Date: 2022/10/12 11:54
 * @Version: 0.0.1
 * @Modified By:
 */
public interface ISetmealDishService extends IService<SetmealDish> {
    // 根据套餐IDs从setmeal_dish表查询对应的菜品IDs
    Set<Long> getDishIdsBySetmealId(List<Long> setmealIds);
}
