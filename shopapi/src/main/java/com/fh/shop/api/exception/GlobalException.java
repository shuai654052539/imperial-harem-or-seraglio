package com.fh.shop.api.exception;

import com.fh.shop.api.goods.common.ResponseEnum;

/**
 *
 */
public class GlobalException extends RuntimeException{
  private ResponseEnum responseEnum;

  public GlobalException(ResponseEnum responseEnum){
    this.responseEnum=responseEnum;
  }

  public ResponseEnum getResponseEnum() {
    return responseEnum;
  }

  public void setResponseEnum(ResponseEnum responseEnum) {
    this.responseEnum = responseEnum;
  }
}
