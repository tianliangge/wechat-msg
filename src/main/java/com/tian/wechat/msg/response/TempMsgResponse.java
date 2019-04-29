package com.tian.wechat.msg.response;

/**
 * 推送模板响应数据
 */
public class TempMsgResponse extends BaseResponse {

    /**
     * 消息id
     */
    private String msgid;

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }
}
