package tk.techforge.springdemo.commons.cache;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StopWatch;
import tk.techforge.springdemo.commons.cache.CacheKey.CacheInstance;
import tk.techforge.springdemo.utils.StopWatchPrinter;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author: YANLL
 * @version:
 * @since: 2020/7/16
 */
@Slf4j
public class MultiplyCache extends CaffeineCache {


    private RedisTemplate redisTemplate;

    private CacheInstance cacheInstance;


    public MultiplyCache(RedisTemplate redisTemplate, CacheInstance cacheInstance, Cache<Object, Object> cache) {
        super(cacheInstance.getCacheName(), cache);
        this.redisTemplate = redisTemplate;
        this.cacheInstance = cacheInstance;
    }

    @Override
    public ValueWrapper get(Object key) {
        StopWatch clock = new StopWatch();
        String key_ = cacheInstance.getCacheName() + key;
        ValueWrapper wrapper = null;
        if (cacheInstance.isLocal()) {
            clock.start("MultiplyCache_Local_Get:" + key_);
            wrapper = super.get(key_);
            clock.stop();
            StopWatchPrinter.printLastTask(clock);
        }
        if (cacheInstance.isRemote() && wrapper == null) {
            clock.start("MultiplyCache_Remote_Get:" + key_);
            Object o = redisTemplate.opsForValue().get(key_);
            clock.stop();
            StopWatchPrinter.printLastTask(clock);
            if (o == null) {
                return null;
            } else {
                if (cacheInstance.isLocal()) {
                    clock.start("MultiplyCache_Local_Put:" + key_);
                    super.put(key_, o);
                    clock.stop();
                    StopWatchPrinter.printLastTask(clock);
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
        StopWatch clock = new StopWatch();
        String key_ = cacheInstance.getCacheName() + key;
        if (cacheInstance.isLocal()) {
            clock.start("MultiplyCache_Local_Put:" + key_);
            super.put(key_, value);
            clock.stop();
        }
        if (cacheInstance.isRemote()) {
            clock.start("MultiplyCache_Remote_Put:" + key_);
            redisTemplate.opsForValue().set(key_, value, cacheInstance.getRemoteExpire(), TimeUnit.SECONDS);
            clock.stop();
        }
        StopWatchPrinter.print(clock);
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
        String key_ = cacheInstance.getCacheName() + key;
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
