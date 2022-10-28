package edu.ouc.common;

/**
 * @Author: Sihang Xie
 * @Description: 基于ThreadLocal封装的工具类，用于保存和获取当前登录用户的ID
 * @Date: 2022/10/2 13:56
 * @Version: 0.0.1
 * @Modified By:
 */
public class BaseContext {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    // 保存当前登录用户的ID
    public static void setCurrentUserId(Long id) {
        threadLocal.set(id);
    }

    // 获取当前登录用户的ID
    public static Long getCurrentUserId() {
        return threadLocal.get();
    }
}
