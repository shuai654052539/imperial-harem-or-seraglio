package com.fh.shop.api.sdk;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.InputStream;

public class MyConfig implements WXPayConfig {

  public String getAppID() {
    return "wxa1e44e130a9a8eee";
  }

  public String getMchID() {
    return "1507758211";
  }

  public String getKey() {
    return "feihujiaoyu12345678yuxiaoyang123";
  }

  @Override
  public InputStream getCertStream() {
    return null;
  }


  public int getHttpConnectTimeoutMs() {
    return 8000;
  }

  public int getHttpReadTimeoutMs() {
    return 10000;
  }

}
