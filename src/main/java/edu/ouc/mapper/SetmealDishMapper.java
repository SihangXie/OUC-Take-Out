package edu.ouc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.ouc.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Sihang Xie
 * @Description: 套餐菜品关联表的数据层
 * @Date: 2022/10/12 11:52
 * @Version: 0.0.1
 * @Modified By:
 */
@Mapper
public interface SetmealDishMapper extends BaseMapper<SetmealDish> {
}
