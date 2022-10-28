package edu.ouc.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Sihang Xie
 * @Description: 登录功能的数据层单元测试
 * @Date: 2022/9/29 13:04
 * @Version: 0.0.1
 * @Modified By:
 */
@SpringBootTest
public class EmployeeDAOTest {

    @Autowired
    private EmployeeMapper employeeDAO;

    @Test
    void testSelect() {
        employeeDAO.selectById(1);
    }
}
