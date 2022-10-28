package edu.ouc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.ouc.entity.User;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author: Sihang Xie
 * @Description: 用户业务层接口
 * @Date: 2022/10/22 10:20
 * @Version: 0.0.1
 * @Modified By:
 */
public interface IUserService extends IService<User> {
    // 发送邮箱验证码
    Boolean sendMsg(User user, HttpSession session) throws MessagingException;

    // 移动端用户登录
    User login(Map<String, String> map, HttpSession session);

    // 移动端用户退出登录
    Boolean logout(HttpSession session);
}
