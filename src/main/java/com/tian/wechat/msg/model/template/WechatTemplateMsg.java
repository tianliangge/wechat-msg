package com.tian.wechat.msg.model.template;

import java.util.TreeMap;

/**
 * 微信模板消息模板
 */
public class WechatTemplateMsg {

    /**
     * 接收者openid
     */
    private String touser;
    /**
     * 模板ID
     */
    private String template_id;
    /**
     * 模板跳转链接
     */
    private String url;
    /**
     * 对小程序的支持
     */
//    private Map<String, String> miniprogram;
    /**
     * 发送的数据体
     */
    private TreeMap<String, TreeMap<String, String>> data;

    /**
     * 构造模板参数
     *
     * @param value
     * @param color 可不填
     * @return
     */
    public static TreeMap<String, String> item(String value, String color) {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("value", value);
        params.put("color", color);
        return params;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

//    public Map<String, String> getMiniprogram() {
//        return miniprogram;
//    }
//
//    public void setMiniprogram(Map<String, String> miniprogram) {
//        this.miniprogram = miniprogram;
//    }

    public TreeMap<String, TreeMap<String, String>> getData() {
        return data;
    }

    public void setData(TreeMap<String, TreeMap<String, String>> data) {
        this.data = data;
    }
}