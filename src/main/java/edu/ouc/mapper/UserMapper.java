package edu.ouc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.ouc.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Sihang Xie
 * @Description: 用户数据层Mapper
 * @Date: 2022/10/22 10:18
 * @Version: 0.0.1
 * @Modified By:
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
