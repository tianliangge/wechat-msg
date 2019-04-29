package com.tian.wechat.msg.config;

import com.tian.wechat.msg.WechatMsgApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WechatMsgApplication.class)
public class TestConfig {

    @Autowired
    private WxConfig config;

    @Test
    public void testConfig() {
        System.out.println("config:"+config);
    }
}
