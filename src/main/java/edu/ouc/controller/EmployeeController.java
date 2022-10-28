package edu.ouc.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.ouc.common.R;
import edu.ouc.entity.Employee;
import edu.ouc.service.IEmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Sihang Xie
 * @Description: 员工Employee表现层
 * @Date: 2022/9/29 13:48
 * @Version: 0.0.1
 * @Modified By:
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    // 注入业务层
    @Autowired
    private IEmployeeService empService;

    // 后台员工登录方法
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {
        String username = employee.getUsername();
        String password = employee.getPassword();
        // 1.对前端传入的密码进行md5加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        // 2.根据前端传入的账号去数据库中查询
        // 2.1 创建查询条件对象
        LambdaQueryWrapper<Employee> lqw = new LambdaQueryWrapper<>();
        // 2.2 使用MP的等值查询eq
        lqw.eq(Strings.isNotEmpty(username), Employee::getUsername, username);
        // 2.3 根据lqw的条件进行等值查询
        Employee emp = empService.getOne(lqw);

        // 3.判断查询到的员工是否为空
        if (emp == null) {
            return R.error("用户不存在，登录失败");
        }
        // 4.密码比对
        if (!password.equals(emp.getPassword())) {
            return R.error("密码错误，登录失败");
        }
        // 5.查看员工状态是否禁用
        if (emp.getStatus() != 1) {
            return R.error("该账号已禁用");
        }
        // 6.将员工ID存放在Session保存作用域中
        request.getSession().setAttribute("employee", emp.getId());
        return R.success(emp);
    }

    // 后台员工退出登录方法
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        // 1.清除Session保存作用域中保存的数据
        request.getSession().removeAttribute("employee");
        // 2.返回结果
        return R.success("退出成功");
    }

    // 新增员工功能
    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Employee employee) {

        // 1.设置创建人ID
//        employee.setCreateUser((Long) request.getSession().getAttribute("employee"));

        // 2.设置最后修改人ID
//        employee.setUpdateUser((Long) request.getSession().getAttribute("employee"));

        // 3.设置初始密码为身份证后6位，并经过MD5加密
        String idNumber = employee.getIdNumber();
        String password = DigestUtils.md5DigestAsHex(idNumber.substring(idNumber.length() - 6).getBytes());
        employee.setPassword(password);

        // 4.设置创建时间
//        employee.setCreateTime(LocalDateTime.now());

        // 5.设置修改时间
//        employee.setUpdateTime(LocalDateTime.now());

        // 6.调用业务层保存到数据库中
        empService.save(employee);
        return R.success("添加成功");
    }

    // 分页查询+根据员工姓名查询功能
    @GetMapping("/page")
    public R<Page<Employee>> getPage(Long page, Long pageSize, String name) {
        return R.success(empService.getPage(page, pageSize, name));
    }

    // 修改员工信息
    @PutMapping
    public R<String> update(@RequestBody Employee employee) {
        if (empService.updateById(employee)) {

            // 查看当前线程的ID
            long id = Thread.currentThread().getId();
            log.info("线程ID为：{}", id);

            return R.success("修改成功");
        }
        return R.error("修改失败");
    }

    // 根据ID查询员工信息
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id) {
        Employee employee = empService.getById(id);
        // 当查询结果不为空时才返回employee
        if (employee != null) {
            return R.success(employee);
        }
        return R.error("查询员工不存在");
    }
}
