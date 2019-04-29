package com.tian.wechat.msg.service;

import com.tian.wechat.msg.config.WxConfig;
import com.tian.wechat.msg.request.WxJoinRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * 微信服务签名验证
 */
@Service
public class WxService {

    @Autowired
    private WxConfig config;

    /**
     * 加密校验 signature
     * @param request 微信请求参数
     * @return
     */
    public boolean checkSignature(WxJoinRequest request) {
        String[] arr = new String[]{config.getToken(), request.getTimestamp(), request.getNonce()};
        //排序
        Arrays.sort(arr);
        //生成字符串
        StringBuffer content = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        //sha1加密
        String temp = getSha1(content.toString());
        return temp.equals(request.getSignature());
    }

    /**
     * Sha1加密方法
     *
     * @param str
     * @return
     */
    public String getSha1(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));
            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }
}
