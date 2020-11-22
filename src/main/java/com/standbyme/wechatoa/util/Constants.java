package com.standbyme.wechatoa.util;

/**
 * @author jcf
 * 微信常量类
 */
public class Constants {

    /**
     * 用于服务器验证参数
     */
    public final static String SIGNATURE = "signature";
    public final static String TIMESTAMP = "timestamp";
    public final static String NONCE = "nonce";
    public final static String ECHOSTR = "echostr";


    /**
     * 微信平台接口url
     */
    public final static String getAccessTokenURL = "https://api.weixin.qq.com/cgi-bin/token";
    public final static String getFollowersOpenIdURL = "https://api.weixin.qq.com/cgi-bin/user/get?";
    public final static String getUserInfoByOpenid = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?";


    /**
     * 微信平台URL调用传入参数
     */
    public final static String ACCESS_TOKEN = "access_token";
    public final static String NEXT_OPENID = "next_openid";

}
