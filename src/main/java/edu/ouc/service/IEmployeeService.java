package edu.ouc.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.ouc.entity.Employee;

/**
 * @Author: Sihang Xie
 * @Description: 员工employee的创建服务层接口
 * @Date: 2022/9/29 13:19
 * @Version: 0.0.1
 * @Modified By:
 */
public interface IEmployeeService extends IService<Employee> {

    // 分页查询+条件查询
    Page<Employee> getPage(Long currentPage, Long pageSize, String name);
}
