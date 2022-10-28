package edu.ouc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.ouc.entity.DishFlavor;
import edu.ouc.mapper.DishFlavorMapper;
import edu.ouc.service.IDishFlavorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: Sihang Xie
 * @Description: 菜品口味业务层接口的实现类
 * @Date: 2022/10/3 17:01
 * @Version: 0.0.1
 * @Modified By:
 */
@Slf4j
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements IDishFlavorService {
}
