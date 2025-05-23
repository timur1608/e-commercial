package shopping.cartserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.convert.RedisCustomConversions;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import shopping.cartserver.model.User;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
public class RedisConfiguration {
    @Bean
    public RedisConnectionFactory lettuceConnectionFactory() {
        return new LettuceConnectionFactory();
    }
    @Bean
    public RedisTemplate<Object, Object> redisTemplate() {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(lettuceConnectionFactory());
        return redisTemplate;
    }

    @Bean
    public RedisCustomConversions redisCustomConversions() {
        Converter<User, byte[]> toBytes = new Converter<>() {
            @Override
            public byte[] convert(User user) {
                return user.getUserId().getBytes(StandardCharsets.UTF_8);
            }
        };
        Converter<User, String> toString = new Converter<>() {
            @Override
            public String convert(User user) {
                return user.getUserId();
            }
        };
        return new RedisCustomConversions(List.of(toBytes, toString));
    }
}