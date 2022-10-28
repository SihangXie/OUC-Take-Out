package edu.ouc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.ouc.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Sihang Xie
 * @Description: 登录功能的数据层
 * @Date: 2022/9/29 12:58
 * @Version: 0.0.1
 * @Modified By:
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
