package edu.ouc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.ouc.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Sihang Xie
 * @Description: 菜品口味数据层接口
 * @Date: 2022/10/3 16:58
 * @Version: 0.0.1
 * @Modified By:
 */
@Mapper
public interface DishFlavorMapper extends BaseMapper<DishFlavor> {
}
