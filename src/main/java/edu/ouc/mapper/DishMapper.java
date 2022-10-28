package edu.ouc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.ouc.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Sihang Xie
 * @Description: 菜品的数据层
 * @Date: 2022/10/3 10:21
 * @Version: 0.0.1
 * @Modified By:
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
