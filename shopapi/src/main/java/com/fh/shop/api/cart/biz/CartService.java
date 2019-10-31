package com.fh.shop.api.cart.biz;

import com.fh.shop.api.goods.common.ServerResponse;

public interface CartService {
  ServerResponse add(Long id, Integer shopId, Integer count);

  ServerResponse deleteById(Long idv, Long goodsId);

  ServerResponse get(Long id);
}
