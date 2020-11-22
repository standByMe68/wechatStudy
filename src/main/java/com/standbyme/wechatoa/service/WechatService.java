package com.standbyme.wechatoa.service;



import com.standbyme.wechatoa.entity.WechatBaseUser;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author jcf
 * 调用微信接口处理事件
 */
public interface WechatService {

    /**
     * 获取微信公众号的凭证
     * @return 凭证
     */
    String getWechatOAAccessToken();


    /**
     * 获取公众号的所有关注者id
     * @param accessToken 微信凭证
     * @param nestOpenId 从哪一个openid开始查询
     * @return 返回所有用户的openID
     */
    List<String> getWechatOAFollowersOpenId(@NotNull String accessToken,@Nullable String nestOpenId);

    /**
     * 根据用户的openID获取用户对应的unionId进行存储,暂时不需要使用
     * @param openIds
     * @return
     */
    @Deprecated
    List<WechatBaseUser> getWeChatUsersInfoByOpenids(List<String> openIds);
}
