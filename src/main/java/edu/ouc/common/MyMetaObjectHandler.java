package edu.ouc.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author: Sihang Xie
 * @Description: 自定义元数据对象处理器
 * @Date: 2022/10/2 11:48
 * @Version: 0.0.1
 * @Modified By:
 */
@Component  // 让Spring管理
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    // 当执行插入数据时自动填充
    @Override
    public void insertFill(MetaObject metaObject) {

        // 实体类中创建了这个属性才会自动填充
        if (metaObject.hasSetter("createTime")) {
            metaObject.setValue("createTime", LocalDateTime.now());
        }
        if (metaObject.hasSetter("updateTime")) {
            metaObject.setValue("updateTime", LocalDateTime.now());
        }

        // 获取当前线程的登录用户的ID
        Long id = BaseContext.getCurrentUserId();

        if (metaObject.hasSetter("createUser")) {
            metaObject.setValue("createUser", id);
        }
        if (metaObject.hasSetter("updateUser")) {
            metaObject.setValue("updateUser", id);
        }

        // 如果实体类中有下单时间，才会自动填充
        if (metaObject.hasSetter("orderTime")) {
            metaObject.setValue("orderTime", LocalDateTime.now());
        }
        if (metaObject.hasSetter("checkoutTime")) {
            metaObject.setValue("checkoutTime", LocalDateTime.now());
        }
    }

    // 当执行更新数据时自动填充
    @Override
    public void updateFill(MetaObject metaObject) {

        // 获取当前线程的登录用户的ID
        Long id = BaseContext.getCurrentUserId();

        if (metaObject.hasSetter("updateUser")) {
            metaObject.setValue("updateUser", id);
        }
        if (metaObject.hasSetter("updateTime")) {
            metaObject.setValue("updateTime", LocalDateTime.now());
        }
    }
}
