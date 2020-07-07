//package tk.techforge.springdemo.commons.cache;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.curator.framework.api.CuratorWatcher;
//import org.apache.zookeeper.WatchedEvent;
//import org.apache.zookeeper.Watcher;
//
///**
// * @author: YANLL
// * @version:
// * @since: 2020/7/6
// */
//@Slf4j
//public class LocalCacheWatcher implements CuratorWatcher {
//
//    static LocalCacheWatcher getInstance() {
//        return LocalCacheWatcherInstance.LOCAL_CACHE_WATCHER;
//    }
//
//    @Override
//    public void process(WatchedEvent event) {
//        log.info("收到监听事件：" + event.toString());
//        //空事件不处理，只做监控
//        if (Watcher.Event.EventType.None.equals(event.getType())) {
//            if (Watcher.Event.KeeperState.Expired.equals(event.getState())) {
//                log.info("清理全部缓存...");
//                LocalCacheUtil.removeAll();
//            }
//            return;
//        }
//        String path = event.getPath();
//        String[] pathSplit = PathUtil.splitPath(path);
//        log.info("GROUP = " + pathSplit[1] + ",KEY = " + pathSplit[2]);
//        LocalCacheUtil.removeLocal(pathSplit[1], pathSplit[2]);
//    }
//
//    private static class LocalCacheWatcherInstance {
//        private static final LocalCacheWatcher LOCAL_CACHE_WATCHER = new LocalCacheWatcher();
//    }
//
//}
