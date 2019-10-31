package com.fh.shop.api.area.controller;

import com.fh.shop.api.area.biz.AreaService;
import com.fh.shop.api.area.po.Member;
import com.fh.shop.api.goods.common.ServerResponse;
import com.fh.shop.api.goods.util.CookieKey;
import com.fh.shop.api.goods.util.JedisUtil;
import com.fh.shop.api.goods.util.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/areas")
@CrossOrigin("*")
public class AreaController {

    @Resource(name = "areaService")
    private AreaService areaService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;



    @GetMapping
    public ServerResponse get(Integer id){
        return areaService.get(id);
    }

    @GetMapping("/code")
    public ServerResponse getCode(String phone){
        if (StringUtils.isEmpty(phone)) {
            return ServerResponse.error("手机号不能为空");
        }
        try {
            return areaService.getCode(phone,request,response);
        } catch (IOException e) {
            e.printStackTrace();
            return ServerResponse.error("发送失败");
        }

    }

    @GetMapping("/codes")
    public ServerResponse getCodes(String phone){
        if (StringUtils.isEmpty(phone)) {
            return ServerResponse.error("手机号不能为空");
        }
        try {
            return areaService.getCode(phone,request,response);
        } catch (IOException e) {
            e.printStackTrace();
            return ServerResponse.error("发送失败");
        }

    }

    @PostMapping
    public ServerResponse addMember(Member member,String oldPassWord,String code){
        ServerResponse serverResponse =  areaService.addMember(member,oldPassWord,code);
        return serverResponse;
    }

    @PostMapping("login")
    public ServerResponse login (Member member) {
        return areaService.login(member);
    }

    @PostMapping("logins")
    public ServerResponse logins (String phone,String yanzhengma) {
        return areaService.logins(phone,yanzhengma);
    }
  /*@RequestMapping("/queryTree")
    @ResponseBody
    public ServerResponse List(){
        try {
            List<AreaVo> list=areaService.getAreaPoList();
            return ServerResponse.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.error();
        }

    }

    @RequestMapping(method=RequestMethod.GET)
    @ResponseBody
    public ServerResponse List1(){
            List<AreaVo> list=areaService.getAreaPoList();
            return ServerResponse.success(list);
    }


    @RequestMapping("/queryTreeById")
    @ResponseBody
    public ServerResponse ListById(Long id){
      return areaService.queryTreeById(id);

    }

    @RequestMapping("typeListById")
    @ResponseBody
    public ServerResponse typeListById(Integer typeid){
        return areaService.typeListById(typeid);
    }

    @RequestMapping("deleteType")
    @ResponseBody
    public ServerResponse deleteType(@RequestParam("ids[]")List<Integer> ids){
        return areaService.deleteType(ids);
    }

    @RequestMapping("addTypeTree")
    @ResponseBody
    public ServerResponse addTypeTree(AreaPo area){
        return areaService.addTypeTree(area);
    }*/
}
