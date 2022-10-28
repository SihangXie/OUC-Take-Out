package edu.ouc.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Sihang Xie
 * @Description: 用于分页功能的MP拦截器
 * @Date: 2022/9/30 17:42
 * @Version: 0.0.1
 * @Modified By:
 */
@Configuration  // 声明为配置类，以便被Spring扫描到，读取配置
public class MPConfig {

    @Bean   // /Spring第三方Bean注解，以便被Spring管理
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        // 1.创建MP的拦截器容器
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        // 2.往MP拦截器容器中添加分页拦截器
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());

        // 3.返回拦截器容器
        return interceptor;
    }
}
