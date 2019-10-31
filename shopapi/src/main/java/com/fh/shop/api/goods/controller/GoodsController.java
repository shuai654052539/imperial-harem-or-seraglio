package com.fh.shop.api.goods.controller;


import com.fh.shop.api.goods.biz.IGoodsService;
import com.fh.shop.api.goods.common.ServerResponse;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *
 */
@RestController
@RequestMapping("/goods")
@CrossOrigin("*")
public class GoodsController {

    @Resource(name = "goodsService")
    private IGoodsService goodsService;

  @RequestMapping("lists")
  public ServerResponse list(){
      return goodsService.list();
  }
}
