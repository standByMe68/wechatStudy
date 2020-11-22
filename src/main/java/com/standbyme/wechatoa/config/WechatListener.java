package com.standbyme.wechatoa.config;



import com.standbyme.wechatoa.entity.WechatUserEntity;
import com.standbyme.wechatoa.service.WechatService;
import com.standbyme.wechatoa.service.WechatUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jcf
 * 程序初始化，获取所有关注当前公众号的用户信息
 */

@Configuration
public class WechatListener  implements ApplicationRunner {

    private static Logger logger = LoggerFactory.getLogger(WechatListener.class);

    @Autowired
    WechatService wechatService;

    @Autowired
    WechatProperties wechatProperties;

    @Autowired
    WechatUserService wechatUserService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        //获取微信公众号凭证
        String wechatOAAccessToken = wechatService.getWechatOAAccessToken();

        //获取所有的用户openid
        List<String> wechatOAFollowersOpenId = wechatService.getWechatOAFollowersOpenId("", null);

        // TODO: 2020/11/16 获取对应的用户信息

        List<WechatUserEntity> wechatUserEntities = new ArrayList<>();

        //获取公众号appid
        String appId = wechatProperties.getAppId();
        for (String openId : wechatOAFollowersOpenId) {
            WechatUserEntity wechatUserEntity = new WechatUserEntity();
            wechatUserEntity.setAppId(appId);
            wechatUserEntity.setOpenid(openId);
            wechatUserEntities.add(wechatUserEntity);
        }

        logger.info("获取关注者信息条数:" + wechatUserEntities.size());

        //将数据入库
        Integer result = wechatUserService.batchInsertWechatUser(wechatUserEntities);

        logger.info("插入数据库关注者信息条数:" + result);

    }
}
