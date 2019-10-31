package com.fh.shop.api.token.biz;

import com.fh.shop.api.goods.common.ServerResponse;
import com.fh.shop.api.goods.util.JedisUtil;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * \
 */
@Service("tokenService")
public class TokenServiceImpl implements TokenService {

  @Override
  public ServerResponse getToken() {
    String string = UUID.randomUUID().toString();
    JedisUtil.set(string,string);
    return ServerResponse.success(string);
  }


}
