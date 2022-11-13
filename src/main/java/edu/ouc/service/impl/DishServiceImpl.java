package edu.ouc.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.ouc.common.CustomException;
import edu.ouc.dto.DishDto;
import edu.ouc.entity.Dish;
import edu.ouc.entity.DishFlavor;
import edu.ouc.entity.Setmeal;
import edu.ouc.mapper.DishMapper;
import edu.ouc.service.IDishService;
import edu.ouc.service.ISetmealService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: Sihang Xie
 * @Description: 菜品的业务层接口实现类
 * @Date: 2022/10/3 10:24
 * @Version: 0.0.1
 * @Modified By:
 */
@Slf4j
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements IDishService {


    @Autowired
    private DishFlavorServiceImpl dishFlavorService;

    // 注入分类业务层依赖
    @Lazy
    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private ISetmealService setmealService;

    // 自动注入StringRedisTemplate类对象
    @Autowired
    private StringRedisTemplate redisTemplate;


    // 新增菜品，同时插入菜品对应的口味数据
    @Transactional  // 涉及到多张表操作，开启事务
    @CacheEvict(value = "DishCache", key = "#dishDto.categoryId + '-1'")
    public Boolean saveWithFlavor(DishDto dishDto) {

        // 1.保存菜品的基本信息
        this.save(dishDto);

        // 2.获取菜品的ID
        Long dishId = dishDto.getId();

        // 3.遍历菜品口味并逐一赋上菜品ID值，然后保存
        List<DishFlavor> flavors = dishDto.getFlavors();
        // 3.1 使用Stream处理集合，结果赋回给它自己
        // peek()是stream的中间操作，是对象的时候才能修改
        flavors = flavors.stream().peek(flavor -> flavor.setDishId(dishId)).collect(Collectors.toList());

        // 4.调用批量保存
        return dishFlavorService.saveBatch(flavors);
    }

    // 分页查询+按菜品名称查询
    @Override
    public Page<DishDto> getPage(Long page, Long pageSize, String name) {
        // 1.创建分页构造器
        Page<Dish> dishPage = new Page<>(page, pageSize);
        // 1.1 用于解决浏览器显示菜品分类名称，但需要给分页构造器赋值，采用复制dishPage的方法
        Page<DishDto> dishDtoPage = new Page<>();

        // 2.创建查询条件构造器
        LambdaQueryWrapper<Dish> lqw = new LambdaQueryWrapper<>();
        // 3.添加过滤添加，按菜品名称查询
        lqw.like(Strings.isNotEmpty(name), Dish::getName, name);
        // 4.添加排序条件，按菜品更新时间降序排列
        lqw.orderByDesc(Dish::getUpdateTime);
        // 5.调用数据层的分页查询方法，此时dishPage中已经有值了
        page(dishPage, lqw);

        // 6.对象拷贝
        // 6.1 把dishPage中的属性值复制到dishDtoPage中，但要忽略dishPage中的records
        // 这是因为records中的数据是真正展示到浏览器上的，我们要处理一下records中的数据
        BeanUtils.copyProperties(dishPage, dishDtoPage, "records");

        // 7.使用stream处理dishRecords集合，目的是处理成dishDtoRecords的集合
        // 7.1 获取dishPage的records
        List<Dish> dishRecords = dishPage.getRecords();
        // 7.2 通过stream流处理dishRecords，目的是要根据分类ID查询分类表，最终获得分类名称
        List<DishDto> dishDtoRecords = dishRecords.stream().map(dish -> { // 遍历dishRecords集合中的每个dish，进行如下操作

            // 7.2.1 获取每个Dish的分类id
            Long categoryId = dish.getCategoryId();
            // 7.2.2 根据分类ID查询分类表，最终获得分类名称
            String categoryName = categoryService.getById(categoryId).getName();
            // 7.2.3 创建DishDto对象
            DishDto dishDto = new DishDto();
            // 7.2.4 先把Dish对象的所有属性拷贝到DishDto对象，然后再设置刚刚获得的分类名称
            BeanUtils.copyProperties(dish, dishDto);
            // 7.2.5 设置分类名称
            dishDto.setCategoryName(categoryName);
            // 7.2.6 返回全部赋值完成的dishDto
            return dishDto;

        }).collect(Collectors.toList());    // stream流的终止操作：返回一个List集合

        // 8.把处理好的dishDtoRecords赋回dishDtoPage中
        dishDtoPage.setRecords(dishDtoRecords);

        return dishDtoPage;
    }

    // 根据菜品ID查询菜品信息和对应的口味信息
    @Override
    public DishDto getByIdWithFlavor(Long id) {

        // 1.根据ID查询菜品基本
        Dish dish = getById(id);

        // 2.查询菜品口味
        LambdaQueryWrapper<DishFlavor> lqw = new LambdaQueryWrapper<>();
        lqw.eq(DishFlavor::getDishId, id);
        List<DishFlavor> flavors = dishFlavorService.list(lqw);

        // 3.创建要返回的DishDto对象
        DishDto dishDto = new DishDto();

        // 4.把dish复制到dishDto中
        BeanUtils.copyProperties(dish, dishDto);

        // 5.把菜品口味封装到DishDto中
        dishDto.setFlavors(flavors);

        return dishDto;
    }

    // 修改菜品，同时修改菜品对应的口味数据
    @Override
    @Transactional  // 涉及到多张表操作，开启事务
    @CacheEvict(value = "DishCache", key = "#dishDto.categoryId + '-1'")
    public Boolean updateWithFlavor(DishDto dishDto) {

        // 1.删除菜品口味表中对应菜品的口味
        LambdaQueryWrapper<DishFlavor> lqw = new LambdaQueryWrapper<>();
        lqw.eq(DishFlavor::getDishId, dishDto.getId());
        dishFlavorService.remove(lqw);

        // 2.截取dishDto中的菜品口味信息
        List<DishFlavor> flavors = dishDto.getFlavors();

        // 3.使用stream流操作为菜品口味集合设置对应的菜品ID
        flavors = flavors.stream().peek(flavor -> flavor.setDishId(dishDto.getId())).collect(Collectors.toList());

        // 4.批量添加修改后的菜品口味到菜品口味表中
        dishFlavorService.saveBatch(flavors);

        // 5.更新菜品基本信息
        return this.updateById(dishDto);
    }

    // (批量)停售/启售菜品
    @Override
    public Boolean updateStatus(Integer status, List<Long> ids) {
        // 业务逻辑：如果要停售则必须检查关联套餐是否停售
        if (status == 0) {
            // 根据菜品IDs获取其关联的套餐IDs
            Set<Long> setmealIds = setmealService.getIdsByDishId(ids);
            // 如果该菜品关联的套餐IDs不为空，才继续进行下一步
            if (!setmealIds.isEmpty()) {
                // 创建setmeal的过滤条件封装器
                LambdaQueryWrapper<Setmeal> lqw = new LambdaQueryWrapper<>();
                // 添加过滤条件：IN()套餐IDs
                lqw.in(Setmeal::getId, setmealIds);
                // 添加过滤条件：在售套餐
                lqw.eq(Setmeal::getStatus, 1);
                // 如果满足的setmeal记录数大于0，则说明关联套餐在售，抛出业务异常
                if (setmealService.count(lqw) > 0) {
                    throw new CustomException("停售失败，菜品所关联套餐仍在售，请停售相关套餐");
                }
            }
        }

        // 目的：尽量减少与MySQL通信的次数
        // 1.根据菜品ID集合批量查询菜品
        List<Dish> dishes = this.listByIds(ids);

        // 2.使用集合的stream流修改售卖状态
        dishes = dishes.stream().peek(dish -> dish.setStatus(status)).collect(Collectors.toList());

        // 4.1 获取涉及到的菜品的类别ID集合，再封装成key
        Set<String> keys = dishes.stream().map(dish -> {
            // 获取每个菜品的类别ID
            Long categoryId = dish.getCategoryId();
            // 用hutool工具包把类别ID序列化成JSON字符串
            return "dish_" + JSONUtil.toJsonStr(categoryId) + "_1";
        }).collect(Collectors.toSet()); // 因为要求类别ID不重复，因此用Set
        // 4.2 清理Redis这个key的数据
        redisTemplate.delete(keys);

        // 5.批量update
        return this.updateBatchById(dishes);
    }

    // 删除(批量删除)菜品
    @Override
    public Boolean removeWithFlavor(List<Long> ids) {
        // 1.判断待删除菜品是否正在售卖
        // 1.1 创建dish的条件封装器
        LambdaQueryWrapper<Dish> dishLqw = new LambdaQueryWrapper<>();
        // 1.2 添加过滤条件：IN()-菜品ID要在入参ids中
        dishLqw.in(Dish::getId, ids);
        // 1.3 添加过滤条件：等值-菜品在售
        dishLqw.eq(Dish::getStatus, 1);
        // 1.4 调用数据层，根据条件封装器dishLqw的条件查询dish构成的集合
        List<Dish> dishes = this.list(dishLqw);
        // 1.5 如果dish集合不为空，说明待删除菜品中有在售菜品，抛出业务异常
        if (!dishes.isEmpty()) {
            // 1.6 获取在售菜品名称，方便用户停售对应菜品
            List<String> dishNames = dishes.stream().map(Dish::getName).collect(Collectors.toList());
            // 1.7 把在售菜品名称也返回到前端页面，方便用户停售对应菜品
            throw new CustomException(dishNames + "正在售卖，删除失败，请停售后重试");
        }

        // 2.判断待删除菜品关联套餐是否在售
        // 2.1 根据菜品IDs获取所关联的所有套餐IDs
        Set<Long> setmealIds = setmealService.getIdsByDishId(ids);
        // 2.2 创建setmeal的条件封装器
        LambdaQueryWrapper<Setmeal> setmealLqw = new LambdaQueryWrapper<>();
        // 2.3 添加过滤条件：IN()-套餐ID要在setmealIds中
        setmealLqw.in(Setmeal::getId, setmealIds);
        // 2.4 调用数据层，根据条件封装器setmealLqw的条件查询setmeal构成的集合
        List<Setmeal> setmeals = setmealService.list(setmealLqw);
        // 2.5 如果setmeal集合不为空，说明待删除菜品已绑定套餐，抛出业务异常
        if (!setmeals.isEmpty()) {
            // 2.6 获取绑定套餐名称，方便用户删除对应套餐
            List<String> setmealNames = setmeals.stream().map(Setmeal::getName).collect(Collectors.toList());
            // 2.7 把绑定套餐名称也返回到前端页面，方便用户删除对应套餐
            throw new CustomException(setmealNames + "套餐正在售卖该菜品，删除失败，请删除套餐后重试");
        }

        // 4. 清理Redis改菜品对应类别的缓存
        // 4.1 获取涉及到的菜品的类别ID集合，再封装成key
        Set<String> keys = dishes.stream().map(dish -> {
            // 获取每个菜品的类别ID
            Long categoryId = dish.getCategoryId();
            // 用hutool工具包把类别ID序列化成JSON字符串
            return "dish_" + JSONUtil.toJsonStr(categoryId) + "_1";
        }).collect(Collectors.toSet()); // 因为要求类别ID不重复，因此用Set
        // 4.2 清理Redis这个key的数据
        redisTemplate.delete(keys);

        // 批量删除菜品
        this.removeByIds(ids);

        // 删除菜品口味
        LambdaQueryWrapper<DishFlavor> dishFlavorLqw = new LambdaQueryWrapper<>();
        dishFlavorLqw.in(DishFlavor::getDishId, ids);
        return dishFlavorService.remove(dishFlavorLqw);
    }

    // 根据指定过滤条件查询菜品
    @Override
    @Cacheable(value = "DishCache", key = "#dish.categoryId + '-' + #dish.status")
    public List<DishDto> listWithFlavor(Dish dish) {
        // 1.创建条件过滤器
        LambdaQueryWrapper<Dish> lqw = new LambdaQueryWrapper<>();
        // 2.添加过滤条件：根据分类ID查询菜品
        lqw.eq(dish.getCategoryId() != null, Dish::getCategoryId, dish.getCategoryId());
        // 3.添加排序条件：根据sort字段升序排列菜品，再根据最后修改时间降序排列
        lqw.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);
        // 4.条件过滤条件：只查询启售的菜品
        lqw.eq(Dish::getStatus, 1);
        // 5.调用数据层的查询方法
        List<Dish> dishes = this.list(lqw);

        // 6.使用集合的stream流逐一把dish封装成dishDto
        return dishes.stream().map(dishItem -> {
            // 6.1 创建dishDto对象
            DishDto dishDto = new DishDto();
            // 6.2 把dish的所有属性值复制到dishDto对象中
            BeanUtils.copyProperties(dishItem, dishDto);
            // 6.3 创建DishFlavor的查询条件封装器
            LambdaQueryWrapper<DishFlavor> dfLqw = new LambdaQueryWrapper<>();
            // 6.4 添加查询条件：按dishId查询
            dfLqw.eq(DishFlavor::getDishId, dishItem.getId());
            // 6.5 根据查询条件查询菜品的口味集合
            List<DishFlavor> dishFlavors = dishFlavorService.list(dfLqw);
            // 6.6 把查询到的菜品口味集合设置到dishDto对象中
            dishDto.setFlavors(dishFlavors);
            // 6.7 返回封装好的dishDto对象
            return dishDto;
        }).collect(Collectors.toList());
    }
}
