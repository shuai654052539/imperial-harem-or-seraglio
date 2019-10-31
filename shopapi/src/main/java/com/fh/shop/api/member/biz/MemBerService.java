package com.fh.shop.api.member.biz;

import com.fh.shop.api.goods.common.ServerResponse;

public interface MemBerService {
  ServerResponse get(String userName);
}
