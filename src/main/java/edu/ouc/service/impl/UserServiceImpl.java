package edu.ouc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.ouc.common.BaseContext;
import edu.ouc.common.CustomException;
import edu.ouc.entity.User;
import edu.ouc.mapper.UserMapper;
import edu.ouc.service.IUserService;
import edu.ouc.utils.MailUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Sihang Xie
 * @Description: 用户业务层接口实现类
 * @Date: 2022/10/22 10:21
 * @Version: 0.0.1
 * @Modified By:
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    // 注入RedisTemplate对象
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // 发送邮箱验证码
    @Override
    public Boolean sendMsg(User user, HttpSession session) throws MessagingException {
        // 1.获取前端传来的用户邮箱
        String email = user.getEmail();
        // 2.如果邮箱不为空才进行下一步操作
        if (!email.isEmpty()) {
            // 2.1 随机生成六位数验证码
            String code = MailUtils.getCode();
            // 2.2 发送验证码邮件
            MailUtils.sendMail(email, code);
            // 2.3 将验证码存入到Redis中，key是email，value是验证码，有效期设置为5分钟
            redisTemplate.opsForValue().set(email, code, 5, TimeUnit.MINUTES);
            return true;
        }
        return false;
    }

    // 移动端用户登录登录
    @Override
    public User login(Map<String, String> map, HttpSession session) {
        // 获取前端传送来的用户邮箱
        String email = map.get("email");
        // 获取前端传送来的验证码
        String code = map.get("code");
        // 验证邮箱和验证码是否为空，如果为空则直接登录失败
        if (email.isEmpty() || code.isEmpty()) {
            throw new CustomException("邮箱或验证码不能为空");
        }

        // 如果邮箱和验证码不为空，前往调用数据层查询数据库有无该用户
        // 从Redis中获取缓存的验证码
        String trueCode = (String) redisTemplate.opsForValue().get(email);

        // 比对用户输入的验证码和真实验证码，错了直接登录失败
        if (!code.equals(trueCode)) {
//        if (false) {  // 用于调试时免验证码校验登录
            throw new CustomException("验证码错误");
        }

        // 验证码匹配，开始调用数据库查询
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getEmail, email);
        User user = this.getOne(lqw);

        // 如果数据库中没有该用户，就是新用户，要添加新用户
        if (user == null) {
            // 添加新用户
            user = new User();
            user.setEmail(email);
            user.setStatus(1);
            this.save(user);
        }
        // 最后把这个登录用户存到session保存作用域中，表示已登录，让拦截器放行
        session.setAttribute("user", user.getId());

        // 用户登录成功，删除Redis中缓存的验证码
        redisTemplate.delete(email);

        return user;
    }

    // 移动端用户退出登录
    @Override
    public Boolean logout(HttpSession session) {
        Long userId = BaseContext.getCurrentUserId();
        User user = this.getById(userId);
        String email = user.getEmail();
        // 清除Session保存作用域中保存的数据
        session.removeAttribute("user");
        session.removeAttribute(email);
        return true;
    }
}
