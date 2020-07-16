package tk.techforge.springdemo.commons.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * @author: YANLL
 * @version:
 * @since: 2020/7/15
 */
@Slf4j
public class ZKClient {


    public static CuratorFramework client;


    public synchronized static void init(String address, String namespace, int connectionTimeout, int sessionTimeout) {
        log.info("Init CuratorFramework");
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(2000, 5);
        client = CuratorFrameworkFactory.builder()
                .connectString(address)
                .retryPolicy(retryPolicy)
                .namespace(namespace)
                .connectionTimeoutMs(connectionTimeout)
                .sessionTimeoutMs(sessionTimeout)
                .build();
        client.start();
    }


    public static void addConnectionStateListener(ConnectionStateListener connectionStateListener) {
        client.getConnectionStateListenable().addListener(connectionStateListener);
    }

    /**
     * 创建节点
     *
     * @param path       路径
     * @param createMode 节点类型
     * @param data       节点数据
     * @return 是否创建成功
     */
    public static boolean crateNode(String path, CreateMode createMode, String data) {
        try {
            client.create().withMode(createMode).forPath(path, data.getBytes());
        } catch (Exception e) {
            log.error("节点创建失败！", e);
            return false;
        }
        return true;
    }

    public static boolean creatingParentsIfNeeded(String path, CreateMode createMode, String data) {
        try {
            client.create().creatingParentsIfNeeded().withMode(createMode).forPath(path, data.getBytes());
        } catch (Exception e) {
            log.error("节点创建失败！", e);
            return false;
        }
        return true;
    }

    /**
     * 删除节点
     *
     * @param path 路径
     * @return 删除结果
     */
    public static boolean deleteNode(String path) {
        try {
            client.delete().forPath(path);
        } catch (Exception e) {
            log.error("节点删除失败！", e);
            return false;
        }
        return true;
    }

    /**
     * 删除一个节点，并且递归删除其所有的子节点
     *
     * @param path 路径
     * @return 删除结果
     */
    public static boolean deleteChildrenIfNeededNode(String path) {
        try {
            client.delete().deletingChildrenIfNeeded().forPath(path);
        } catch (Exception e) {
            log.error("节点递归删除失败！", e);
            return false;
        }
        return true;
    }

    /**
     * 判断节点是否存在
     *
     * @param path 路径
     * @return 是否存在
     */
    public static boolean isExistNode(String path) {
        try {
            Stat stat = client.checkExists().forPath(path);
            return stat != null;
        } catch (Exception e) {
            log.error("节点是否存在判断失败！", e);
            return false;
        }
    }

    /**
     * 判断节点是否是持久化节点
     *
     * @param path 路径
     * @return true:持久化，false:临时节点，null:节点不存在
     */
    public static Boolean isPersistentNode(String path) {
        try {
            Stat stat = client.checkExists().forPath(path);
            if (stat == null) {
                return null;
            }
            return stat.getEphemeralOwner() > 0;
        } catch (Exception e) {
            log.error("节点是否是持久化判断失败！", e);
            return null;
        }
    }

    /**
     * 获取节点数据
     *
     * @param path 路径
     * @return 节点数据
     */
    public static String getNodeData(String path) {

        try {
            byte[] bytes = client.getData().forPath(path);
            return new String(bytes);
        } catch (Exception e) {
            log.error("节点数据获取失败！", e);
            return null;
        }
    }


    /**
     * 更新节点数据
     *
     * @param path 路径
     * @param data 数据
     * @return 更新结果
     */
    public static boolean updateNodeData(String path, String data) {
        //判断节点是否存在
        if (!isExistNode(path)) {
            return false;
        }
        try {
            client.setData().forPath(path, data.getBytes());
        } catch (Exception e) {
            log.error("节点数据更新失败！", e);
            return false;
        }
        return true;
    }


    /**
     * 注册节点数据变化事件
     *
     * @param path              节点路径
     * @param nodeCacheListener 监听事件
     * @return 注册结果
     */
    public static boolean registerWatcherNodeChanged(String path, NodeCacheListener nodeCacheListener) {
        NodeCache nodeCache = new NodeCache(client, path, false);
        try {
            nodeCache.getListenable().addListener(nodeCacheListener);
            nodeCache.start(true);
        } catch (Exception e) {
            log.error("节点数据变化事件注册失败！", e);
            return false;
        }
        return true;
    }

    public static boolean registerWatcherTreeChanged(String path, TreeCacheListener treeCacheListener) {
        TreeCache treeCache = new TreeCache(client, path);
        try {
            treeCache.getListenable().addListener(treeCacheListener);
            treeCache.start();
        } catch (Exception e) {
            log.error("节点数据变化事件注册失败！", e);
            return false;
        }
        return true;
    }


}
