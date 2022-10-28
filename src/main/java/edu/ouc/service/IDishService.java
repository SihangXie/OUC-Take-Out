package edu.ouc.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.ouc.dto.DishDto;
import edu.ouc.entity.Dish;

import java.util.List;

/**
 * @Author: Sihang Xie
 * @Description: 菜品的业务层接口
 * @Date: 2022/10/3 10:22
 * @Version: 0.0.1
 * @Modified By:
 */
public interface IDishService extends IService<Dish> {

    // 新增菜品，同时插入菜品对应的口味数据
    Boolean saveWithFlavor(DishDto dishDto);

    // 分页查询+按菜品名称查询
    Page<DishDto> getPage(Long page, Long pageSize, String name);

    // 根据菜品ID查询菜品信息和对应的口味信息
    DishDto getByIdWithFlavor(Long id);

    // 修改菜品，同时修改菜品对应的口味数据
    Boolean updateWithFlavor(DishDto dishDto);

    // (批量)停售/启售菜品
    Boolean updateStatus(Integer status, List<Long> ids);

    // 删除(批量删除)菜品
    Boolean removeWithFlavor(List<Long> ids);

    // 根据指定过滤条件查询菜品
    List<DishDto> listWithFlavor(Dish dish);
}