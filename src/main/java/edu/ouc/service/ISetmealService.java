package edu.ouc.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.ouc.dto.SetmealDto;
import edu.ouc.entity.Setmeal;

import java.util.List;
import java.util.Set;

/**
 * @Author: Sihang Xie
 * @Description: 套餐的业务层接口
 * @Date: 2022/10/3 10:28
 * @Version: 0.0.1
 * @Modified By:
 */
public interface ISetmealService extends IService<Setmeal> {

    // 新增套餐，同时插入套餐对应菜品
    Boolean saveWithSetmealDishes(SetmealDto setmealDto);

    // 带搜索功能的分页查询
    Page<SetmealDto> getPage(Long currentPage, Long pageSize, String name);

    // 根据套餐ID查询单个带套餐菜品的套餐信息
    SetmealDto getByIdWithDishes(Long id);

    // 修改套餐信息和套餐关联菜品
    Boolean updateWithDishes(SetmealDto setmealDto);

    // (批量)套餐启售/停售
    Boolean updateStatus(Integer status, List<Long> ids);

    // 根据套餐ID删除(批量删除)套餐
    Boolean removeWithDish(List<Long> ids);

    // 根据菜品IDs集合，查询对应套餐Ids集合
    Set<Long> getIdsByDishId(List<Long> dishIds);

    // 根据条件查询套餐集合
    List<Setmeal> list(Setmeal setmeal);
}
