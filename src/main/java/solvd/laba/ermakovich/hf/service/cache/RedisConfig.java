package solvd.laba.ermakovich.hf.service.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.*;
import solvd.laba.ermakovich.hf.domain.Transactions;

/**
 * @author Ermakovich Kseniya
 */
@Configuration
public class RedisConfig {

    @Bean
    public ReactiveRedisOperations<String, Transactions> template(LettuceConnectionFactory connectionFactory) {
        final RedisSerializationContext<String, Transactions> serializationContext = RedisSerializationContext
                .<String, Transactions>newSerializationContext(new StringRedisSerializer())
                .key(new StringRedisSerializer())
                .value(new GenericToStringSerializer<>(Transactions.class))
                .hashKey(new Jackson2JsonRedisSerializer<>(String.class))
                .hashValue(new GenericJackson2JsonRedisSerializer())
                .build();
        return new ReactiveRedisTemplate<>(connectionFactory, serializationContext);
    }

}
