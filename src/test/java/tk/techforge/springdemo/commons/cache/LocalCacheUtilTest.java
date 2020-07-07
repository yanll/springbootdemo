//package tk.techforge.springdemo.commons.cache;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * @author: YANLL
// * @version:
// * @since: 2020/7/6
// */
//public class LocalCacheUtilTest {
//    //模拟数据库
//    private static final Map<String, Map<String, String>> db = new ConcurrentHashMap<>();
//
//    //模拟数据库数据
//    static {
//        Map<String, String> map = new HashMap();
//        map.put("uId", "1001");
//        map.put("name", "name_" + "1001");
//        db.put("1001", map);
//    }
//
//
//    public static void main(String[] args) throws InterruptedException {
//        //设置配置
//        ZkConfig cacheZkConfig = new ZkConfig();
//        cacheZkConfig.setConnectString("localhost:2181");
//        //初始化zk
//        ZkClientUtil.init(cacheZkConfig);
//
//        //1.测试读取缓存
//        for (int i = 0; i <= 1; i++) {
//            System.out.println(findUser("1001"));
//        }
//        //2.测试缓存失效
////        Thread.sleep(60000);
//        System.out.println("--------------");
//        System.out.println("过期后查询：" + findUser("1001"));
//        //3.测试缓存移除
//        updateUser("1001", "name_2");
//        System.out.println("更新后查询：" + findUser("1001"));
//        //4.测试缓存移除
//        deleteUser("1001");
//        System.out.println("删除后查询：" + findUser("1001"));
//    }
//
//    /**
//     * 读取缓存测试
//     *
//     * @param uId
//     * @return
//     */
//    private static Map<String, String> findUser(String uId) {
//        String group = "user";
//        Object o = LocalCacheUtil.get(group, uId);
//        if (o != null) {
//            System.out.println("------->缓存命中，key：" + uId);
//            return (Map<String, String>) o;
//        }
//        System.out.println("------->缓存未命中，key：" + uId);
//        Map<String, String> user = db.get(uId);
//        if (user != null) {
//            System.out.println("------->放缓存，key：" + uId);
//            LocalCacheUtil.put(group, uId, user, 1L);
//        }
//        return user;
//    }
//
//    private static void updateUser(String uId, String name) {
//        String group = "user";
//        Map<String, String> user = findUser(uId);
//        if (user == null) {
//            throw new RuntimeException("user not exist,uId:" + uId);
//        }
//        System.out.println("------->删除缓存，key：" + uId);
//        LocalCacheUtil.remove(group, uId);
//        user.put("name", name);
//        db.put(uId, user);
//        //如果担心此期间其他请求刷新缓存，可以在db修改后再remove一次缓存（缓存双淘汰）
//    }
//
//    private static void deleteUser(String uId) {
//        String group = "user";
//        Map<String, String> user = findUser(uId);
//        if (user == null) {
//            //不存在直接认为成功
//            return;
//        }
//        System.out.println("------->删除缓存，key：" + uId);
//        LocalCacheUtil.remove(group, uId);
//        db.remove(uId);
//        //如果担心此期间其他请求刷新缓存，可以在db删除后再remove一次缓存（缓存双淘汰）
//    }
//}