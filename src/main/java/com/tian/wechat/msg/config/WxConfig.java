package com.tian.wechat.msg.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wx.account")
public class WxConfig {
    /**
     * 公众号id
     */
    private String appId;
    /**
     * appsecret
     */
    private String appsecret;
    /**
     * token
     */
    private String token;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "WxConfig{" +
                "appId='" + appId + '\'' +
                ", appsecret='" + appsecret + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
