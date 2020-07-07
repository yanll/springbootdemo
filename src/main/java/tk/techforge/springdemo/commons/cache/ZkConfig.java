package tk.techforge.springdemo.commons.cache;

/**
 * @author: YANLL
 * @version:
 * @since: 2020/7/6
 */
public class ZkConfig {
    /**
     * 连接地址，多个地址用“,”间隔
     * 例如：localhost:2181,localhost1:2181
     */
    private String connectString;
    /**
     * session超时时间，单位ms
     */
    private int sessionTimeoutMs = 5000;
    /**
     * 连接超时时间，单位ms
     */
    private int connectionTimeoutMs = 5000;
    /**
     * 重试机制，间隔时间
     */
    private int baseSleepTimeMs = 100;
    /**
     * 重试机制，重试次数
     */
    private int maxRetries = 5;
    /**
     * 应用路径，用于隔离znood路径
     */
    private String appPath = "defaultAppPath";
    /**
     * namespace，用于区分zk用途
     */
    private String namespace;

    public String getConnectString() {
        return connectString;
    }

    public void setConnectString(String connectString) {
        if (connectString == null || "".equals(connectString.trim())) {
            throw new RuntimeException("load properties fail，connectString must spacial!!!");
        }
        this.connectString = connectString;
    }

    public int getSessionTimeoutMs() {
        return sessionTimeoutMs;
    }

    public void setSessionTimeoutMs(String sessionTimeoutMs) {
        if (sessionTimeoutMs != null && !"".equals(sessionTimeoutMs.trim())) {
            this.sessionTimeoutMs = Integer.valueOf(sessionTimeoutMs);
        }
    }

    public void setSessionTimeoutMs(int sessionTimeoutMs) {
        this.sessionTimeoutMs = sessionTimeoutMs;
    }

    public int getConnectionTimeoutMs() {
        return connectionTimeoutMs;
    }

    public void setConnectionTimeoutMs(String connectionTimeoutMs) {
        if (connectionTimeoutMs != null && !"".equals(connectionTimeoutMs.trim())) {
            this.connectionTimeoutMs = Integer.valueOf(connectionTimeoutMs);
        }
    }

    public void setConnectionTimeoutMs(int connectionTimeoutMs) {
        this.connectionTimeoutMs = connectionTimeoutMs;
    }

    public int getBaseSleepTimeMs() {
        return baseSleepTimeMs;
    }

    public void setBaseSleepTimeMs(String baseSleepTimeMs) {
        if (baseSleepTimeMs != null && !"".equals(baseSleepTimeMs.trim())) {
            this.baseSleepTimeMs = Integer.valueOf(baseSleepTimeMs);
        }
    }

    public void setBaseSleepTimeMs(int baseSleepTimeMs) {
        this.baseSleepTimeMs = baseSleepTimeMs;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(String maxRetries) {
        if (maxRetries != null && !"".equals(maxRetries.trim())) {
            this.maxRetries = Integer.valueOf(maxRetries);
        }
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public String getAppPath() {
        return appPath;
    }

    public void setAppPath(String appPath) {
        if (appPath != null && !"".equals(appPath.trim())) {
            this.appPath = appPath;
        }
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
}
