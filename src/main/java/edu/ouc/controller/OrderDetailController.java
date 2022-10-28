package edu.ouc.controller;

import edu.ouc.service.impl.OrderDetailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Sihang Xie
 * @Description: 订单明细表控制层
 * @Date: 2022/10/27 10:49
 * @Version: 0.0.1
 * @Modified By:
 */
@Slf4j
@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {
    @Autowired
    private OrderDetailServiceImpl orderDetailService;
}
