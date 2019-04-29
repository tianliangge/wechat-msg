package com.tian.wechat.msg.http;

import com.tian.wechat.msg.config.HttpConfig;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClient {

    @Autowired
    private HttpConfig httpConfig;

    /**
     * 实例化一个连接管理器，设置最大连接数、并发数
     * @return httpClientConnectionManager
     */
    @Bean(name = "httpClientConnectionManager")
    public PoolingHttpClientConnectionManager getHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager();
        // 最大连接数
        httpClientConnectionManager.setMaxTotal(httpConfig.getMaxTotal());
        // 并发数
        httpClientConnectionManager.setDefaultMaxPerRoute(httpConfig.getDefaultMaxPerRoute());
        return httpClientConnectionManager;
    }

    /**
     * 实例化连接池，设置连接池管理器
     * @param poolingHttpClientConnectionManager
     * @return httpClientBuilder
     */
    @Bean(name = "httpClientBuilder")
    public HttpClientBuilder getHttpClientBuilder(@Qualifier("httpClientConnectionManager")
                                                          PoolingHttpClientConnectionManager poolingHttpClientConnectionManager) {
        // HttpClientBuilder构造方法被保护了
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager);
        return httpClientBuilder;
    }

    /**
     * 注入连接池，用于获取httpClient
     * @param httpClientBuilder
     * @return
     */
    @Bean
    public CloseableHttpClient getCloseableHttpClient(@Qualifier("httpClientBuilder")HttpClientBuilder httpClientBuilder) {
        return httpClientBuilder.build();
    }

    /**
     * Builder是RequestConfig的一个内部类 通过RequestConfig的custom方法来获取到一个Builder对象
     * 设置builder的连接信息 这里还可以设置proxy，cookieSpec等属性。有需要的话可以在此设置
     *
     * @return
     */
    @Bean(name = "builder")
    public RequestConfig.Builder getBuilder() {
        RequestConfig.Builder builder = RequestConfig.custom();
        return builder.setConnectTimeout(httpConfig.getConnectTimeout())
                .setConnectionRequestTimeout(httpConfig.getConnectionRequestTimeout())
                .setSocketTimeout(httpConfig.getSocketTimeout())
                .setStaleConnectionCheckEnabled(httpConfig.getStaleConnectionCheckEnabled());
    }

    /**
     * 使用builder构建 RequestConfig
     * @param builder
     * @return
     */
    @Bean
    public RequestConfig getRequestConfig(@Qualifier("builder") RequestConfig.Builder builder) {
        return builder.build();
    }
}
