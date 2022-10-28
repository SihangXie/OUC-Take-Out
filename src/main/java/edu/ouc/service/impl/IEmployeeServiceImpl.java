package edu.ouc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.ouc.entity.Employee;
import edu.ouc.mapper.EmployeeMapper;
import edu.ouc.service.IEmployeeService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Sihang Xie
 * @Description: 员工employee的创建服务层接口实现类
 * @Date: 2022/9/29 13:24
 * @Version: 0.0.1
 * @Modified By:
 */
@Service
public class IEmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    // 自动注入数据层
    @Autowired
    private EmployeeMapper employeeDAO;


    // 分页查询+条件查询
    @Override
    public Page<Employee> getPage(Long currentPage, Long pageSize, String name) {

        // 1.创建新的条件查询构造器，用于根据员工姓名搜索
        LambdaQueryWrapper<Employee> lqw = new LambdaQueryWrapper<>();

        // 2.添加过滤条件：根据员工姓名查询
        lqw.like(Strings.isNotEmpty(name), Employee::getName, name);

        // 3.添加排序条件：按最后修改时间降序排序
        lqw.orderByDesc(Employee::getUpdateTime);

        // 4.分页构造器
        Page<Employee> page = new Page<>(currentPage, pageSize);

        // 5.把分页和条件查询包装器传入数据层的selectPage()方法
        return employeeDAO.selectPage(page, lqw);
    }
}
