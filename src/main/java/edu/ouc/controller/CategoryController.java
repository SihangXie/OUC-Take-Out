package edu.ouc.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.ouc.common.R;
import edu.ouc.entity.Category;
import edu.ouc.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Sihang Xie
 * @Description: Category的表现层
 * @Date: 2022/10/2 14:45
 * @Version: 0.0.1
 * @Modified By:
 */
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    // 新增分类
    @PostMapping
    public R<String> save(@RequestBody Category category) {
        if (categoryService.save(category)) {
            return R.success("添加成功");
        }
        return R.error("添加失败");
    }

    // 分页查询
    @GetMapping("/page")
    public R<Page<Category>> getPage(Long page, Long pageSize) {
        return R.success(categoryService.getPage(page, pageSize));
    }

    // 完善的删除分类操作
    // 根据id删除分类，删除之前判断分类下是否有关联的菜品或套餐
    @DeleteMapping
    public R<String> deleteById(Long ids) {
        if (categoryService.remove(ids)) {
            return R.success("删除成功");
        }
        return R.error("删除失败");
    }

    // 修改分类
    @PutMapping
    public R<String> update(@RequestBody Category category) {
        if (categoryService.updateById(category)) {
            return R.success("修改成功");
        }
        return R.error("修改失败");
    }

    // 添加菜品或套餐时获取分类信息
    @GetMapping("/list")
    public R<List<Category>> list(Category category) {
        LambdaQueryWrapper<Category> lqw = new LambdaQueryWrapper<>();
        lqw.eq(category.getType() != null, Category::getType, category.getType());
        // 双重排序条件
        lqw.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);
        return R.success(categoryService.list(lqw));
    }
}
