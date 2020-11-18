package com.lengqi.oauth.controller;

import com.lengqi.oauth.domain.TbContent;
import com.lengqi.oauth.service.TbContentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TbContentController {

    @Resource
    private TbContentService tbContentService;
    @GetMapping("/")
    public List<TbContent> selectAll(){
        return tbContentService.selectAll();
    }
}
