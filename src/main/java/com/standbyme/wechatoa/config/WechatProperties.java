package com.standbyme.wechatoa.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author jcf
 * 微信配置类
 */

@Component
public class WechatProperties {

    @Value("${appid}")
    private String appId;

    @Value("${secret}")
    private String secret;

    @Value("${grant_type}")
    private String grantType;

    public Long getAccessTokenTimeout() {
        return accessTokenTimeout;
    }

    public void setAccessTokenTimeout(Long accessTokenTimeout) {
        this.accessTokenTimeout = accessTokenTimeout;
    }

    @Value("${accessTokenTimeout}")
    private Long accessTokenTimeout;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    @Override
    public String toString() {
        return "WechatProperties{" +
                "appId='" + appId + '\'' +
                ", secret='" + secret + '\'' +
                ", grantType='" + grantType + '\'' +
                '}';
    }
}
