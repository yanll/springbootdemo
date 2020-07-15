package tk.techforge.springdemo.configuration;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.techforge.springdemo.commons.cache.ZKClient;

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


    @Bean(name = "curatorFramework")
    public CuratorFramework curatorFramework() {
        log.info("Init CuratorFramework");
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(2000, 5);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(address)
                .retryPolicy(retryPolicy)
                .namespace(namespace)
                .connectionTimeoutMs(connectionTimeout)
                .sessionTimeoutMs(sessionTimeout)
                .build();
        client.start();
        return client;
    }

    @Bean(name = "zkClient")
    public ZKClient zkClient(@Autowired CuratorFramework curatorFramework) {
        log.info("Init ZKClient");
        return new ZKClient(curatorFramework);
    }


}
