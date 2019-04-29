package com.tian.wxapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tian.wxapp.config.WxConfig;
import com.tian.wxapp.http.HttpApiService;
import com.tian.wxapp.model.template.WechatTemplateMsg;
import com.tian.wxapp.request.AccessRequest;
import com.tian.wxapp.response.AccessResponse;
import com.tian.wxapp.response.TempMsgResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoWxappApplication.class)
public class TestWechatService {

    @Autowired
    private HttpApiService httpApiService;
    @Autowired
    private WxConfig config;
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?";
    private static Logger logger = LoggerFactory.getLogger(TestWechatService.class);

    /**
     * 测试获取ACCESS-TOKEN
     */
    @Test
    public void testGetAccessToken() {
        AccessRequest request = new AccessRequest(config.getAppId(), config.getAppsecret());
        // 构建请求参数
        Map<String, String> map = new HashMap<String, String>();
        map.put("grant_type", request.getGrant_type());
        map.put("appida", request.getAppid());
        map.put("secret", request.getSecret());
        try {
            String result = httpApiService.doGet(ACCESS_TOKEN_URL, map);
            ObjectMapper obj = new ObjectMapper();
            AccessResponse response = obj.readValue(result, AccessResponse.class);
            System.out.println("response:"+response);
        } catch (Exception e) {
            logger.info("获取access_token失败"+e);
            e.printStackTrace();
        }
    }

    @Test
    public void testSendTemplate() {
        TempMsgResponse tempMsgResponse = null;
        TreeMap<String, TreeMap<String,String>> params = new TreeMap<String,TreeMap<String,String>>();
        //根据具体模板参数组装
        params.put("first", WechatTemplateMsg.item("处方审核未通过，请修改处方", "#000000"));
        params.put("prescriptionCode",WechatTemplateMsg.item("RP2018091020001", "#000000"));
        params.put("doctorName",WechatTemplateMsg.item("彭慰医师", "#000000"));
        params.put("auditStatus",WechatTemplateMsg.item("未通过", "#000000"));
        params.put("prescriptionState",WechatTemplateMsg.item("待修改", "#000000"));
        params.put("auditTime",WechatTemplateMsg.item("2018-09-10 22:22:00", "#000000"));
        params.put("remark",WechatTemplateMsg.item("你开处的处方不合格，请注意修改", "#000000"));
        WechatTemplateMsg wechatTemplateMsg = new WechatTemplateMsg();
        // 组装小程序的地址
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("appid", "wx8d42835866c9d7e6");
//        map.put("pagepath", "index");
//        wechatTemplateMsg.setMiniprogram(map);
        wechatTemplateMsg.setTemplate_id("Xg_CUCHKHpPgaKkGoneWxPKJBmldknmlwBknpOZYHGA");
        wechatTemplateMsg.setTouser("oSKX-0tdbbSeiDJifJrz1o45zHtc");
        wechatTemplateMsg.setUrl("http://music.163.com/#/song?id=27867140");
        wechatTemplateMsg.setData(params);
        ObjectMapper object = new ObjectMapper();
        String data = null;
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=13_sfAW2Q_263pmiromMVDRQ_qxrwX1OfRbfPyefpmq9WzdMYiA_FSm3YtKsuPaiJTZVRoGBYV8cGPEVhQJ016CvSkUfEcBM9AXfAvgZskzczsW_zK_m_rH_8KnRvcZAOeADAEFT";
        try {
            data = object.writeValueAsString(wechatTemplateMsg);
            System.out.println("data:"+data);
            String result = httpApiService.postJson(url, data);
            System.out.println("输出结果："+result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
//        templateMsgResult =  wechatMsgService.sendTemplate("获取的accessToken", data);
//        return templateMsgResult;
    }

}
