package edu.ouc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.ouc.entity.OrderDetail;
import edu.ouc.mapper.OrderDetailMapper;
import edu.ouc.service.IOrderDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: Sihang Xie
 * @Description: 订单明细表业务层接口实现类
 * @Date: 2022/10/27 10:35
 * @Version: 0.0.1
 * @Modified By:
 */
@Slf4j
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements IOrderDetailService {
}
