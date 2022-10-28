package edu.ouc.filter;

import com.alibaba.fastjson.JSON;
import edu.ouc.common.BaseContext;
import edu.ouc.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Sihang Xie
 * @Description: 用户登录检查过滤器
 * @Date: 2022/9/30 9:47
 * @Version: 0.0.1
 * @Modified By:
 */
// 过滤器注解
// filterName：过滤器名称，可以随便起
// urlPatterns：想要拦截的URL地址，/*表示拦截所有请求
@WebFilter(filterName = "LoginCheckFilter", urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {

    // Spring框架提供的路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 强转
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 1.获取本次请求的URL
        String requestURI = request.getRequestURI();    // /backend/index.html

        log.info("拦截到请求URL：{}", requestURI);

        // 2.定义不需要拦截的URL地址数组
        String[] urls = new String[]{
                "/employee/login",  // 登录页面
                "/employee/logout", // 退出登录
                "/backend/**",      // 后台页面的页面的静态资源
                "/front/**",        // 移动端页面的静态资源
                "/user/login",      // 用户登录
                "/user/sendMsg"     // 发送登录验证码
        };

        // 3.判断本次请求URL是否需要拦截
        Boolean check = check(urls, requestURI);

        // 4.如果check为true则不需要处理，直接放行
        if (check) {
            log.info("本次请求{}不需要处理", requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        // 5.如果需要处理，则判断B端员工是否登录
        if (request.getSession().getAttribute("employee") != null) {
            // 能进入说明已经登录，直接放行
            Long id = (Long) request.getSession().getAttribute("employee");
            log.info("B端员工{}已登录", id);

            // 把当前登录用户的ID保存到ThreadLocal中
            BaseContext.setCurrentUserId(id);

            filterChain.doFilter(request, response);
            return;
        }

        // 6.如果需要处理，判断C端用户是否登录
        if (request.getSession().getAttribute("user") != null) {
            // 能进入说明已经登录，直接放行
            Long userId = (Long) request.getSession().getAttribute("user");
            log.info("邮箱用户{}已登录", userId);

            // 把当前登录用户的ID保存到ThreadLocal中
            BaseContext.setCurrentUserId(userId);

            // 放行
            filterChain.doFilter(request, response);
            return;
        }

        log.info("用户未登录");

        // 7.走到这里就是没登录
        // 向浏览器响应一个流，让前端读到R里面的数据
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
    }

    // 核查请求URL是否在放行URL数组中，检查本次请求是否需要放行
    private Boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) {
                return true;
            }
        }
        // 循环完了都匹配不上就返回false
        return false;
    }
}
