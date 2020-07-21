package tk.techforge.springdemo.commons.cache;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.redis.core.RedisTemplate;
import tk.techforge.springdemo.commons.cache.CacheKey.CacheInstance;
import tk.techforge.springdemo.utils.UtilJackson;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author: YANLL
 * @version:
 * @since: 2020/7/16
 */
@Slf4j
public class MultiplyCache extends CaffeineCache {

    private static final String NAMESPACE = "/caffeine-test";

    private RedisTemplate redisTemplate;

    private CacheInstance cacheInstance;


    public MultiplyCache(RedisTemplate redisTemplate, CacheInstance cacheInstance, Cache<Object, Object> cache) {
        super(cacheInstance.getCacheName(), cache);
        this.redisTemplate = redisTemplate;
        this.cacheInstance = cacheInstance;
    }

    @Override
    public ValueWrapper get(Object key) {
        String key_ = NAMESPACE + cacheInstance.getCacheName() + key;
        ValueWrapper wrapper = null;
        if (cacheInstance.isLocal()) {
            wrapper = super.get(key_);
            log.info("MultiplyCache 本地查询：{}", key_);
        }
        if (cacheInstance.isRemote() && wrapper == null) {
            Object o = redisTemplate.opsForValue().get(key_);
            log.info("MultiplyCache 远程查询：{}", key_);
            if (o == null) {
                log.info("MultiplyCache 远程为空：" + key_);
                return null;
            } else {
                if (cacheInstance.isLocal()) {
                    super.put(key_, o);
                }
                return new SimpleValueWrapper(o);
            }
        }
        return wrapper;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        if (true) {
            throw new UnsupportedOperationException("接口未实现！");
        }
        return super.get(key, valueLoader);
    }

    @Override
    protected Object lookup(Object key) {
        if (false) {
            throw new UnsupportedOperationException("接口未实现！");
        }
        return super.lookup(key);
    }

    @Override
    public void put(Object key, Object value) {
        String key_ = NAMESPACE + cacheInstance.getCacheName() + key;
        if (cacheInstance.isLocal()) {
            log.info("MultiplyCache 本地存储：{}", key_);
            super.put(key_, value);
        }
        if (cacheInstance.isRemote()) {
            log.info("MultiplyCache 远程存储：{}", key_);
            redisTemplate.opsForValue().set(key_, value, cacheInstance.getRemoteExpire(), TimeUnit.SECONDS);
        }
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        if (true) {
            throw new UnsupportedOperationException("接口未实现！");
        }
        return super.putIfAbsent(key, value);
    }

    @Override
    public void evict(Object key) {
        String key_ = NAMESPACE + cacheInstance.getCacheName() + key;
        if (cacheInstance.isLocal()) {
            log.info("MultiplyCache 本地清除：{}", key_);
            super.evict(key);
        }
        if (cacheInstance.isRemote()) {
            log.info("MultiplyCache 远程清除：{}", key_);
            redisTemplate.delete(key_);
        }
    }

    @Override
    public boolean evictIfPresent(Object key) {
        if (true) {
            throw new UnsupportedOperationException("接口未实现！");
        }
        return super.evictIfPresent(key);
    }

    @Override
    public void clear() {
        if (true) {
            throw new UnsupportedOperationException("接口未实现！");
        }
        super.clear();
    }

    @Override
    public boolean invalidate() {
        if (true) {
            throw new UnsupportedOperationException("接口未实现！");
        }
        return super.invalidate();
    }
}
