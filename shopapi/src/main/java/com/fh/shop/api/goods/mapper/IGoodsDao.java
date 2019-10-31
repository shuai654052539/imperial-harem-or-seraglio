package com.fh.shop.api.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.shop.api.goods.po.Goods;
import com.fh.shop.api.timer.po.RemindShop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 */
public interface IGoodsDao extends BaseMapper<Goods> {

  List<RemindShop> getRemindShop();

  Long upById(@Param("id") Integer id, @Param("count") Integer count);
}
