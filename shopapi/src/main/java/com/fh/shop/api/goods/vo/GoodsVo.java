package com.fh.shop.api.goods.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class GoodsVo implements Serializable {
    private Integer id;
    private String goodsName;
    private BigDecimal price;
    private String goodsImgUrl;
    private String createDate;
    private Integer stock;
    private Integer hotSell;
    private Integer putaway;

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getHotSell() {
        return hotSell;
    }

    public void setHotSell(Integer hotSell) {
        this.hotSell = hotSell;
    }

    public Integer getPutaway() {
        return putaway;
    }

    public void setPutaway(Integer putaway) {
        this.putaway = putaway;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getGoodsImgUrl() {
        return goodsImgUrl;
    }

    public void setGoodsImgUrl(String goodsImgUrl) {
        this.goodsImgUrl = goodsImgUrl;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
