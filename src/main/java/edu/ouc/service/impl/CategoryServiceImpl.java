package edu.ouc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.ouc.common.CustomException;
import edu.ouc.entity.Category;
import edu.ouc.entity.Dish;
import edu.ouc.entity.Setmeal;
import edu.ouc.mapper.CategoryMapper;
import edu.ouc.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @Author: Sihang Xie
 * @Description: Category的业务层接口实现类
 * @Date: 2022/10/2 14:42
 * @Version: 0.0.1
 * @Modified By:
 */
@Service
@Slf4j
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    // 注入菜品业务层
    @Autowired
    @Lazy
    private DishServiceImpl dishService;

    // 注入套餐业务层
    @Autowired
    private SetmealServiceImpl setmealService;

    // 分页查询
    @Override
    public Page<Category> getPage(Long currentPage, Long pageSize) {

        // 1.创建条件查询构造器，用于按展示顺序排序
        LambdaQueryWrapper<Category> lqw = new LambdaQueryWrapper<>();

        // 2.根据日期时间排序
        lqw.orderByAsc(Category::getSort);

        // 3.创建分页构造器
        Page<Category> page = new Page<>(currentPage, pageSize);

        // 4.调用数据层的分页查询
        return page(page, lqw);
    }

    // 完善的删除分类操作
    // 根据id删除分类，删除之前判断分类下是否有关联的菜品或套餐
    @Override
    public Boolean remove(Long id) {

        // 1.查询当前分类是否关联了菜品，如果已经关联，则抛出一个业务异常

        // 1.1 创建条件构造器
        LambdaQueryWrapper<Dish> dishLqw = new LambdaQueryWrapper<>();

        // 1.2 添加查询条件，按照分类ID进行查询
        dishLqw.eq(Dish::getCategoryId, id);

        // 1.3 返回查询结果的总数
        int dishCount = dishService.count(dishLqw);

        // 1.4 如果查询结果大于0，说明该分类已经关联菜品，抛出一个自定义的业务异常
        if (dishCount > 0) {
            throw new CustomException("该分类有关联菜品，无法删除");
        }


        // 2.查询当前分类是否关联了套餐，如果已经关联，则抛出一个业务异常

        // 2.1 创建条件构造器
        LambdaQueryWrapper<Setmeal> setmealLqw = new LambdaQueryWrapper<>();

        // 2.2 添加查询条件，按照分类ID进行查询
        setmealLqw.eq(Setmeal::getCategoryId, id);

        // 2.3 返回查询结果的总数
        Integer setmealCount = setmealService.count(setmealLqw);

        // 2.4 如果查询结果大于0，说明该分类已经关联套餐，抛出一个自定义的业务异常
        if (setmealCount > 0) {
            throw new CustomException("该分类有关联套餐，无法删除");
        }

        // 3.如果都没有关联，就可以正常删除
        return removeById(id);
    }
}
