package tk.techforge.springdemo.commons.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: YANLL
 * @version:
 * @since: 2020/7/15
 */
@Slf4j
public class ZKClient {

    @Autowired
    private CuratorFramework curatorFramework;
    @Autowired
    private TreeCacheListener treeCacheListener;


    public ZKClient() {

    }

    /**
     * 创建节点
     *
     * @param path       路径
     * @param createMode 节点类型
     * @param data       节点数据
     * @return 是否创建成功
     */
    public boolean crateNode(String path, CreateMode createMode, String data) {
        try {
            curatorFramework.create().withMode(createMode).forPath(path, data.getBytes());
        } catch (Exception e) {
            log.error("节点创建失败！", e);
            return false;
        }
        return true;
    }

    public void close() {
        curatorFramework.close();
    }

    /**
     * 删除节点
     *
     * @param path 路径
     * @return 删除结果
     */
    public boolean deleteNode(String path) {
        try {
            curatorFramework.delete().forPath(path);
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
    public boolean deleteChildrenIfNeededNode(String path) {
        try {
            curatorFramework.delete().deletingChildrenIfNeeded().forPath(path);
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
    public boolean isExistNode(String path) {
        try {
            Stat stat = curatorFramework.checkExists().forPath(path);
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
    public Boolean isPersistentNode(String path) {
        try {
            Stat stat = curatorFramework.checkExists().forPath(path);
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
    public String getNodeData(String path) {

        try {
            byte[] bytes = curatorFramework.getData().forPath(path);
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
    public boolean updateNodeData(String path, String data) {
        //判断节点是否存在
        if (!isExistNode(path)) {
            return false;
        }
        try {
            curatorFramework.setData().forPath(path, data.getBytes());
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
    public boolean registerWatcherNodeChanged(String path, NodeCacheListener nodeCacheListener) {
        NodeCache nodeCache = new NodeCache(curatorFramework, path, false);
        try {
            nodeCache.getListenable().addListener(nodeCacheListener);
            nodeCache.start(true);
        } catch (Exception e) {
            log.error("节点数据变化事件注册失败！", e);
            return false;
        }
        return true;
    }

    public boolean registerWatcherTreeChanged(String path) {
        TreeCache treeCache = new TreeCache(curatorFramework, path);
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
