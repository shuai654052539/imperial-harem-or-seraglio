package com.fh.shop.api.order.po;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

/**
 *
 */
public class Recipient implements Serializable {
  private Long id;
  private String recipientName; //收件人姓名
  private String recipientPhone; //收件人电话
  private String detailedAddress; // 详细地址
  private Integer areaid1; // 省
  private Integer areaid2; // 市
  private Integer areaid3; // 县
  private Long memberId; // 会员ID
  @TableField(exist = false)
  private String specificAddress; //具体地址


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRecipientName() {
    return recipientName;
  }

  public void setRecipientName(String recipientName) {
    this.recipientName = recipientName;
  }

  public String getRecipientPhone() {
    return recipientPhone;
  }

  public void setRecipientPhone(String recipientPhone) {
    this.recipientPhone = recipientPhone;
  }

  public String getDetailedAddress() {
    return detailedAddress;
  }

  public void setDetailedAddress(String detailedAddress) {
    this.detailedAddress = detailedAddress;
  }

  public Integer getAreaid1() {
    return areaid1;
  }

  public void setAreaid1(Integer areaid1) {
    this.areaid1 = areaid1;
  }

  public Integer getAreaid2() {
    return areaid2;
  }

  public void setAreaid2(Integer areaid2) {
    this.areaid2 = areaid2;
  }

  public Integer getAreaid3() {
    return areaid3;
  }

  public void setAreaid3(Integer areaid3) {
    this.areaid3 = areaid3;
  }

  public Long getMemberId() {
    return memberId;
  }

  public void setMemberId(Long memberId) {
    this.memberId = memberId;
  }

  public String getSpecificAddress() {
    return specificAddress;
  }

  public void setSpecificAddress(String specificAddress) {
    this.specificAddress = specificAddress;
  }
}
