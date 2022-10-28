package edu.ouc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.ouc.common.CustomException;
import edu.ouc.dto.SetmealDto;
import edu.ouc.entity.Dish;
import edu.ouc.entity.Setmeal;
import edu.ouc.entity.SetmealDish;
import edu.ouc.mapper.SetmealMapper;
import edu.ouc.service.ICategoryService;
import edu.ouc.service.IDishService;
import edu.ouc.service.ISetmealDishService;
import edu.ouc.service.ISetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: Sihang Xie
 * @Description: 套餐的业务层接口的实现类
 * @Date: 2022/10/3 10:29
 * @Version: 0.0.1
 * @Modified By:
 */
@Slf4j
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements ISetmealService {

    @Autowired
    private ISetmealDishService setmealDishService;

    @Lazy
    @Autowired
    private ICategoryService categoryService;

    @Lazy
    @Autowired
    private IDishService dishService;


    // 新增套餐，同时插入套餐对应菜品
    @Override
    @Transactional  // 多表操作切记添加事务注解
    public Boolean saveWithSetmealDishes(SetmealDto setmealDto) {
        // 1.保存套餐的基本信息
        this.save(setmealDto);

        // 2.获取套餐菜品集合
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();

        // 3.以stream流的方式设置每个套餐菜品的setmeal_id
        setmealDishes = setmealDishes.stream().peek(setmealDish -> setmealDish.setSetmealId(setmealDto.getId())).collect(Collectors.toList());

        // 4.批量保存套餐菜品
        return setmealDishService.saveBatch(setmealDishes);
    }

    // 带搜索功能的分页查询
    @Override
    public Page<SetmealDto> getPage(Long currentPage, Long pageSize, String name) {
        // 1.创建Setmeal的分页构造器
        Page<SetmealDto> dtoPage = new Page<>(currentPage, pageSize);
        // 2.创建SetmealDto的分页构造器
        Page<Setmeal> page = new Page<>(currentPage, pageSize);

        // 3.创建Setmeal的条件查询过滤器
        LambdaQueryWrapper<Setmeal> lqw = new LambdaQueryWrapper<>();
        // 4.添加过滤条件：根据套餐名称模糊匹配
        lqw.like(name != null, Setmeal::getName, name);
        // 5.添加排序条件：根据修改时间降序排列
        lqw.orderByDesc(Setmeal::getUpdateTime);
        // 6.调用数据层的分页查询+条件查询
        this.page(page, lqw);

        // 7.复制分页构造器：除了记录其他都复制
        BeanUtils.copyProperties(page, dtoPage, "records");

        // 8.获取Setmeal Page的记录集合
        List<Setmeal> setmeals = page.getRecords();

        // 9.通过集合stream流逐一给SetmealDtos赋上类别名
        List<SetmealDto> setmealDtos = setmeals.stream().map(setmeal -> {
            // 9.1 创建SetmealDto对象
            SetmealDto setmealDto = new SetmealDto();
            // 9.2 把setmeal中的信息复制到setmealDto中
            BeanUtils.copyProperties(setmeal, setmealDto);
            // 9.3 获取类别名称
            String categoryName = categoryService.getById(setmeal.getCategoryId()).getName();
            // 9.4 给setmealDto设置类别名称
            setmealDto.setCategoryName(categoryName);
            // 9.5 返回setmealDto对象
            return setmealDto;
        }).collect(Collectors.toList());

        // 10.给SetmealDto Page设置Records
        dtoPage.setRecords(setmealDtos);

        return dtoPage;
    }

    // 根据套餐ID查询单个带套餐菜品的套餐信息
    @Override
    public SetmealDto getByIdWithDishes(Long id) {
        // 1.获取套餐的基本信息
        Setmeal setmeal = this.getById(id);

        // 2.获取套餐所有关联菜品构成的列表
        LambdaQueryWrapper<SetmealDish> lqw = new LambdaQueryWrapper<>();
        lqw.eq(SetmealDish::getSetmealId, id);
        lqw.orderByDesc(SetmealDish::getUpdateTime);
        List<SetmealDish> setmealDishes = setmealDishService.list(lqw);

        // 3.把套餐基本信息和套餐菜品封装到SetmealDto对象中
        SetmealDto setmealDto = new SetmealDto();
        BeanUtils.copyProperties(setmeal, setmealDto);
        setmealDto.setSetmealDishes(setmealDishes);

        return setmealDto;
    }

    // 修改套餐信息和套餐关联菜品
    @Override
    @Transactional  // 修改两张表不能忘记事务注解
    public Boolean updateWithDishes(SetmealDto setmealDto) {
        // 1.修改setmeal基本信息
        this.updateById(setmealDto);

        // 2.把setmeallDish信息抽取出来
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();

        // 3.通过集合的stream流给套餐关联的菜品逐一赋上套餐ID
        setmealDishes = setmealDishes.stream().peek(setmealDish -> setmealDish.setSetmealId(setmealDto.getId())).collect(Collectors.toList());

        // 4.把setmeal_dish表中原有的套餐菜品删除掉
        LambdaQueryWrapper<SetmealDish> lqw = new LambdaQueryWrapper<>();
        lqw.eq(SetmealDish::getSetmealId, setmealDto.getId());
        setmealDishService.remove(lqw);

        // 5.把新的套餐菜品插入到setmeal_dish表中
        return setmealDishService.saveBatch(setmealDishes);
    }

    // (批量)套餐启售/停售
    @Override
    public Boolean updateStatus(Integer status, List<Long> ids) {

        // 启售套餐前，应检查套餐关联菜品是否存在且在售
        if (status == 1) {
            // 根据套餐IDs从setmeal_dish表查询对应的菜品IDs
            Set<Long> dishIds = setmealDishService.getDishIdsBySetmealId(ids);

            // 创建dish的条件包装器
            LambdaQueryWrapper<Dish> lqw1 = new LambdaQueryWrapper<>();
            // 添加过滤条件：查询停售的dish
            lqw1.eq(Dish::getStatus, 0);
            // 根据条件封装器lqw查询满足条件的dish实体类对象构成的集合
            List<Dish> dishes = dishService.list(lqw1);
            // 如果dish集合不为空，说明套餐菜品中有停售菜品，套餐不能启售，抛出业务异常
            if (!dishes.isEmpty()) {
                // 获取已停售菜品的名称
                List<String> dishNames = dishes.stream().map(Dish::getName).collect(Collectors.toList());
                // 向前端页面展示已停售菜品的名称，方便用户启售对应菜品
                throw new CustomException("套餐所关联菜品" + dishNames + "已停售，启售失败");
            }

            // 检查菜品有没有被删除
            LambdaQueryWrapper<Dish> lqw2 = new LambdaQueryWrapper<>();
            lqw2.in(Dish::getId, dishIds);
            List<Dish> dishes1 = dishService.list(lqw2);
            // 如果套餐菜品个数与dish数据表中根据菜品ID查询回来的个数不相等，说明dish数据表中有菜品被删除了
            if (dishes1.size() != dishIds.size()) {
                throw new CustomException("套餐中有菜品被删除，启售失败");
            }
        }

        // 1.根据套餐ID集合批量查询套餐
        List<Setmeal> setmeals = this.listByIds(ids);

        // 2.使用stream流逐一修改其售卖状态
        setmeals = setmeals.stream().peek(setmeal -> setmeal.setStatus(status)).collect(Collectors.toList());

        // 3.批量修改
        return this.updateBatchById(setmeals);
    }

    // 根据套餐ID删除(批量删除)套餐
    @Override
    @Transactional // 删除两张表必须添加事务注解
    public Boolean removeWithDish(List<Long> ids) {

        // 1.首先判断当前套餐的售卖状态
        // 1.1 获取待删除套餐构成的集合
        // List<Setmeal> setmeals = this.listByIds(ids);
        // 1.2 使用stream流逐一检查集合内的套餐元素售卖状态
        // boolean isRemovable = setmeals.stream().anyMatch(setmeal -> 1 == setmeal.getStatus());
        // 1.3 如果存在在售的套餐，则抛出业务异常，无法删除
        // if (isRemovable) {
        // throw new CustomException("所删除套餐中包含在售套餐，删除失败，请停售后再删除");
        // }

        // 1.1 创建过滤条件封装器
        LambdaQueryWrapper<Setmeal> wq = new LambdaQueryWrapper<>();
        // 1.2 添加包含条件IN()，待删除套餐的ID
        wq.in(Setmeal::getId, ids);
        // 1.3 添加过滤条件：在售的套餐
        wq.eq(Setmeal::getStatus, 1);
        // 1.4 如果查询结果记录数大于0，说明待删除套餐中包含在售套餐，直接抛出业务异常
        if (this.count(wq) > 0) {
            throw new CustomException("所删除套餐中包含在售套餐，删除失败，请停售后再删除");
        }

        // 2.能走到这里，就是已经全部停售了。首先删除setmeal表中的套餐记录
        this.removeByIds(ids);

        // 3.根据套餐ID删除数据表setmeal_dish中的关联菜品
        // 3.1创建过滤条件封装器
        LambdaQueryWrapper<SetmealDish> lqw = new LambdaQueryWrapper<>();
        // 3.2添加过滤条件：IN()
        lqw.in(SetmealDish::getSetmealId, ids);
        // 3.3删除setmeal_dish表中所有的套餐关联菜品
        return setmealDishService.remove(lqw);
    }

    // 根据菜品IDs集合，查询对应套餐Ids集合
    @Override
    public Set<Long> getIdsByDishId(List<Long> dishIds) {
        // 1.创建setmeal_dish的过滤条件封装器
        LambdaQueryWrapper<SetmealDish> lqw = new LambdaQueryWrapper<>();
        // 2.添加过滤条件：IN()
        lqw.in(SetmealDish::getDishId, dishIds);
        // 3.查询对应的SetmealDish实体类集合
        List<SetmealDish> setmealDishes = setmealDishService.list(lqw);
        // 4.获取其setmealId的去重集合：Set集合是无序不可重复的
        return setmealDishes.stream().map(SetmealDish::getSetmealId).collect(Collectors.toSet());
    }

    // 根据条件查询套餐集合
    @Override
    public List<Setmeal> list(Setmeal setmeal) {
        // 1.创建查询条件封装器
        LambdaQueryWrapper<Setmeal> lqw = new LambdaQueryWrapper<>();
        // 2.添加查询条件：根据类别ID查询
        lqw.eq(setmeal.getCategoryId() != null, Setmeal::getCategoryId, setmeal.getCategoryId());
        // 3.添加查询条件：根据售卖状态查询
        lqw.eq(setmeal.getStatus() != null, Setmeal::getStatus, setmeal.getStatus());
        // 4.调用数据层返回套餐对象构成的集合
        return this.list(lqw);
    }
}
