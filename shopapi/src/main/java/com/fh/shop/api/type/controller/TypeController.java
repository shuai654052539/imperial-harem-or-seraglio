package com.fh.shop.api.type.controller;

import com.fh.shop.api.goods.common.ServerResponse;
import com.fh.shop.api.type.biz.TypeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/type")
@CrossOrigin
public class TypeController {

    @Resource(name="typeService")
    private TypeService typeService;

    @RequestMapping("typeListById")
    public ServerResponse typeListById(Integer typeid){
        return typeService.typeListById(typeid);
    }


    @RequestMapping("queryTypeTree")
    public ServerResponse queryTypeTree(){
        return typeService.queryTypeTree();
    }

}
