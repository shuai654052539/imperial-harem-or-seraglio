package com.fh.shop.api.goods.biz;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.shop.api.goods.common.ServerResponse;
import com.fh.shop.api.goods.mapper.IGoodsDao;
import com.fh.shop.api.goods.po.Goods;
import com.fh.shop.api.goods.util.DateUtil;
import com.fh.shop.api.goods.util.JedisUtil;
import com.fh.shop.api.goods.vo.GoodsVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("goodsService")
public class IGoodsServiceImpl implements IGoodsService {

    @Autowired
    private IGoodsDao goodsDao;


    @Override
    public ServerResponse list() {
        String goodsList = JedisUtil.get("goodsList");
        if(StringUtils.isNotEmpty(goodsList)){
            List<GoodsVo> goodsVos = JSONObject.parseArray(goodsList, GoodsVo.class);
            return ServerResponse.success(goodsVos);
        }
        QueryWrapper<Goods> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        queryWrapper.eq("putaway","1");
        List<Goods> goods = goodsDao.selectList(queryWrapper);
        List<GoodsVo> goodsVos1 = buildListVo(goods);
        String jsonString = JSONObject.toJSONString(goodsVos1);
        JedisUtil.set("goodsList",jsonString);
        return ServerResponse.success(goodsVos1);
    }

    private List<GoodsVo> buildListVo(List<Goods> goods) {
        List<GoodsVo> goodsVos=new ArrayList<>();
        for (Goods goods1:goods) {
            GoodsVo g=new GoodsVo();
            g.setId(goods1.getId());
            g.setCreateDate(DateUtil.date2src(goods1.getCreateDate(),DateUtil.Y_M_D));
            g.setGoodsImgUrl(goods1.getGoodsImgUrl());
            g.setGoodsName(goods1.getGoodsName());
            g.setHotSell(goods1.getHotSell());
            g.setPrice(goods1.getPrice());
            g.setPutaway(goods1.getPutaway());
            g.setStock(goods1.getStock());
            goodsVos.add(g);
        }
        return goodsVos;
    }
}
