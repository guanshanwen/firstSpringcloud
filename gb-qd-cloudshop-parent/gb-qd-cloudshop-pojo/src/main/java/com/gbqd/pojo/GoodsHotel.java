package com.gbqd.pojo;

import java.util.Date;

public class GoodsHotel {
    private Integer id;

    private Integer gName;

    private Integer storeId;

    private Integer businessId;

    private Integer bedTypeKey;

    private String bedTypeName;

    private Integer roomTypeId;

    private String roomTypeName;

    private String roomDesc;

    private Byte numberPeople;

    private Integer temId;

    private String itemAttr;

    private String itemAttrExpand;

    private Byte roomStatus;

    private Byte saleStatus;

    private Byte delStatus;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getgName() {
        return gName;
    }

    public void setgName(Integer gName) {
        this.gName = gName;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Integer getBedTypeKey() {
        return bedTypeKey;
    }

    public void setBedTypeKey(Integer bedTypeKey) {
        this.bedTypeKey = bedTypeKey;
    }

    public String getBedTypeName() {
        return bedTypeName;
    }

    public void setBedTypeName(String bedTypeName) {
        this.bedTypeName = bedTypeName == null ? null : bedTypeName.trim();
    }

    public Integer getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Integer roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName == null ? null : roomTypeName.trim();
    }

    public String getRoomDesc() {
        return roomDesc;
    }

    public void setRoomDesc(String roomDesc) {
        this.roomDesc = roomDesc == null ? null : roomDesc.trim();
    }

    public Byte getNumberPeople() {
        return numberPeople;
    }

    public void setNumberPeople(Byte numberPeople) {
        this.numberPeople = numberPeople;
    }

    public Integer getTemId() {
        return temId;
    }

    public void setTemId(Integer temId) {
        this.temId = temId;
    }

    public String getItemAttr() {
        return itemAttr;
    }

    public void setItemAttr(String itemAttr) {
        this.itemAttr = itemAttr == null ? null : itemAttr.trim();
    }

    public String getItemAttrExpand() {
        return itemAttrExpand;
    }

    public void setItemAttrExpand(String itemAttrExpand) {
        this.itemAttrExpand = itemAttrExpand == null ? null : itemAttrExpand.trim();
    }

    public Byte getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(Byte roomStatus) {
        this.roomStatus = roomStatus;
    }

    public Byte getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(Byte saleStatus) {
        this.saleStatus = saleStatus;
    }

    public Byte getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Byte delStatus) {
        this.delStatus = delStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}