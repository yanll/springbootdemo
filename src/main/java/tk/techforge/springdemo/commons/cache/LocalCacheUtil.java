//package tk.techforge.springdemo.commons.cache;
//
//import org.springframework.cache.Cache;
//
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * @author: YANLL
// * @version:
// * @since: 2020/7/6
// */
//public class LocalCacheUtil {
//    private static Map<String, Cache> cacheMap = new ConcurrentHashMap<>();
//
//    private static ZkClient zkClient = new ZkClient("zk.properties");
//
//    /**
//     * 获取缓存内容
//     *
//     * @param group 分组
//     * @param key   key
//     * @return 缓存对象
//     */
//    public static Object get(String group, String key) {
//        PathUtil.check(group, "group");
//        PathUtil.check(key, "key");
//        Cache cache = cacheMap.get(group);
//        if (cache == null) {
//            return null;
//        }
//        return cache.getIfPresent(key);
//    }
//
//    /**
//     * 写缓存
//     *
//     * @param group  分组
//     * @param key    key
//     * @param val    值
//     * @param expire 过期时间，不支持动态传入
//     */
//    public static void put(String group, String key, Object val, Long expire) {
//        PathUtil.check(group, "group");
//        PathUtil.check(key, "key");
//        Cache cache = getCache(group, expire);
//        cache.put(key, val);
//        zkClient.setWatcher(group, key, LocalCacheWatcher.getInstance());
//    }
//
//    /**
//     * 删除缓存
//     *
//     * @param group 分组
//     * @param key   key
//     */
//    public static void remove(String group, String key) {
//        PathUtil.check(group, "group");
//        PathUtil.check(key, "key");
//        Cache cache = cacheMap.get(group);
//        if (cache != null) {
//            cache.invalidate(key);
//        }
//        zkClient.delete(group, key);
//    }
//
//    static void removeLocal(String group, String key) {
//        PathUtil.check(group, "group");
//        PathUtil.check(key, "key");
//        Cache cache = cacheMap.get(group);
//        if (cache != null) {
//            cache.invalidate(key);
//        }
//    }
//
//    static void removeAll() {
//        for (String group : cacheMap.keySet()) {
//            Cache cache = cacheMap.get(group);
//            if (cache != null) {
//                cache.invalidateAll();
//            }
//        }
//    }
//
//    /**
//     * 获取cache
//     *
//     * @param group  分组
//     * @param expire 超时时间
//     * @return cache
//     */
//    private static Cache getCache(String group, Long expire) {
//        Cache cache = cacheMap.get(group);
//        if (cache == null) {
//            cache = CacheBuilder.newBuilder()
//                    .maximumSize(2000)
//                    .expireAfterWrite(expire, TimeUnit.MINUTES)
//                    .build();
//            cacheMap.put(group, cache);
//        }
//        return cache;
//    }
//}
