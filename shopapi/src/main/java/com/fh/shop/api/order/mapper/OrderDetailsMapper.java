package com.fh.shop.api.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.shop.api.order.po.OrderDetails;

import java.util.List;

/**
 *
 */
public interface OrderDetailsMapper extends BaseMapper<OrderDetails> {
  /**
   *
   * @param orderDetailsList
   */
  void adds(List<OrderDetails> orderDetailsList);
}
