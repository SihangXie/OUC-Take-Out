package edu.ouc.common;

/**
 * @Author: Sihang Xie
 * @Description: 自定义业务异常
 * @Date: 2022/10/3 11:42
 * @Version: 0.0.1
 * @Modified By:
 */
public class CustomException extends RuntimeException {

    // 序列化ID
    static final Long serialVersionUID = 32424L;

    // 空参构造器
    public CustomException() {
    }

    // 带参构造器，入参是异常信息
    public CustomException(String message) {
        super(message);
    }
}
