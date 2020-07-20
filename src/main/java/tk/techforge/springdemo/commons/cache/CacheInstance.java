package tk.techforge.springdemo.commons.cache;

import lombok.Getter;

/**
 * @author: YANLL
 * @version:
 * @since: 2020/7/16
 */
@Getter
public enum CacheInstance {


    //首页缓存
    INDEX("/index/{id}", 5000, 5, 30, true, false),
    //用户缓存
    USER("/user/{id}", 5000, 60 * 60 * 2, 60 * 60 * 2, true, true),
    //组织机构有缓存
    ORGANIZATION("/organization/{id}", 5000, 60 * 60 * 2, 60 * 60 * 2, true, true);

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
