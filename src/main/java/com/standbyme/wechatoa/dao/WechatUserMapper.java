package com.standbyme.wechatoa.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.standbyme.wechatoa.entity.WechatUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jcf
 * 与wechat_user进行接触
 */

@Mapper
public interface WechatUserMapper extends BaseMapper<WechatUserEntity> {

    /**
     * 批量插入关注者信息
     * @param wechatUserEntities
     * @return
     */
    Integer batchInsertWechatUser(@Param("wechatUserEntities") List<WechatUserEntity> wechatUserEntities);
}
