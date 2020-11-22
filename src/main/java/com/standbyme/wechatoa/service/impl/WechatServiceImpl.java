package com.standbyme.wechatoa.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.standbyme.wechatoa.config.WechatProperties;
import com.standbyme.wechatoa.entity.WechatBaseUser;
import com.standbyme.wechatoa.error.ErrorConstants;
import com.standbyme.wechatoa.error.InterfaceCallException;
import com.standbyme.wechatoa.service.WechatService;
import com.standbyme.wechatoa.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author jcf
 * 用户调用微信平台接口获取对应的属性
 */

@Service
public class WechatServiceImpl  implements WechatService {

    private final static Logger logger = LoggerFactory.getLogger(WechatServiceImpl.class);

    @Autowired
    private WechatProperties wechatProperties;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String getWechatOAAccessToken() {

        /*//在redis钟获取对应的凭证
        String access_token_redis = stringRedisTemplate.opsForValue().get(Constants.ACCESS_TOKEN);

        if (access_token_redis == null || "".equals(access_token_redis)){
            //调用微信凭证所需要的参数并拼接url
            String appId = wechatProperties.getAppId();
            String grantType = wechatProperties.getGrantType();
            String secret = wechatProperties.getSecret();
            String url = Constants.getAccessTokenURL + "?grant_type=" + grantType + "&appid=" + appId + "&secret=" + secret;

            //解析json获取微信凭证
            RestTemplate restTemplate = new RestTemplate();
            String forObject = restTemplate.getForObject(url, String.class);
            JSONObject jsonObject = JSONObject.parseObject(forObject);
            Object access_token = jsonObject.get("access_token");
            if (access_token == null){
                logger.info("Exception in interface call to get access token，errcode:"+jsonObject.get("errcode"));
                throw new InterfaceCallException(ErrorConstants.GET_ACCESS_TOKEN_ERROR,ErrorConstants.WECHAT_INTERFACE_ERROR,ErrorConstants.GET_ACCESS_TOKEN_ERROR);
            }else {

                stringRedisTemplate.opsForValue().set(Constants.ACCESS_TOKEN,(String)access_token,wechatProperties.getAccessTokenTimeout(), TimeUnit.SECONDS);

                return (String) access_token;
            }
        }else {
            return access_token_redis;
        }*/

        String appId = wechatProperties.getAppId();
        String grantType = wechatProperties.getGrantType();
        String secret = wechatProperties.getSecret();
        String url = Constants.getAccessTokenURL + "?grant_type=" + grantType + "&appid=" + appId + "&secret=" + secret;

        //解析json获取微信凭证
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = JSONObject.parseObject(forObject);
        Object access_token = jsonObject.get("access_token");
        if (access_token == null){
            logger.info("Exception in interface call to get access token，errcode:"+jsonObject.get("errcode"));
            throw new InterfaceCallException(ErrorConstants.GET_ACCESS_TOKEN_ERROR,ErrorConstants.WECHAT_INTERFACE_ERROR,ErrorConstants.GET_ACCESS_TOKEN_ERROR);
        }else {

            stringRedisTemplate.opsForValue().set(Constants.ACCESS_TOKEN,(String)access_token,wechatProperties.getAccessTokenTimeout(), TimeUnit.SECONDS);

            return (String) access_token;
        }


    }

    @Override
    public List<String> getWechatOAFollowersOpenId(@NotNull String accessToken,@Nullable String nestOpenId) {

        String url = Constants.getFollowersOpenIdURL + Constants.ACCESS_TOKEN + "=" + getWechatOAAccessToken() + "&" + Constants.NEXT_OPENID + nestOpenId;

        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = JSONObject.parseObject(forObject);
        //验证当前调用接口是否成功
        Object data = jsonObject.get("data");
        JSONArray objects = ((JSONObject)data).getJSONArray("openid");
        List<String> followerOpenOIds = new ArrayList<>();
        for (int i = 0 ; i < objects.size() ; i++){
            Object o = objects.get(i);
            followerOpenOIds.add(o.toString());
        }

        return followerOpenOIds;
    }

    @Override
    public List<WechatBaseUser> getWeChatUsersInfoByOpenids(List<String> openIds) {

        List<WechatBaseUser> wechatBaseUsers = new ArrayList<>();

        for (String openId : openIds) {
            WechatBaseUser wechatBaseUser = new WechatBaseUser();
            wechatBaseUser.setLang("zh-CN");
            wechatBaseUser.setOpenid(openId);
            wechatBaseUsers.add(wechatBaseUser);
        }

        Map<String, List<WechatBaseUser>> param = new HashMap<>();
        param.put("user_list", wechatBaseUsers);

        RestTemplate restTemplate = new RestTemplate();
        String s = restTemplate.postForObject(Constants.getUserInfoByOpenid, param, String.class);

        return null;
    }

}
