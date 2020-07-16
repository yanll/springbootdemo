package tk.techforge.springdemo.configuration;


import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import tk.techforge.springdemo.commons.cache.CacheInstance;
import tk.techforge.springdemo.commons.cache.MultiplyCache;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: YANLL
 * @version:
 * @since: 2020/7/16
 */
@EnableCaching
@Configuration
public class MultiplyCacheConfiguration {

    @Autowired
    RedisTemplate redisTemplate;


    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<Cache> caches = new ArrayList<>();
        for (CacheInstance cacheInstance : CacheInstance.values()) {
            caches.add(new MultiplyCache(redisTemplate, cacheInstance,
                    Caffeine.newBuilder()
                            .maximumSize(cacheInstance.getMaximumSize())
                            .expireAfterWrite(cacheInstance.getExpireAfterWrite(), TimeUnit.SECONDS)
                            .recordStats()
                            .build()));
        }
        cacheManager.setCaches(caches);
        return cacheManager;
    }
}
