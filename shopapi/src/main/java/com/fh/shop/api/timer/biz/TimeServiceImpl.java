package com.fh.shop.api.timer.biz;

import com.fh.shop.api.goods.mapper.IGoodsDao;
import com.fh.shop.api.goods.util.Sendmail;
import com.fh.shop.api.timer.po.RemindShop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service("timeService")
public class TimeServiceImpl implements TimeService {

  @Autowired
  private IGoodsDao goodsDao;

  @Override
  public void timedTask() {
    List<RemindShop> list = goodsDao.getRemindShop();
    StringBuffer detail = new StringBuffer();
    detail.append("以下商品库存不足，请及时补充。");
    detail.append("<div>\n" +
        "     <table cellspacing=\"1\" cellpadding=\"1\" border=\"1px\">");
    detail.append("<tr>\n" +
        "         <td>商品名</td>\n" +
        "         <td>价格</td>\n" +
        "         <td>库存量</td>\n" +
        "       </tr>");
    for (RemindShop remindShop:list) {
      detail.append(" <tr>\n" +
          "         <td>" + remindShop.getShopName() + "</td>\n" +
          "         <td>" + remindShop.getPrice().toString() + "</td>\n" +
          "         <td>" + remindShop.getCount() + "</td>\n" +
          "       </tr>");
    }
    detail.append("</table>\n" +
        "   </div>");
      /*Sendmail.SendOutEmail("于老大好，我是唐帅", detail.toString(), "1633403337@qq.com");*/
    System.out.println("22");
  }


}
