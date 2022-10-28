package edu.ouc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.ouc.entity.Setmeal;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Sihang Xie
 * @Description: 套餐的数据层接口
 * @Date: 2022/10/3 10:27
 * @Version: 0.0.1
 * @Modified By:
 */
@Mapper
public interface SetmealMapper extends BaseMapper<Setmeal> {
}
