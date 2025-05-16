package shopping.productservice.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.util.Map;

@EnableCaching
@Configuration
public class RedisConfig {
    @Bean
    public RedisConnectionFactory lettuceConnectionFactory() {
        return new LettuceConnectionFactory();
    }
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory());
        return redisTemplate;
    }
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10));
        return RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(redisCacheConfiguration)
                .withInitialCacheConfigurations(Map.of("categories", redisCacheConfiguration.entryTtl(Duration.ofMinutes(20)),
                                                        "productsByCategory", redisCacheConfiguration.entryTtl(Duration.ofMinutes(20)),
                        "categoryById", redisCacheConfiguration.entryTtl(Duration.ofMinutes(20)),
                        "products", redisCacheConfiguration.entryTtl(Duration.ofMinutes(20)),
                        "productsByIds", redisCacheConfiguration.entryTtl(Duration.ofMinutes(20))))
                .build();
    }
}
