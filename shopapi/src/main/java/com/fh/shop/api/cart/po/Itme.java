package com.fh.shop.api.cart.po;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 */
public class Itme implements Serializable {
  private Long id;
  private String itmeName;
  private String img;
  private String subtotal;
  private String price;
  private Integer num;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getItmeName() {
    return itmeName;
  }

  public void setItmeName(String itmeName) {
    this.itmeName = itmeName;
  }

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

  public String getSubtotal() {
    return subtotal;
  }

  public void setSubtotal(String subtotal) {
    this.subtotal = subtotal;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public Integer getNum() {
    return num;
  }

  public void setNum(Integer num) {
    this.num = num;
  }
   public void update(){
     BigDecimal bigDecimal = new BigDecimal(this.price);
     BigDecimal bigDecimal1 = new BigDecimal(num.toString());
     this.subtotal =bigDecimal.multiply(bigDecimal1).toString();
   }

}
