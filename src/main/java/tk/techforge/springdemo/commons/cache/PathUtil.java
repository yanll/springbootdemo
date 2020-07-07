package tk.techforge.springdemo.commons.cache;

/**
 * @author: YANLL
 * @version:
 * @since: 2020/7/6
 */
public class PathUtil {
    private static final String PATH_SPLIT_CHAR = "/";

    private static final String PATH_SPLIT_CHAR1 = "\\";

    static String getPath(String... strs) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : strs) {
            stringBuilder.append(PATH_SPLIT_CHAR).append(str.trim());
        }
        return stringBuilder.toString();
    }

    static String[] splitPath(String path) {
        path = path.substring(1);
        return path.split(PATH_SPLIT_CHAR);
    }

    static void check(String src, String name) {
        if (src == null || "".equals(src.trim())) {
            throw new RuntimeException(name + " not empty!");
        }
        //判断“/”,"\"，因为zk是路径
        if (src.contains(PATH_SPLIT_CHAR) || src.contains(PATH_SPLIT_CHAR1)) {
            throw new RuntimeException(name + " can not contains \"/\" and \"\\\"!");
        }
    }
}
