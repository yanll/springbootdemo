package tk.techforge.springdemo.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfiguration {

    private Logger logger = LoggerFactory.getLogger(RedisConfiguration.class);


    @Value("${spring.redis.host:}")
    private String host;
    @Value("${spring.redis.port:6379}")
    private int port;
    @Value("${spring.redis.password:}")
    private String password;
    @Value("${spring.redis.timeout:2000}")
    private int timeout;
    @Value("${spring.redis.min-idle:2}")
    private int min_idle;
    @Value("${spring.redis.max-idle:10}")
    private int max_idle;
    @Value("${spring.redis.max-total:20}")
    private int max_total;
    @Value("${spring.redis.max-wait:2000}")
    private long max_wait;
    @Value("${spring.redis.test-on-borrow:true}")
    private boolean test_on_borrow;
    @Value("${spring.redis.test-on-return:true}")
    private boolean test_on_return;


    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(max_idle);
        jedisPoolConfig.setMinIdle(min_idle);
        jedisPoolConfig.setMaxTotal(max_total);
        jedisPoolConfig.setMaxWaitMillis(max_wait);
        jedisPoolConfig.setTestOnBorrow(test_on_borrow);
        jedisPoolConfig.setTestOnBorrow(test_on_return);
        return jedisPoolConfig;
    }

    @Bean
    public RedisStandaloneConfiguration redisStandaloneConfiguration() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(host, port);
        if (!StringUtils.isEmpty(password)) {
            redisStandaloneConfiguration.setPassword(password);
        }
        return redisStandaloneConfiguration;
    }


    @Bean
    public JedisConnectionFactory jedisConnectionFactory(@Autowired RedisStandaloneConfiguration redisStandaloneConfiguration, @Autowired JedisPoolConfig jedisPoolConfig) {
        JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder().usePooling().poolConfig(jedisPoolConfig).build();
        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
    }

    @Bean("redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setEnableTransactionSupport(false);
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

}
