package tk.techforge.springdemo.commons.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author: YANLL
 * @version:
 * @since: 2020/7/6
 */
@Slf4j
public class ZkClient {
    /**
     * zk客户端
     */
    private CuratorFramework cf;

    private String appPath;

    ZkClient(String path) {
        ZkConfig zkConfig = loadConfig(path);
        init(zkConfig);
    }

    public ZkClient(ZkConfig config) {
        init(config);
    }

    /**
     * zk初始化方法
     */
    private void init(ZkConfig zkConfig) {
        log.info("ZkClientUtil init start...");
        cf = CuratorFrameworkFactory.builder()
                .connectString(zkConfig.getConnectString())
                .sessionTimeoutMs(zkConfig.getSessionTimeoutMs())
                .connectionTimeoutMs(zkConfig.getConnectionTimeoutMs())
                .retryPolicy(new ExponentialBackoffRetry(
                        zkConfig.getBaseSleepTimeMs(),
                        zkConfig.getMaxRetries()))
                .namespace(zkConfig.getNamespace())
                .build();
        cf.start();
        appPath = zkConfig.getAppPath();
        log.info("ZkClientUtil init complete...");
    }

    /**
     * 设置watcher
     *
     * @param group          分组
     * @param key            key
     * @param curatorWatcher watcher回调
     */
    void setWatcher(String group, String key, CuratorWatcher curatorWatcher) {

        String path = PathUtil.getPath(appPath, group, key);
        try {
            Stat stat = cf.checkExists().forPath(path);
            if (stat == null) {
                cf.create().creatingParentsIfNeeded().forPath(path);
            }
            cf.checkExists().usingWatcher(curatorWatcher).forPath(path);
        } catch (Exception e) {
            log.error("set watcher fail, path:" + path + ":" + e.getMessage());
        }
    }

    /**
     * 删除节点
     *
     * @param group 分组
     * @param key   key
     */
    void delete(String group, String key) {
        String path = PathUtil.getPath(appPath, group, key);
        try {
            Stat stat = cf.checkExists().forPath(path);
            if (stat != null) {
                cf.delete().guaranteed().forPath(path);
            }

        } catch (Exception e) {
            log.error("delete zNode fail, path:" + path + ":" + e.getMessage());
        }
    }

    private ZkConfig loadConfig(String path) {
        log.info("ZkClient start load properties start :" + path);
        Properties properties = new Properties();
        // 使用ClassLoader加载properties配置文件生成对应的输入流
        InputStream in = ZkClient.class.getClassLoader().getResourceAsStream(path);
        // 使用properties对象加载输入流
        try {
            properties.load(in);
            log.info("ZkClient load properties finish :" + path);
        } catch (IOException e) {
            throw new RuntimeException("ZkClient load properties fail :" + path, e);
        }
        ZkConfig zkConfig = new ZkConfig();
        zkConfig.setConnectString(properties.getProperty("connectString"));
        zkConfig.setSessionTimeoutMs(properties.getProperty("sessionTimeoutMs"));
        zkConfig.setConnectionTimeoutMs(properties.getProperty("connectionTimeoutMs"));
        zkConfig.setBaseSleepTimeMs(properties.getProperty("baseSleepTimeMs"));
        zkConfig.setMaxRetries(properties.getProperty("maxRetries"));
        zkConfig.setAppPath(properties.getProperty("appPath"));
        zkConfig.setNamespace(properties.getProperty("namespace"));
        return zkConfig;
    }

}
