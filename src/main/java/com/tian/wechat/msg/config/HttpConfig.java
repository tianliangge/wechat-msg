package com.tian.wechat.msg.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Http配置信息
 */
@Component
@ConfigurationProperties(prefix = "client.http")
public class HttpConfig {

    /**
     * 最大连接数
     */
    private int maxTotal;
    /**
     * 并发数
     */
    private int defaultMaxPerRoute;
    /**
     * 创建连接的最长时间
     */
    private int connectTimeout;
    /**
     * 从连接池获取连接的最长时间
     */
    private int connectionRequestTimeout;
    /**
     * 数据传输最长时间
     */
    private int socketTimeout;
    /**
     * 提交请求之前测试连接是否可用
     */
    private boolean staleConnectionCheckEnabled;

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getDefaultMaxPerRoute() {
        return defaultMaxPerRoute;
    }

    public void setDefaultMaxPerRoute(int defaultMaxPerRoute) {
        this.defaultMaxPerRoute = defaultMaxPerRoute;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    public void setConnectionRequestTimeout(int connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public boolean getStaleConnectionCheckEnabled() {
        return staleConnectionCheckEnabled;
    }

    public void setStaleConnectionCheckEnabled(boolean staleConnectionCheckEnabled) {
        this.staleConnectionCheckEnabled = staleConnectionCheckEnabled;
    }

    @Override
    public String toString() {
        return "HttpConfig{" +
                "maxTotal=" + maxTotal +
                ", defaultMaxPerRoute=" + defaultMaxPerRoute +
                ", connectTimeout=" + connectTimeout +
                ", connectionRequestTimeout=" + connectionRequestTimeout +
                ", socketTimeout=" + socketTimeout +
                ", staleConnectionCheckEnabled=" + staleConnectionCheckEnabled +
                '}';
    }
}
