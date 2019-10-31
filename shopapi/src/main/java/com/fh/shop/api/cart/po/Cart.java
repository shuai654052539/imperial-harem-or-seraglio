package com.fh.shop.api.cart.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Cart implements Serializable {

    private Integer count;
    private String sumTotal;
    private List<Itme> list = new ArrayList<>();

  public Integer getCount() {
    return count;
  }


  public void setCount(Integer count) {
    this.count = count;
  }


  public String getSumTotal() {
    return sumTotal;
  }


  public void setSumTotal(String sumTotal) {
    this.sumTotal = sumTotal;
  }


  public List<Itme> getList() {
    return list;
  }


  public void setList(List<Itme> list) {
    this.list = list;
  }


  //更新
  public void update(){
    List<Itme> list = this.list;
    Integer itmeCount = 0;
    BigDecimal bigDecimal = new BigDecimal("0");
    for (int i = 0; i < list.size() ; i++ ) {
      Itme itme = list.get(i);
       if (itme.getNum() <= 0) {
         list.remove(i);
       } else {
         itme.update();
         itmeCount += itme.getNum();
         BigDecimal bigDecimal1 = new BigDecimal(itme.getSubtotal());
         bigDecimal = bigDecimal.add(bigDecimal1);
       }
    }
    this.count = itmeCount;
    this.sumTotal = bigDecimal.toString();
  }
}
