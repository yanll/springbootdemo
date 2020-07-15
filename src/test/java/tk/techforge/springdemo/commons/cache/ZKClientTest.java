package tk.techforge.springdemo.commons.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tk.Base;

/**
 * @author: YANLL
 * @version:
 * @since: 2020/7/15
 */
@Slf4j
public class ZKClientTest extends Base {


    @Autowired
    ZKClient zkClient;

    @Test
    public void testA() throws Exception {
        zkClient.registerWatcherTreeChanged("/node");
        boolean rs = zkClient.crateNode("/node", CreateMode.EPHEMERAL, "123456");
        log.info("执行结果：" + rs);
        Thread.sleep(20000);
        zkClient.close();
    }

}