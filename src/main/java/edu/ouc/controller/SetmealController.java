package edu.ouc.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.ouc.common.R;
import edu.ouc.dto.SetmealDto;
import edu.ouc.entity.Setmeal;
import edu.ouc.service.ISetmealDishService;
import edu.ouc.service.ISetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @Author: Sihang Xie
 * @Description: 套餐管理的控制层
 * @Date: 2022/10/12 12:00
 * @Version: 0.0.1
 * @Modified By:
 */
@Slf4j
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Autowired
    private ISetmealService setmealService;

    @Autowired
    private ISetmealDishService setmealDishService;

    // 保存新套餐，同时插入套餐对应菜品
    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto) {
        if (setmealService.saveWithSetmealDishes(setmealDto)) {
            return R.success("保存成功");
        }
        return R.error("保存失败");
    }

    // 带搜索功能的分页查询
    @GetMapping("/page")
    public R<Page<SetmealDto>> getPage(Long page, Long pageSize, String name) {
        return R.success(setmealService.getPage(page, pageSize, name));
    }

    // 根据套餐ID查询单个带套餐菜品的套餐信息
    @GetMapping("/{id}")
    public R<SetmealDto> getById(@PathVariable Long id) {
        return R.success(setmealService.getByIdWithDishes(id));
    }

    // 修改套餐信息和套餐关联菜品
    @PutMapping
    public R<String> updateWithDishes(@RequestBody SetmealDto setmealDto) {
        if (setmealService.updateWithDishes(setmealDto)) {
            return R.success("修改成功");
        }
        return R.error("修改失败");
    }

    // (批量)套餐启售/停售
    @PostMapping("/status/{status}")
    @CacheEvict(value = "setmealCache", allEntries = true)  // 表示删除"SetmealCache"下的所有缓存数据(个人感觉粒度太粗了)
    public R<String> updateStatus(@PathVariable Integer status, @RequestParam List<Long> ids) {
        if (setmealService.updateStatus(status, ids)) {
            return R.success("修改成功");
        }
        return R.error("修改失败");
    }

    // 根据套餐ID删除(批量删除)套餐
    @DeleteMapping
    @CacheEvict(value = "setmealCache", allEntries = true)  // 表示删除"SetmealCache"下的所有缓存数据(个人感觉粒度太粗了)
    public R<String> removeWithDishes(@RequestParam List<Long> ids) {
        if (setmealService.removeWithDish(ids)) {
            return R.success("删除成功");
        }
        return R.error("删除失败");
    }

    // 根据条件查询套餐集合
    @GetMapping("/list")
    public R<List<Setmeal>> list(Setmeal setmeal) {
        return R.success(setmealService.list(setmeal));
    }
}
