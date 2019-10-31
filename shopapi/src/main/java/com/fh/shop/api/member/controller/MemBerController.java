package com.fh.shop.api.member.controller;

import com.fh.shop.api.area.po.MemberVo;
import com.fh.shop.api.goods.common.Check;
import com.fh.shop.api.goods.common.ServerResponse;
import com.fh.shop.api.goods.util.JedisUtil;
import com.fh.shop.api.member.biz.MemBerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("members")
@CrossOrigin("*")
public class MemBerController {
  @Resource(name = "memBerService")
  private MemBerService memBerService;
  @Autowired
  private HttpServletRequest request;
  @GetMapping()
  public ServerResponse get(String userName){
    return memBerService.get(userName);
  }

  @GetMapping("userInfo")
  @Check
  public ServerResponse getUserInfo(){
    MemberVo memberVo = (MemberVo) request.getAttribute("memberVo");
    if (memberVo == null) {
      return ServerResponse.error();
    }
      return ServerResponse.success(memberVo.getRealName());
  }
  @GetMapping("out")
  @Check
  public ServerResponse out(){
    MemberVo memberVo = (MemberVo) request.getAttribute("memberVo");
    JedisUtil.del(memberVo.getUserName() + ":" + memberVo.getUuid());
    return ServerResponse.success();
  }
}
