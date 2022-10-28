package edu.ouc.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @Author: Sihang Xie
 * @Description: 全局异常处理器
 * @Date: 2022/9/30 15:40
 * @Version: 0.0.1
 * @Modified By:
 */
// Spring提供的注解，拦截所有注解是@RestController和@Controller的Controller
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody   // 最终要返回json数据
@Slf4j
public class GlobalExceptionHandler {

    // 异常处理器注解，括号里是该方法要处理的异常类型
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex) {
        log.info(ex.getMessage());  // Duplicate entry '111' for key 'employee.idx_username'

        // 1.判断异常信息是否包含违反唯一约束的前面两个字
        if (ex.getMessage().contains("Duplicate entry")) {

            // 2.按空格把异常信息分割成String数组
            String[] split = ex.getMessage().split(" ");

            // 3.取数组索引为2的字符串，即重复的username
            String msg = split[2] + "已存在，添加失败";

            // 4.返回错误提示信息
            return R.error(msg);
        }
        // 5.如果不是则返回未知错误
        return R.error("未知错误");
    }

    // 捕获删除分类失败的业务异常
    @ExceptionHandler(CustomException.class)
    public R<String> customerExceptionHandler(CustomException ce) {
        return R.error(ce.getMessage());
    }
}
