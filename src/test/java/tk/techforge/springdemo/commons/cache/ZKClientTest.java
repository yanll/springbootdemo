package tk.techforge.springdemo.commons.cache;

import org.apache.zookeeper.CreateMode;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tk.Base;

/**
 * @author: YANLL
 * @version:
 * @since: 2020/7/15
 */
public class ZKClientTest extends Base {


    @Autowired
    ZKClient zkClient;

    @Test
    public void testA() {
        zkClient.crateNode("/node", CreateMode.EPHEMERAL, "234");
    }

}