package com.fh.shop.api.exception;

import com.fh.shop.api.goods.common.ResponseEnum;
import com.fh.shop.api.goods.common.ServerResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 */
@RestControllerAdvice
public class ControllerException {

  @ExceptionHandler(GlobalException.class)
  public ServerResponse defaultException(GlobalException global){
    ResponseEnum responseEnum = global.getResponseEnum();
    return ServerResponse.error(responseEnum);
  }



}
