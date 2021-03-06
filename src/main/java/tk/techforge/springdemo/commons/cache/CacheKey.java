package tk.techforge.springdemo.commons.cache;

import lombok.Getter;

/**
 * @author: YANLL
 * @version:
 * @since: 2020/7/16
 */
public class CacheKey {

    private static final String NAMESPACE = "/caffeine-test";

    public class Key {
        public static final String INDEX = NAMESPACE + "/index/{id}";
        public static final String USER = NAMESPACE + "/user/{id}";
        public static final String ORGANIZATION = NAMESPACE + "/organization/{id}";
    }

    @Getter
    public enum CacheInstance {
        //首页缓存
        INDEX(Key.INDEX, 5000, 5, 60, true, true),
        //用户缓存
        USER(Key.USER, 5000, 60 * 60 * 2, 60 * 60 * 2, true, true),
        //组织机构有缓存
        ORGANIZATION(Key.ORGANIZATION, 5000, 60 * 60 * 2, 60 * 60 * 2, true, true);

        private String cacheName;

        /**
         * 本地缓存的最大条数
         */
        private int maximumSize;
        /**
         * 本地最后一次写入后经过固定时间过期（单位：秒）
         */
        private long expireAfterWrite;

        /**
         * 远程缓存过期时间（单位：秒）
         */
        private long remoteExpire;

        /**
         * 是否远程缓存
         */
        private boolean remote;
        /**
         * 是否本地缓存
         */
        private boolean local;

        CacheInstance(String cacheName, int maximumSize, long expireAfterWrite, long remoteExpire, boolean remote, boolean local) {
            this.cacheName = cacheName;
            this.maximumSize = maximumSize;
            this.expireAfterWrite = expireAfterWrite;
            this.remoteExpire = remoteExpire;
            this.remote = remote;
            this.local = local;
        }
    }
}
