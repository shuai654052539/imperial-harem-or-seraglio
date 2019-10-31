package com.fh.shop.api.order.controller;

import com.fh.shop.api.area.po.MemberVo;
import com.fh.shop.api.goods.common.Check;
import com.fh.shop.api.goods.common.Idempotent;
import com.fh.shop.api.goods.common.ServerResponse;
import com.fh.shop.api.order.biz.OrderService;
import com.fh.shop.api.order.po.Order;
import com.fh.shop.api.sdk.MyConfig;
import com.github.wxpay.sdk.WXPay;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@RestController
@RequestMapping("orders")
public class OrderController {
    @Resource(name = "orderService")
    private OrderService orderService;

    @PostMapping
    @Check
    @Idempotent
    public ServerResponse addOrder(Order order, MemberVo memberVo){
      return orderService.addOrder(order,memberVo.getId());
    }

    @GetMapping
    @Check
    public ServerResponse payOrder(MemberVo memberVo){
        return orderService.payOrder(memberVo.getId());
    }

    @GetMapping("/status")
    @Check
    public ServerResponse requestStatus(MemberVo memberVo){
        return orderService.requestStatus(memberVo.getId());
    }
}
