package edu.ouc.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.ouc.entity.Category;

/**
 * @Author: Sihang Xie
 * @Description: Category的业务层接口
 * @Date: 2022/10/2 14:40
 * @Version: 0.0.1
 * @Modified By:
 */
public interface ICategoryService extends IService<Category> {

    // 分页查询
    Page<Category> getPage(Long currentPage, Long pageSize);
    
    // 完善的删除分类操作
    Boolean remove(Long id);
}
