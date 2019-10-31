package com.fh.shop.api.order.controller;

import com.fh.shop.api.area.po.MemberVo;
import com.fh.shop.api.goods.common.Check;
import com.fh.shop.api.goods.common.ServerResponse;
import com.fh.shop.api.order.biz.RecipientService;
import com.fh.shop.api.order.po.Recipient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 *
 */
@RestController
@RequestMapping("recipients")
public class RecipientControler {
  @Resource(name = "recipientService")
  private RecipientService recipientService;

  @PostMapping
  @Check
  public ServerResponse addRecipient(Recipient recipient, MemberVo memberVo){
    return recipientService.addRecipient(recipient,memberVo);
  }

  @GetMapping
  @Check
  public ServerResponse getRecipient(MemberVo memberVo){
    return recipientService.getRecipient(memberVo);
  }

  @GetMapping("/{id}")
  @Check
  public ServerResponse getRecipientById(@PathVariable Long id){
    return recipientService.getRecipientById(id);
  }

  @DeleteMapping("/{id}")
  @Check
  public ServerResponse delRecipient(@PathVariable Long id){
    return recipientService.delRecipient(id);
  }

  @PutMapping
  @Check
  public ServerResponse updateRecipient(@RequestBody Recipient recipient){
    return recipientService.updateRecipient(recipient);
  }

}
