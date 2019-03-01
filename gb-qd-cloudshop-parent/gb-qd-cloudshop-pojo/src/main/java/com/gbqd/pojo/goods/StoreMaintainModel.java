package com.gbqd.pojo.goods;

import java.util.List;

/**
 * 店铺返回主页
 */
public class StoreMaintainModel {
    /**
     * 店铺id
     */
    private Long storeId;
    /**
     * 店铺名称
     */
    private Long storeName;
    /**
     * 访客数
     */
    private Integer visitorNum;
    /**
     * 获取商品列表
     */
    private List<CsStoreGoodsHotelCategory> goodsList ;

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getStoreName() {
        return storeName;
    }

    public void setStoreName(Long storeName) {
        this.storeName = storeName;
    }

    public Integer getVisitorNum() {
        return visitorNum;
    }

    public void setVisitorNum(Integer visitorNum) {
        this.visitorNum = visitorNum;
    }

    public List<CsStoreGoodsHotelCategory> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<CsStoreGoodsHotelCategory> goodsList) {
        this.goodsList = goodsList;
    }
}
