package com.fh.shop.api.cart.controller;

import com.fh.shop.api.area.po.MemberVo;
import com.fh.shop.api.cart.biz.CartService;
import com.fh.shop.api.goods.common.Check;
import com.fh.shop.api.goods.common.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 *
 */
@RestController
@RequestMapping("carts")
public class CartController  {
    @Resource(name = "cartService" )
    private CartService cartService;
    @Autowired
    private HttpServletRequest request;

    @PostMapping
    @Check
    public ServerResponse add(Integer count,Integer shopId,MemberVo memberVo){
      return cartService.add(memberVo.getId(),shopId,count);
    }

    @GetMapping
    @Check
    public ServerResponse get(MemberVo memberVo){
      return cartService.get(memberVo.getId());
    }

  @DeleteMapping("/{goodsId}")
  @Check
  public ServerResponse deleteById(@PathVariable Long goodsId,MemberVo memberVo){
    return cartService.deleteById(memberVo.getId(),goodsId);
  }
}
