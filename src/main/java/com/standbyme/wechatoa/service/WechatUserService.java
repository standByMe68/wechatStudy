package com.standbyme.wechatoa.service;



import com.standbyme.wechatoa.entity.WechatUserEntity;

import java.util.List;

/**
 * @author jcf
 * 用于维护微信关注者信息
 */
public interface WechatUserService {

    /**
     * 批量插入关注者信息
     * @param wechatUserEntities 关注者信息
     * @return 插入数量
     */
    Integer batchInsertWechatUser(List<WechatUserEntity> wechatUserEntities);

}
