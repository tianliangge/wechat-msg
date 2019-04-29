package com.tian.wechat.msg.web;

import com.tian.wechat.msg.request.WxJoinRequest;
import com.tian.wechat.msg.service.WxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/***
 * 验证微信服务器控制器
 */
@RestController
public class CertifiController {

    @Autowired
    private WxService wxService;
    private static Logger logger = LoggerFactory.getLogger(CertifiController.class);

    @GetMapping("/wxService")
    public String doGet(@Valid WxJoinRequest request) {
        System.out.println("测试微信的接入:"+request);
        if (wxService.checkSignature(request)) {
            logger.info("校验成功，直接返回数据");
            return request.getEchostr();
        }
        logger.info("校验失败");
        return null;
    }
}

