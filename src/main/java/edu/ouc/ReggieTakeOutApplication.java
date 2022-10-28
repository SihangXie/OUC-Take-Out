package edu.ouc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j  // 日志
@SpringBootApplication  // Spring Boot启动类
@ServletComponentScan   // Servlet组件扫描，扫描过滤器
@EnableTransactionManagement    // 开启Spring事务注解管理
public class ReggieTakeOutApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReggieTakeOutApplication.class, args);
        // 打印Slf4j日志
        log.info("项目启动成功");
    }
}
