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
            log.info("本地查询：{}，{}", key_, wrapper == null ? null : UtilJackson.toJSON(wrapper.get()));
        }
        if (cacheInstance.isRemote() && wrapper == null) {
            Object o = redisTemplate.opsForValue().get(key_);
            log.info("远程查询：{}，{}", key_, UtilJackson.toJSON(o));
            if (o == null) {
                log.info("远程为空：" + key_);
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
            log.info("本地存储：{}，{}", key_, UtilJackson.toJSON(value));
            super.put(key_, value);
        }
        if (cacheInstance.isRemote()) {
            log.info("远程存储：{}，{}", key_, UtilJackson.toJSON(value));
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
        if (true) {
            throw new UnsupportedOperationException("接口未实现！");
        }
        super.evict(key);
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
