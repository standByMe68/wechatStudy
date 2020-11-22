package com.standbyme.wechatoa.service.impl;



import com.standbyme.wechatoa.dao.WechatUserMapper;
import com.standbyme.wechatoa.entity.WechatUserEntity;
import com.standbyme.wechatoa.service.WechatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jcf
 */

@Service
public class WechatUserServiceImpl implements WechatUserService {

    @Autowired
    WechatUserMapper wechatUserMapper;


    @Override
    public Integer batchInsertWechatUser(List<WechatUserEntity> wechatUserEntities) {
        return wechatUserMapper.batchInsertWechatUser(wechatUserEntities);
    }
}
