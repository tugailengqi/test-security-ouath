package com.lengqi.oauth.service.impl;

import com.lengqi.oauth.domain.TbContent;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lengqi.oauth.mapper.TbContentMapper;
import com.lengqi.oauth.service.TbContentService;

import java.util.List;

@Service
public class TbContentServiceImpl implements TbContentService{

    @Resource
    private TbContentMapper tbContentMapper;

    @Override
    public List<TbContent> selectAll() {
        return tbContentMapper.selectAll();
    }
}
