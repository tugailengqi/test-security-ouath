package com.lengqi.oauth.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lengqi.oauth.mapper.TbUserMapper;
import com.lengqi.oauth.service.TbUserService;
@Service
public class TbUserServiceImpl implements TbUserService{

    @Resource
    private TbUserMapper tbUserMapper;

}
