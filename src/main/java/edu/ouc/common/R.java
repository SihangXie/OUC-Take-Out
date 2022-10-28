package edu.ouc.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Sihang Xie
 * @Description: 前后端统一格式协议，通用的返回结果类
 * @Date: 2022/9/29 13:54
 * @Version: 0.0.1
 * @Modified By:
 */
@Data
public class R<T> {
    private Integer code;
    private T data;
    private String msg;
    private Map map = new HashMap();// 动态数据

    public R() {
    }

    // 适用于成功
    public static <T> R<T> success(T object) {
        R<T> r = new R<>();
        r.code = 1;
        r.data = object;
        return r;
    }

    // 适用于失败
    public static <T> R<T> error(String msg) {
        R<T> r = new R<>();
        r.code = 0;
        r.msg = msg;
        return r;
    }

    // 操作动态数据的
    public R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }
}
