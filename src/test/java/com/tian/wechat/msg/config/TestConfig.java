package com.tian.wxapp.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoWxappApplication.class)
public class TestConfig {

    @Autowired
    private WxConfig config;

    @Test
    public void testConfig() {
        System.out.println("config:"+config);
    }
}
