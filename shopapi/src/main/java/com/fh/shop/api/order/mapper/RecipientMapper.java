package com.fh.shop.api.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.shop.api.order.po.Recipient;

import java.util.List;

/**
 *
 */
public interface RecipientMapper extends BaseMapper<Recipient> {
  List<Recipient> getList(Long id);
}
