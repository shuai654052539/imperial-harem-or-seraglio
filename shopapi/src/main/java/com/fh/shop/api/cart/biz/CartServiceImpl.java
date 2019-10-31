package com.fh.shop.api.cart.biz;

import com.alibaba.fastjson.JSONObject;
import com.fh.shop.api.cart.po.Cart;
import com.fh.shop.api.cart.po.Itme;
import com.fh.shop.api.goods.common.ServerResponse;
import com.fh.shop.api.goods.mapper.IGoodsDao;
import com.fh.shop.api.goods.po.Goods;
import com.fh.shop.api.goods.util.CookieKey;
import com.fh.shop.api.goods.util.JedisUtil;
import com.fh.shop.api.goods.util.SystemConst;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service("cartService")
public class CartServiceImpl implements CartService {

  @Autowired
  private IGoodsDao goodsDao;

  @Override
  public ServerResponse add(Long id, Integer goodsId, Integer count) {
    //非空判断
    if (goodsId==null && count==null){
       return ServerResponse.error();
    }
    //根据商品id查询商品信息
    Goods goods = goodsDao.selectById(goodsId);
    //判断商品是否存在和它的上架状态
    if (goods == null) {
      return ServerResponse.error();
    }
    if (goods.getStock()==0) {
      return ServerResponse.error();
    }
    //声明购物车对象
    Cart cart = null;
    //redis中找到用户的购物车
    String cartJson = JedisUtil.hget(SystemConst.REDIS_CART, CookieKey.getCartValue(id.toString()));
    //判断购物车是否为空  为空新建 否则就用找到的购物车
    if(StringUtils.isEmpty(cartJson)){
      cart = new Cart();
    }else {
      Cart cart1 = JSONObject.parseObject(cartJson, Cart.class);
      cart = cart1;
    }
    //将商品添加到购物车中
    List<Itme> list = cart.getList();
    //先判断 购物车中是否存在该商品
    boolean exist = false;
    for (Itme itme:list) {
        if (itme.getId() == Long.valueOf(goods.getId())) {
          itme.setNum(itme.getNum() + count);
            exist = true;
            break;
        }
    }
    if (!exist) {
      //将商品信息存入到购物项对象中
      Itme itme = new Itme();
      itme.setId(Long.valueOf(goodsId.toString()));
      itme.setImg(goods.getGoodsImgUrl());
      itme.setItmeName(goods.getGoodsName());
      itme.setPrice(goods.getPrice().toString());
      itme.setNum(count);
      BigDecimal bigDecimal = BigDecimal.valueOf(count);
      itme.setSubtotal(goods.getPrice().multiply(bigDecimal).toString());
      list.add(itme);
    }
    //购物车对象中的 更新方法 根据list集合 更新对象中的 总和 和 数量
    //更新以后将数据保存到redis
    cart.update();
    if (cart.getList().size() <= 0) {
      JedisUtil.hdel(SystemConst.REDIS_CART,CookieKey.getCartValue(id.toString()));
      return ServerResponse.success();
    } else {
      String jsonString = JSONObject.toJSONString(cart);
      JedisUtil.hset(SystemConst.REDIS_CART,CookieKey.getCartValue(id.toString()),jsonString);
    }
    return ServerResponse.success(cart);
  }


  @Override
  public ServerResponse deleteById(Long id,Long goodsId) {
    //非空判断
     if(goodsId == null) {
       return ServerResponse.error();
     }
     //
    String cartJson = JedisUtil.hget(SystemConst.REDIS_CART,CookieKey.getCartValue(id.toString()));
     if (StringUtils.isEmpty(cartJson)) {
       return ServerResponse.error();
     }
    Cart cart = JSONObject.parseObject(cartJson, Cart.class);
    List<Itme> list = cart.getList();
    for (Itme itme:list) {
      if (itme.getId() == goodsId) {
        list.remove(itme);
        break;
      }
    }
    cart.update();
    if (cart.getList().size() <= 0) {
      JedisUtil.hdel(SystemConst.REDIS_CART,CookieKey.getCartValue(id.toString()));
      return ServerResponse.success();
    } else {
      String jsonString = JSONObject.toJSONString(cart);
      JedisUtil.hset(SystemConst.REDIS_CART,CookieKey.getCartValue(id.toString()),jsonString);
    }
    return ServerResponse.success();
  }


  @Override
  public ServerResponse get(Long id) {
    //根据id找到购物车
    String cartJson = JedisUtil.hget(SystemConst.REDIS_CART,CookieKey.getCartValue(id.toString()));
    //判断有没有购物车
    if (StringUtils.isEmpty(cartJson)) {
      return ServerResponse.error();
    }
    Cart cart = JSONObject.parseObject(cartJson, Cart.class);
    cart.update();
    /*if (cart.getList().size() <= 0) {
      JedisUtil.hdel(SystemConst.REDIS_CART,CookieKey.getCartValue(id.toString()));
      return ServerResponse.error();
    } else {
      String jsonString = JSONObject.toJSONString(cart);
      JedisUtil.hset(SystemConst.REDIS_CART,CookieKey.getCartValue(id.toString()),jsonString);
    }*/
    return ServerResponse.success(cart);
  }


}
