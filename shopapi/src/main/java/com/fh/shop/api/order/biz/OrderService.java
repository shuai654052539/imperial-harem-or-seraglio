package com.fh.shop.api.order.biz;

import com.fh.shop.api.goods.common.ServerResponse;
import com.fh.shop.api.order.po.Order;

/**
 *
 */
public interface OrderService {
  /**
   *
   * @param order
   * @return
   */
  ServerResponse addOrder(Order order, Long id);

  /**
   *
   * @param id
   * @return
   */
  ServerResponse payOrder(Long id);

  /**
   *
   * @param id
   * @return
   */
  ServerResponse requestStatus(Long id);
}
