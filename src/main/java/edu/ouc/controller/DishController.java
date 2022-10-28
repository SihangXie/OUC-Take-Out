package edu.ouc.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.ouc.common.R;
import edu.ouc.dto.DishDto;
import edu.ouc.entity.Dish;
import edu.ouc.service.impl.DishFlavorServiceImpl;
import edu.ouc.service.impl.DishServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Sihang Xie
 * @Description: 菜品的表现层
 * @Date: 2022/10/3 16:20
 * @Version: 0.0.1
 * @Modified By:
 */
@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private DishServiceImpl dishService;

    @Autowired
    private DishFlavorServiceImpl dishFlavorService;

    // 添加菜品，同时插入菜品对应的口味数据
    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto) {
        if (dishService.saveWithFlavor(dishDto)) {
            return R.success("保存成功");
        }
        return R.error("保存失败");
    }

    // 分页查询+按菜品名称查询
    @GetMapping("/page")
    public R<Page<DishDto>> getPage(Long page, Long pageSize, String name) {
        return R.success(dishService.getPage(page, pageSize, name));
    }

    // 根据菜品ID查询菜品信息和对应的口味信息
    @GetMapping("/{id}")
    public R<DishDto> getById(@PathVariable Long id) {
        return R.success(dishService.getByIdWithFlavor(id));
    }

    // 修改菜品，同时修改菜品对应的口味数据
    @PutMapping
    public R<String> update(@RequestBody DishDto dishDto) {
        if (dishService.updateWithFlavor(dishDto)) {
            return R.success("修改成功");
        }
        return R.error("修改失败");
    }

    // (批量)停售/启售菜品
    @PostMapping("/status/{status}")
    public R<String> updateStatus(@PathVariable Integer status, @RequestParam List<Long> ids) {
        if (dishService.updateStatus(status, ids)) {
            return R.success("修改状态成功");
        }
        return R.error("修改状态失败");
    }

    // 删除(批量删除)菜品
    @DeleteMapping
    public R<String> remove(@RequestParam List<Long> ids) {
        if (dishService.removeWithFlavor(ids)) {
            return R.success("删除成功");
        }
        return R.error("删除失败");
    }

    // 根据指定过滤条件查询菜品
    @GetMapping("/list")
    public R<List<DishDto>> listByCategoryId(Dish dish) {
        return R.success(dishService.listWithFlavor(dish));
    }
}
