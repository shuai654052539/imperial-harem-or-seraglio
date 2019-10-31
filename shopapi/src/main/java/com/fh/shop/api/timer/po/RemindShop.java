package com.fh.shop.api.timer.po;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 */
public class RemindShop implements Serializable {
  private String shopName;
  private BigDecimal price;
  private Integer count;

  public String getShopName() {
    return shopName;
  }

  public void setShopName(String shopName) {
    this.shopName = shopName;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }
}
