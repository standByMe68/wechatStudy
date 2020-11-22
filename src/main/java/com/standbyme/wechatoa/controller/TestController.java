package com.standbyme.wechatoa.controller;

import com.standbyme.wechatoa.service.WechatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private WechatService wechatService;


    @RequestMapping("/test/accesstoken")
    public String TestGetAccessToken(){
        logger.info("获取对应的微信凭证");
        return wechatService.getWechatOAAccessToken();
    }

}
