package com.fh.shop.api.Interceptor;

import com.alibaba.fastjson.JSONObject;
import com.fh.shop.api.area.po.MemberVo;
import com.fh.shop.api.exception.GlobalException;
import com.fh.shop.api.goods.common.Check;
import com.fh.shop.api.goods.common.ResponseEnum;
import com.fh.shop.api.goods.util.JedisUtil;
import com.fh.shop.api.goods.util.Md5Util;
import com.fh.shop.api.goods.util.SystemConst;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Base64;

/**
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.addHeader("Access-Control-Allow-Origin","*");
        response.addHeader("Access-Control-Allow-Methods","POST,GET,OPTIONS,DELETE,PUT");
        response.addHeader("Access-Control-Allow-Headers","x-requested-with,content-type,x_fh,token");
        String method1 = request.getMethod();
        if (method1.equalsIgnoreCase("OPTIONS")) {
            return false;
        }
        HandlerMethod handler1 = (HandlerMethod) handler;
        Method method = handler1.getMethod();
        if (!method.isAnnotationPresent(Check.class)) {
            return true;
        }
        //获取头信息
        String x_fh = request.getHeader("x_fh");
        if (StringUtils.isEmpty(x_fh)) {
            throw new GlobalException(ResponseEnum.HANDLER_IS_NULL);
        }
        String[] split = x_fh.split("\\.");
        if (split.length != 2) {
            throw new GlobalException(ResponseEnum.HANDLER_IS_ATTACK);
        }
        String memberVoEncoder = split[0];
        String md5MemberVo = split[1];
        String md5 = Md5Util.md5(memberVoEncoder + SystemConst.MIYAO);
        if (!md5.equals(md5MemberVo)) {
            throw new GlobalException(ResponseEnum.HANDLER_IS_ATTACK);
        }
        //解码
        String memberVoJson = new String(Base64.getDecoder().decode(memberVoEncoder));
        MemberVo memberVo = JSONObject.parseObject(memberVoJson, MemberVo.class);
        boolean exists = JedisUtil.exists(memberVo.getUserName() + ':' + memberVo.getUuid());
        if (!exists) {
            throw new GlobalException(ResponseEnum.PAST_DUE);
        }
        request.setAttribute("memberVo",memberVo);
        return true;
    }

}
