package edu.ouc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.ouc.entity.SetmealDish;
import edu.ouc.mapper.SetmealDishMapper;
import edu.ouc.service.ISetmealDishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: Sihang Xie
 * @Description: 套餐菜品关联表的业务层接口的实现类
 * @Date: 2022/10/12 11:57
 * @Version: 0.0.1
 * @Modified By:
 */
@Slf4j
@Service
public class SetmealDishServiceImpl extends ServiceImpl<SetmealDishMapper, SetmealDish> implements ISetmealDishService {

    // 根据套餐IDs从setmeal_dish表查询对应的菜品IDs
    @Override
    public Set<Long> getDishIdsBySetmealId(List<Long> setmealIds) {
        // 1.创建过滤条件封装器
        LambdaQueryWrapper<SetmealDish> lqw = new LambdaQueryWrapper<>();
        // 2.添加过滤条件：IN()在传入的套餐IDs之内
        lqw.in(SetmealDish::getSetmealId, setmealIds);
        // 3.根据过滤条件查询SetmealDish实体类的集合
        List<SetmealDish> setmealDishes = this.list(lqw);
        // 4.通过集合的stream流map映射返回一个菜品IDs的Set集合：不可重复
        return setmealDishes.stream().map(SetmealDish::getDishId).collect(Collectors.toSet());
    }
}
