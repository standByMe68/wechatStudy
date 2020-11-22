package com.standbyme.wechatoa.entity;

/**
 * @author jcf
 * 用于对接数据库
 */


public class WechatUserEntity extends WechatBaseUser{

    private Integer id;

    private String appid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppId(String appId) {
        this.appid = appId;
    }
}
