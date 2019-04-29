package com.tian.wechat.msg.response;

/**
 * 通用的返回状态
 */
public class BaseResponse{
    /**
     * 错误码
     */
    private int errcode;
    /**
     * 错误信息
     */
    private String errmsg;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
