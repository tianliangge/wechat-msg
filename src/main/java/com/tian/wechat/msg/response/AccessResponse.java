package com.tian.wechat.msg.response;

/**
 * 获取access_tokn返回值
 */
public class AccessResponse {

    /**
     * 获取到的凭证
     */
    private String access_token;
    /**
     * access_token接口调用凭证超时时间，单位（秒）
     */
    private String expires_in;
    /**
     * 错误码
     */
    private String errorcode;
    /**
     * 错误信息
     */
    private String errmsg;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    @Override
    public String toString() {
        return "AccessResponse{" +
                "access_token='" + access_token + '\'' +
                ", expires_in='" + expires_in + '\'' +
                ", errorcode='" + errorcode + '\'' +
                ", errmsg='" + errmsg + '\'' +
                '}';
    }
}
