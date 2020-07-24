package tk.techforge.springdemo.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

/**
 * @author: YANLL
 * @version:
 * @since: 2020/7/24
 */
@Slf4j
public class StopWatchPrinter {

    public static void printLastTask(StopWatch watch) {
        long time = watch.getLastTaskTimeMillis();
        if (time > 30 * 1000) {
            log.info("ExecuteTime_IIIIIIII:{},{} ms", watch.getLastTaskName(), time);
            return;
        }
        if (time > 10 * 1000) {
            log.info("ExecuteTime_IIIIIII:{},{} ms", watch.getLastTaskName(), time);
            return;
        }
        if (time > 5 * 1000) {
            log.info("ExecuteTime_IIIIII:{},{} ms", watch.getLastTaskName(), time);
            return;
        }
        if (time > 2 * 1000) {
            log.info("ExecuteTime_IIIII:{},{} ms", watch.getLastTaskName(), time);
            return;
        }
        if (time > 1000) {
            log.info("ExecuteTime_IIII:{},{} ms", watch.getLastTaskName(), time);
            return;
        }
        if (time > 500) {
            log.info("ExecuteTime_III:{},{} ms", watch.getLastTaskName(), time);
            return;
        }
        if (time > 200) {
            log.info("ExecuteTime_II:{},{} ms", watch.getLastTaskName(), time);
            return;
        }
        if (time > 100) {
            log.info("ExecuteTime_I:{},{} ms", watch.getLastTaskName(), time);
            return;
        }
        if (time > 50) {
            log.info("ExecuteTime:{},{} ms", watch.getLastTaskName(), time);
            return;
        }
    }

    public static void print(StopWatch watch) {
        StopWatch.TaskInfo taskInfos[] = watch.getTaskInfo();
        for (StopWatch.TaskInfo taskInfo : taskInfos) {
            long time = taskInfo.getTimeMillis();
            if (time > 30 * 1000) {
                log.info("ExecuteTime_IIIIIIII:{},{} ms", taskInfo.getTaskName(), time);
                return;
            }
            if (time > 10 * 1000) {
                log.info("ExecuteTime_IIIIIII:{},{} ms", taskInfo.getTaskName(), time);
                return;
            }
            if (time > 5 * 1000) {
                log.info("ExecuteTime_IIIIII:{},{} ms", taskInfo.getTaskName(), time);
                return;
            }
            if (time > 2 * 1000) {
                log.info("ExecuteTime_IIIII:{},{} ms", taskInfo.getTaskName(), time);
                return;
            }
            if (time > 1000) {
                log.info("ExecuteTime_IIII:{},{} ms", taskInfo.getTaskName(), time);
                return;
            }
            if (time > 500) {
                log.info("ExecuteTime_III:{},{} ms", taskInfo.getTaskName(), time);
                return;
            }
            if (time > 200) {
                log.info("ExecuteTime_II:{},{} ms", taskInfo.getTaskName(), time);
                return;
            }
            if (time > 100) {
                log.info("ExecuteTime_I:{},{} ms", taskInfo.getTaskName(), time);
                return;
            }
            if (time > 50) {
                log.info("ExecuteTime:{},{} ms", taskInfo.getTaskName(), time);
                return;
            }
        }
    }

}
