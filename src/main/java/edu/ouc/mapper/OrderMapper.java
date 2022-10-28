package edu.ouc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.ouc.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Sihang Xie
 * @Description: 订单数据层接口
 * @Date: 2022/10/27 10:25
 * @Version: 0.0.1
 * @Modified By:
 */
@Mapper
public interface OrderMapper extends BaseMapper<Orders> {
}
