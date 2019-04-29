package com.tian.wechat.msg.http;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Http请求服务
 */
@Service
public class HttpApiService {

    @Autowired
    private RequestConfig requestConfig;
    @Autowired
    private CloseableHttpClient closeableHttpClient;
    private static int DEFAULT_CONNTIME = 5000;
    private static int DEFAULT_READTIME = 5000;
    private static int DEFAULT_UPLOAD_READTIME = 10 * 1000;

    private static Logger logger = LoggerFactory.getLogger(HttpApiService.class);

    /**
     * 不带参数的get,状态码为200，返回数据，否则返回null
     *
     * @param url
     * @return
     * @throws IOException
     */
    public String doGet(String url) {
        try {
            logger.info(">>>>>不带参数的get");
            // get请求
            HttpGet httpGet = new HttpGet(url);
            // 设置配置信息
            httpGet.setConfig(requestConfig);
            // 发起网络请求
            CloseableHttpResponse response = closeableHttpClient.execute(httpGet);
            // 判断状态码是否是200
            if (response.getStatusLine().getStatusCode() == 200) {
                // 获取响应体内容
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            logger.info("请求失败");
            e.printStackTrace();
            throw new RuntimeException("请求失败");
        }
        return null;
    }

    /**
     * 带参数的get,状态码为200，返回数据，否则返回null
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public String doGet(String url, Map<String, String> map) throws Exception {
        try {
            logger.info(">>>>>带参数的get");
            URIBuilder uriBuilder = new URIBuilder(url);
            if (map != null && !map.isEmpty()) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    uriBuilder.addParameter(entry.getKey(), entry.getValue());
                }
            }
            // 调用不带参的doget
            return doGet(uriBuilder.build().toString());
        } catch (Exception e) {
            logger.info("请求失败");
            e.printStackTrace();
            throw new RuntimeException("请求失败");
        }
    }

    /**
     * 带参数的post请求
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public String doPost(String url, Map<String, String> map) throws Exception {
        try {
            logger.info(">>>>>带参数的post请求");
            // post请求
            HttpPost httpPost = new HttpPost(url);
            // 设置配置信息
            httpPost.setConfig(requestConfig);
            if (map != null && map.isEmpty()) {
                List<NameValuePair> list = new ArrayList<NameValuePair>(0);
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
                }
                // post需要构建表单对象
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "UTF-8");
                // 表单对象放入post中
                httpPost.setEntity(urlEncodedFormEntity);
            }
            // 发起请求
            CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
            // 判断状态码是否是200
            if (response.getStatusLine().getStatusCode() == 200) {
                // 获取响应体内容
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            logger.info("请求失败");
            e.printStackTrace();
            throw new RuntimeException("请求失败");
        }
        return null;
    }

    /**
     * 不带参数的post
     *
     * @param url
     * @return
     * @throws Exception
     */
    public String doPost(String url) throws Exception {
        try {
            logger.info(">>>>>不带参数的post");
            return doPost(url, null);
        } catch (Exception e) {
            logger.info("请求失败");
            e.printStackTrace();
            throw new RuntimeException("请求失败");
        }
    }

    /**
     * post请求传输String参数 例如：name=Jack&sex=1&type=2
     * Content-type:application/x-www-form-urlencoded
     * @param url      url地址
     * @param strParam 参数
     * @return
     */
    public String postJson(String url, String strParam) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        String rs = "";
        try {
            if (null != strParam) {
                // 解决中文乱码问题
                StringEntity entity = new StringEntity(strParam, "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/x-www-form-urlencoded");
                httpPost.setEntity(entity);
            }
            CloseableHttpResponse result = closeableHttpClient.execute(httpPost);
            // 请求发送成功，并得到响应
            if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                try {
                    // 读取服务器返回过来的json字符串数据
                    rs = EntityUtils.toString(result.getEntity(), "utf-8");
                } catch (Exception e) {
                    logger.error("post请求提交失败:" + url, e);
                }
            }
        } catch (IOException e) {
            logger.error("post请求提交失败:" + url, e);
        } finally {
            httpPost.releaseConnection();
        }
        return rs;
    }
}
