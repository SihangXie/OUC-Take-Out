package edu.ouc.controller;

import edu.ouc.common.R;
import edu.ouc.entity.User;
import edu.ouc.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author: Sihang Xie
 * @Description: 用户控制层
 * @Date: 2022/10/22 10:23
 * @Version: 0.0.1
 * @Modified By:
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    // 发送邮箱验证码
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session) throws MessagingException {
        if (userService.sendMsg(user, session)) {
            return R.success("验证码发送成功");
        }
        return R.error("验证码发送失败");
    }

    // 移动端用户登录登录
    @PostMapping("/login")
    public R<User> login(@RequestBody Map<String, String> map, HttpSession session) {
        User user = userService.login(map, session);
        return R.success(user);
    }

    // 移动端用户退出登录
    @PostMapping("/loginout")
    public R<String> logout(HttpSession session) {
        if (userService.logout(session)) {
            return R.success("退出成功");
        }
        return R.error("退出失败");
    }
}
