package com.fh.shop.api.order.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 */
public class Order implements Serializable {
  @TableId(type = IdType.INPUT)
  private String id;
  private Long memberid;
  private Date createDate = new Date();   //订单创建时间
  private Integer status = 10;     //订单状态
  private Integer payType = 1;    //支付类型   1 微信 2 支付宝
  private BigDecimal totalPrice;  //支付总金额
  private Date payDate;   //支付时间
  private Date dealCloseDate;   //交易结束时间
  private Integer totalCount;   //总件数
  private Date shipmentsDate;  //发货时间
  private Date successfulDate;  //交易成功时间 收件人信息Id
  private Long recipientInfo;    //收件人信息Id

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Long getMemberid() {
    return memberid;
  }

  public void setMemberid(Long memberid) {
    this.memberid = memberid;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Integer getPayType() {
    return payType;
  }

  public void setPayType(Integer payType) {
    this.payType = payType;
  }

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }

  public Date getPayDate() {
    return payDate;
  }

  public void setPayDate(Date payDate) {
    this.payDate = payDate;
  }

  public Date getDealCloseDate() {
    return dealCloseDate;
  }

  public void setDealCloseDate(Date dealCloseDate) {
    this.dealCloseDate = dealCloseDate;
  }

  public Integer getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(Integer totalCount) {
    this.totalCount = totalCount;
  }

  public Date getShipmentsDate() {
    return shipmentsDate;
  }

  public void setShipmentsDate(Date shipmentsDate) {
    this.shipmentsDate = shipmentsDate;
  }

  public Date getSuccessfulDate() {
    return successfulDate;
  }

  public void setSuccessfulDate(Date successfulDate) {
    this.successfulDate = successfulDate;
  }

  public Long getRecipientInfo() {
    return recipientInfo;
  }

  public void setRecipientInfo(Long recipientInfo) {
    this.recipientInfo = recipientInfo;
  }
}
