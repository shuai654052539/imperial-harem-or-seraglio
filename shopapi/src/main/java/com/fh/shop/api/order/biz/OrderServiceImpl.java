package com.fh.shop.api.order.biz;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.fh.shop.api.cart.po.Cart;
import com.fh.shop.api.cart.po.Itme;
import com.fh.shop.api.goods.common.ResponseEnum;
import com.fh.shop.api.goods.common.ServerResponse;
import com.fh.shop.api.goods.mapper.IGoodsDao;
import com.fh.shop.api.goods.po.Goods;
import com.fh.shop.api.goods.util.*;
import com.fh.shop.api.order.mapper.OrderDetailsMapper;
import com.fh.shop.api.order.mapper.OrderMapper;
import com.fh.shop.api.order.po.Order;
import com.fh.shop.api.order.po.OrderDetails;
import com.fh.shop.api.paylog.mapper.PayLogMapper;
import com.fh.shop.api.paylog.po.PayLog;
import com.fh.shop.api.sdk.MyConfig;
import com.github.wxpay.sdk.WXPay;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 *
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

  @Autowired
  private OrderMapper orderMapper;
  @Autowired
  private OrderDetailsMapper orderDetailsMapper;
  @Autowired
  private IGoodsDao goodsDao;
  @Autowired
  private PayLogMapper payLogMapper;

  @Override
  public ServerResponse addOrder(Order order, Long id) {
    //非空判断
    Integer payType = order.getPayType();
    Long recipientInfo = order.getRecipientInfo();
    if (payType == null || recipientInfo == null) {
      return ServerResponse.error("支付类型或地址信息为空");
    }
    //判断是否有购物车
    String cartJson = JedisUtil.hget(SystemConst.REDIS_CART, CookieKey.getCartValue(id.toString()));
    //判断有没有购物车
    if (StringUtils.isEmpty(cartJson)) {
      return ServerResponse.error("购物车没有物品");
    }
    Cart cart = JSONObject.parseObject(cartJson, Cart.class);
    List<Itme> list = cart.getList();
    List<OrderDetails> orderDetailsList = new ArrayList<>();
    List<Itme> itmeList = new ArrayList<>();
    //雪花算法 IdWorker.getTimeId()
    String timeId = IdWorker.getTimeId();
    for (int i = list.size() - 1; i >= 0; i--) {
      Itme itme = list.get(i);
      Goods goods = goodsDao.selectById(itme.getId());
      goods.setStock(goods.getStock() - itme.getNum());
      Long byId = goodsDao.upById(goods.getId(), itme.getNum());
      if (goods.getStock() < itme.getNum() || byId <= 0) {
        itmeList.add(itme);
        list.remove(i);
      } else {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setCount(itme.getNum());
        orderDetails.setImgPath(itme.getImg());
        orderDetails.setMemberId(id);
        orderDetails.setOrderId(timeId);
        orderDetails.setPrice(new BigDecimal(itme.getPrice()));
        orderDetails.setShopId(itme.getId());
        orderDetails.setShopName(itme.getItmeName());
        orderDetails.setTotalPrice(new BigDecimal(itme.getSubtotal()));
        orderDetailsList.add(orderDetails);
      }
    }
    //重新计算总价格和个数
    cart.update();
    order.setId(timeId);
    order.setMemberid(id);
    order.setTotalCount(cart.getCount());
    order.setTotalPrice(new BigDecimal(cart.getSumTotal()));
    if (orderDetailsList.size() == 0) {
      return ServerResponse.error(ResponseEnum.ERROE_UNDERSTOCK, itmeList);
    }
    orderDetailsMapper.adds(orderDetailsList);
    orderMapper.insert(order);
    JedisUtil.hdel(SystemConst.REDIS_CART, CookieKey.getCartValue(id.toString()));
    PayLog payLog = new PayLog();
    payLog.setOrderId(timeId);
    payLog.setPayType(payType);
    payLog.setOutTradeNo(DateUtil.date2src());
    payLog.setPayMoney(order.getTotalPrice());
    payLog.setUserId(id);
    payLogMapper.insert(payLog);
    String jsonString = JSONObject.toJSONString(payLog);
    JedisUtil.hset(SystemConst.ORDERLOGINFO, CookieKey.getMemberValue(id.toString()), jsonString);
    return ServerResponse.success(itmeList);
  }


  @Override
  public ServerResponse payOrder(Long id) {
    String hget = JedisUtil.hget(SystemConst.ORDERLOGINFO, CookieKey.getMemberValue(id.toString()));
    if (StringUtils.isEmpty(hget)) {
      return ServerResponse.error(-2,"没有订单");
    }
    PayLog payLog = JSONObject.parseObject(hget, PayLog.class);

    Map<String, String> stringStringMap = WXUtil.WXPayExample(payLog.getOutTradeNo(), payLog.getPayMoney());
    if (!stringStringMap.get("return_code").equalsIgnoreCase("SUCCESS")) {
      return ServerResponse.error(-2, stringStringMap.get("return_msg"));
    }
    if (!stringStringMap.get("result_code").equalsIgnoreCase("SUCCESS")) {
      return ServerResponse.error(-2, stringStringMap.get("return_msg"));
    }
    payLog.setErweima(stringStringMap.get("code_url"));
    return ServerResponse.success(payLog);
  }


  @Override
  public ServerResponse requestStatus(Long id) {
    String hget = JedisUtil.hget(SystemConst.ORDERLOGINFO, CookieKey.getMemberValue(id.toString()));
    if (StringUtils.isEmpty(hget)) {
      return ServerResponse.error(ResponseEnum.ERROE_ORDER_IS_NULL);
    }
    PayLog payLog = JSONObject.parseObject(hget, PayLog.class);
    MyConfig config = new MyConfig();
    WXPay wxpay = new WXPay(config);

    Map<String, String> data = new HashMap<String, String>();
    data.put("out_trade_no", payLog.getOutTradeNo());

    try {
      Integer count = 0;

      while (true) {
        Map<String, String> resp = wxpay.orderQuery(data);
        System.out.println(resp);
        if (!resp.get("return_code").equalsIgnoreCase("success")) {
          return ServerResponse.error(-2, resp.get("return_msg"));
        }
        if (!resp.get("result_code").equalsIgnoreCase("success")) {
          return ServerResponse.error(-2, resp.get("err_code_des"));
        }
        if (resp.get("trade_state").equalsIgnoreCase("SUCCESS")) {
          //支付成功 修改订单状态 和 日志状态 清空redis 日志缓存
          Date date = new Date();
          payLog.setPayStatus(1);
          payLog.setPayTime(date);
          payLog.setTransactionId(resp.get("transaction_id"));
          payLogMapper.updateById(payLog);
          Order order = new Order();
          order.setId(payLog.getOrderId());
          order.setStatus(20);
          order.setPayDate(date);
          orderMapper.updateById(order);
          //清缓存
          JedisUtil.hdel(SystemConst.ORDERLOGINFO, CookieKey.getMemberValue(id.toString()));
          return ServerResponse.success();
        }
        System.out.println("一步两步，是魔鬼的步伐，摩擦摩擦。");
        Thread.sleep(3000);
        count++;
        if (count >= 100) {
          return ServerResponse.error(ResponseEnum.QR_TIMEOUT);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      return ServerResponse.error();
    }
  }


}
