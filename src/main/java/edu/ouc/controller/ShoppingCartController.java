package edu.ouc.controller;

import edu.ouc.common.R;
import edu.ouc.entity.ShoppingCart;
import edu.ouc.service.impl.ShoppingCartServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Sihang Xie
 * @Description: 购物车控制层
 * @Date: 2022/10/24 15:09
 * @Version: 0.0.1
 * @Modified By:
 */
@Slf4j
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartServiceImpl shoppingCartService;

    // 添加菜品到购物车
    @PostMapping("/add")
    public R<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart) {
        return R.success(shoppingCartService.add(shoppingCart));
    }

    // 查询当前用户的购物车中所有信息
    @GetMapping("/list")
    public R<List<ShoppingCart>> list() {
        return R.success(shoppingCartService.getUserList());
    }

    // 清空购物车
    @DeleteMapping("/clean")
    public R<String> clean() {
        if (shoppingCartService.clean()) {
            return R.success("清空成功");
        }
        return R.error("清空失败");
    }

    // 购物车商品减一
    @PostMapping("/sub")
    public R<String> sub(@RequestBody ShoppingCart shoppingCart) {
        if (shoppingCartService.sub(shoppingCart)) {
            return R.success("删除成功");
        }
        return R.error("删除失败");
    }
}
