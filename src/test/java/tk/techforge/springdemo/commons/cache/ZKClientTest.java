package tk.techforge.springdemo.commons.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;
import tk.Base;

/**
 * @author: YANLL
 * @version:
 * @since: 2020/7/15
 */
@Slf4j
public class ZKClientTest extends Base {


    @Test
    public void testC() throws Exception {
        String path = "/node/000";
        boolean rs = ZKClient.creatingParentsIfNeeded(path, CreateMode.EPHEMERAL, "123456");
        log.info("执行结果：" + rs);
        rs = ZKClient.updateNodeData(path, "000000");
        log.info("执行结果：" + rs);
        Thread.sleep(60000);
    }


}