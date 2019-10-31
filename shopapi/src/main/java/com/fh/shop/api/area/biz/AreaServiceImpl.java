package com.fh.shop.api.area.biz;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.shop.api.area.mapper.AreaDao;
import com.fh.shop.api.area.mapper.MemberDao;
import com.fh.shop.api.area.po.AreaPo;
import com.fh.shop.api.area.po.Member;
import com.fh.shop.api.area.po.MemberVo;
import com.fh.shop.api.goods.common.ServerResponse;
import com.fh.shop.api.goods.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("areaService")
@Transactional(rollbackFor = Exception.class)
public class AreaServiceImpl implements AreaService {

    @Autowired
     private AreaDao areaDao;
    @Autowired
    private MemberDao memberDao;
    private static final String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";

    @Override
    public ServerResponse get(Integer id) {
        QueryWrapper<AreaPo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("pid",id);
        List<AreaPo> areaPos = areaDao.selectList(queryWrapper);
        return ServerResponse.success(areaPos);
    }


    @Override
    public ServerResponse getCode(String phone, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (phone.length() != 11 ) {
            return ServerResponse.error("长度不对");
        }
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        boolean isMatch = m.matches();
        if (!isMatch) {
            return ServerResponse.error("格式不对");
        }

        QueryWrapper<Member> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("phone",phone);
        Member member = memberDao.selectOne(queryWrapper);

        if (member == null) {
            return ServerResponse.error("请先进行用户注册，再进行免密登录");
        }

        String code = WangYiYunUtil.testDuanxin(phone);
        JedisUtil.set(CookieKey.yanZhengMaKey(phone),code);
        return ServerResponse.success();
    }


    @Override
    public ServerResponse addMember(Member member,String oldPassWord,String code) {
        if (StringUtils.isEmpty(code)) {
            return ServerResponse.error("请输入验证码");
        }
        if (StringUtils.isEmpty(member.getPhone())) {
            return ServerResponse.error("手机号码不可为空");
        }
        String s = JedisUtil.get(CookieKey.yanZhengMaKey(member.getPhone()));
        if (StringUtils.isEmpty(s)) {
            return ServerResponse.error("验证码无效");
        }
        if (!code.equals(s)) {
            return ServerResponse.error("验证码错误");
        }
        if (StringUtils.isEmpty(member.getPassWord()) || StringUtils.isEmpty(oldPassWord)) {
            return ServerResponse.error("请输入密码");
        }
        if (!oldPassWord.equals(member.getPassWord())) {
            return ServerResponse.error("密码不一致");
        }
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone",member.getPhone());
        Member memberByPhone = memberDao.selectOne(queryWrapper);
        if (memberByPhone != null) {
            return ServerResponse.error("手机号码已被注册");
        }
        QueryWrapper<Member> queryWrapperByUser = new QueryWrapper<>();
        queryWrapperByUser.eq("userName",member.getUserName());
        Member memberByUser = memberDao.selectOne(queryWrapper);
        if (memberByUser != null) {
            return ServerResponse.error("账号已被注册");
        }
        memberDao.insert(member);
        return ServerResponse.success();
    }


    @Override
    public ServerResponse login(Member member) {
        //非空验证
        if (StringUtils.isEmpty(member.getUserName()) || StringUtils.isEmpty(member.getPassWord())) {
            return  ServerResponse.error("用户名密码不能为空");
        }
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userName",member.getUserName());
        Member member1 = memberDao.selectOne(queryWrapper);
        //账号验证
        if (null == member1) {
            return ServerResponse.error("用户名不存在");
        }
        //密码验证
        if ( !member1.getPassWord().equals(member.getPassWord()) ) {
            return ServerResponse.error("密码错误");
        }
        MemberVo memberVo = new MemberVo();
        memberVo.setUserName(member1.getUserName());
        memberVo.setId(member1.getId());
        memberVo.setRealName(member1.getRealName());
        String uuid = UUID.randomUUID().toString();
        memberVo.setUuid(uuid);
        String memberVoJson = JSONObject.toJSONString(memberVo);
        String memberVoEncoder = Base64.getEncoder().encodeToString(memberVoJson.getBytes());
        String md5MemberVo = Md5Util.md5(memberVoEncoder + SystemConst.MIYAO);
        //保存到redis中
        JedisUtil.setex(memberVo.getUserName()+":"+memberVo.getUuid(),60*10,"1");
        return ServerResponse.success( memberVoEncoder + "." + md5MemberVo );
    }

    @Override
    public ServerResponse logins(String phone, String yanzhengma) {
        //非空验证
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(yanzhengma)) {
            return  ServerResponse.error("手机或验证码不能为空");
        }
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone",phone);
        Member member1 = memberDao.selectOne(queryWrapper);
        //账号验证
        if (null == member1) {
            return ServerResponse.error("手机号不存在");
        }
        String s = JedisUtil.get(CookieKey.yanZhengMaKey(phone));
        //密码验证
        if ( !yanzhengma.equals(s) ) {
            return ServerResponse.error("验证码错误");
        }
        MemberVo memberVo = new MemberVo();
        memberVo.setUserName(member1.getUserName());
        memberVo.setId(member1.getId());
        memberVo.setRealName(member1.getRealName());
        String uuid = UUID.randomUUID().toString();
        memberVo.setUuid(uuid);
        String memberVoJson = JSONObject.toJSONString(memberVo);
        String memberVoEncoder = Base64.getEncoder().encodeToString(memberVoJson.getBytes());
        String md5MemberVo = Md5Util.md5(memberVoEncoder + SystemConst.MIYAO);
        //保存到redis中
        JedisUtil.setex(memberVo.getUserName()+":"+memberVo.getUuid(),60*10,"1");
        return ServerResponse.success( memberVoEncoder + "." + md5MemberVo );

    }


}
