package edu.ouc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.ouc.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Sihang Xie
 * @Description: 创建订单明细表数据层
 * @Date: 2022/10/27 10:26
 * @Version: 0.0.1
 * @Modified By:
 */
@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {
}
