package edu.ouc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.ouc.entity.ShoppingCart;

import java.util.List;

/**
 * @Author: Sihang Xie
 * @Description: 购物车业务层接口
 * @Date: 2022/10/24 15:06
 * @Version: 0.0.1
 * @Modified By:
 */
public interface IShoppingCartService extends IService<ShoppingCart> {

    // 添加菜品到购物车
    ShoppingCart add(ShoppingCart shoppingCart);

    // 查询当前用户的购物车中所有信息
    List<ShoppingCart> getUserList();

    // 清空购物车
    Boolean clean();

    // 购物车商品减一
    Boolean sub(ShoppingCart shoppingCart);
}
