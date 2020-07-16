package tk.techforge.springdemo.configuration;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.techforge.springdemo.commons.cache.ZKClient;

import java.io.UnsupportedEncodingException;

/**
 * @author: YANLL
 * @version:
 * @since: 2020/7/15
 */
@Configuration
@Slf4j
public class ZKConfiguration {


    @Value("${zookeeper.address:}")
    private String address;

    @Value("${zookeeper.namespace:}")
    private String namespace;

    @Value("${zookeeper.connection-timeout:30000}")
    private int connectionTimeout;

    @Value("${zookeeper.session-timeout:30000}")
    private int sessionTimeout;


    @Bean(name = "connectionStateListener")
    public ConnectionStateListener connectionStateListener() {
        return new ConnectionStateListener() {
            @Override
            public void stateChanged(CuratorFramework client, ConnectionState newState) {
                log.info("ssssssssssssssssssssss   " + newState);
                if (newState == ConnectionState.LOST) {
                    log.info("链接丢失！");
                    while (true) {
                        ZKClient.init(address, namespace, connectionTimeout, sessionTimeout);
                    }
                }
            }
        };
    }

    @Bean(name = "treeCacheListener")
    public TreeCacheListener treeCacheListener() {
        return new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) {
                try {
                    ChildData data = event.getData();
                    if (data == null) {
                        log.info("数据为空");
                        return;
                    }
                    switch (event.getType()) {
                        case NODE_ADDED:
                            log.info("[TreeCache]节点增加, path={}, data={}", data.getPath(), new String(data.getData(), "UTF-8"));
                            break;
                        case NODE_UPDATED:
                            log.info("[TreeCache]节点更新, path={}, data={}", data.getPath(), new String(data.getData(), "UTF-8"));
                            break;
                        case NODE_REMOVED:
                            log.info("[TreeCache]节点删除, path={}, data={}", data.getPath(), new String(data.getData(), "UTF-8"));
                            break;
                        default:
                            break;
                    }
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException("监听器创建失败！", e);
                }
            }
        };
    }

    @Bean
    public ZKClient zkClient(@Autowired ConnectionStateListener connectionStateListener, TreeCacheListener treeCacheListener) {
        ZKClient.init(address, namespace, connectionTimeout, sessionTimeout);
        ZKClient.addConnectionStateListener(connectionStateListener);
        ZKClient.registerWatcherTreeChanged("/node", treeCacheListener);
        return new ZKClient();
    }


}
