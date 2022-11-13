package edu.ouc.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

/**
 * @Author: Sihang Xie
 * @Description:
 * @Date: 2022/11/12 15:42
 * @Version: 0.0.1
 * @Modified By:
 */
@Configuration
@EnableCaching  // 开启Spring Cache缓存注解
@ConditionalOnClass(RedisOperations.class)
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig extends CachingConfigurerSupport {

    // 实例化具体的缓存配置类
    // 设置序列化方式为JSON
    // 设置缓存时间，单位为秒
    private RedisCacheConfiguration instanceConfig(Long ttl) {

        // 1.创建jackson的Redis缓存序列化器
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

//        // 2.常见的Jackson的对象映射器，并设置一些基本属性
//        // 2.1 创建Jackson的对象映射器对象
//        ObjectMapper objectMapper = new ObjectMapper();
//        // 2.2 在序列化过程中关闭把日期时间转换成时间戳
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        // 2.3 注册Java的时间模块
//        objectMapper.registerModule(new JavaTimeModule());
//        // 2.4 禁用映射器注解
//        objectMapper.configure(MapperFeature.USE_ANNOTATIONS, false); // 被弃用
//        // 2.5 设置JSON序列化器，且不为空时才序列化
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        // 2.6 不懂
//        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
//                ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);

        // 2.新版本Jackson推荐使用JsonMapper，与替换上面的老版本的ObjectMapper
        // JsonMapper继承了ObjectMapper
        JsonMapper jsonMapper = JsonMapper.builder()
                .configure(MapperFeature.USE_ANNOTATIONS, false)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .build();
        jsonMapper.registerModule(new JavaTimeModule());
        jsonMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        jsonMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);

        // 3.为序列化器设置对象映射器
        jackson2JsonRedisSerializer.setObjectMapper(jsonMapper);

        // 4.返回Redis缓存配置对象
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(ttl))  // 设置缓存时间
                .disableCachingNullValues()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer));
    }

    // 配置缓存Manager
    @Bean
    @Primary  //同类型多个bean时，默认生效! 默认缓存时间1小时!  可以选择!
    public RedisCacheManager cacheManagerHour(RedisConnectionFactory redisConnectionFactory) {

        RedisCacheConfiguration instanceConfig = instanceConfig(1 * 3600L); //缓存时间1小时

        //构建缓存对象
        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(instanceConfig)
                .transactionAware()
                .build();
    }

    //缓存一天配置
    @Bean
    public RedisCacheManager cacheManagerDay(RedisConnectionFactory redisConnectionFactory) {

        RedisCacheConfiguration instanceConfig = instanceConfig(24 * 3600L);    //缓存时间24小时

        //构建缓存对象
        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(instanceConfig)
                .transactionAware()
                .build();
    }
}
