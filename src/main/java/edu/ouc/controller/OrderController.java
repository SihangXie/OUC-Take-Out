package edu.ouc.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.ouc.common.R;
import edu.ouc.dto.OrderDto;
import edu.ouc.entity.Orders;
import edu.ouc.service.impl.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Sihang Xie
 * @Description: 订单控制层
 * @Date: 2022/10/27 10:45
 * @Version: 0.0.1
 * @Modified By:
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;

    // 提交(添加)订单
    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders) {
        if (orderService.submit(orders)) {
            return R.success("下单成功");
        }
        return R.error("下单失败");
    }

    // 用户端获取订单分页展示
    @GetMapping("/userPage")
    public R<Page<OrderDto>> getPage(Long page, Long pageSize) {
        return R.success(orderService.getPage(page, pageSize));
    }

    // 后台管理端获取订单分页展示
    @GetMapping("/page")
    public R<Page<OrderDto>> page(Long page, Long pageSize, String number, String beginTime, String endTime) {
        return R.success(orderService.getAllPage(page, pageSize, number, beginTime, endTime));
    }

    // 修改订单状态
    @PutMapping
    public R<String> update(@RequestBody Orders order) {
        if (orderService.update(order)) {
            return R.success("修改成功");
        }
        return R.error("修改失败");
    }
}
