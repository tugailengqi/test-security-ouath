package com.lengqi.oauth.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lengqi.oauth.mapper.TbPermissionMapper;
import com.lengqi.oauth.service.TbPermissionService;
@Service
public class TbPermissionServiceImpl implements TbPermissionService{

    @Resource
    private TbPermissionMapper tbPermissionMapper;

}
