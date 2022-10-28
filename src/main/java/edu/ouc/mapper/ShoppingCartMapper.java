package edu.ouc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.ouc.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Sihang Xie
 * @Description: 购物车数据层
 * @Date: 2022/10/24 15:04
 * @Version: 0.0.1
 * @Modified By:
 */
@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {
}
