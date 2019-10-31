package com.fh.shop.api.Interceptor;

import com.fh.shop.api.exception.GlobalException;
import com.fh.shop.api.goods.common.Idempotent;
import com.fh.shop.api.goods.common.ResponseEnum;
import com.fh.shop.api.goods.util.JedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 *
 */
public class IdempotentInterceptor extends HandlerInterceptorAdapter {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    HandlerMethod handler1 = (HandlerMethod) handler;
    Method method = handler1.getMethod();
    if (!method.isAnnotationPresent(Idempotent.class)) {
      return true;
    }

    String token = request.getHeader("token");
    if (StringUtils.isEmpty(token)) {
      throw new GlobalException(ResponseEnum.HANDLER_IS_NULL);
    }
    String tokenJson = JedisUtil.get(token);
    if (StringUtils.isEmpty(tokenJson)) {
      throw new GlobalException(ResponseEnum.HANDLER_IS_ATTACK);
    }
    Long del = JedisUtil.del(tokenJson);
    if (del <= 0) {
      throw new GlobalException(ResponseEnum.OPERATION_REPEATED);
    }
    return true;
  }

}
