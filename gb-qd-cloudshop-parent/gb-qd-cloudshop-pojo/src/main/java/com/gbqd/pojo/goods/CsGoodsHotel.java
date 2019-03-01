package com.gbqd.pojo.goods;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class CsGoodsHotel {
    @ApiModelProperty(value = " 主键ID")
    private Long id;
    @ApiModelProperty(value = " 商品自定义类别: cs_store_goods_hotel_category.id")
    private Long ghcId;
    @ApiModelProperty(value = " 商品名称 店主上传商品的名称 exp:北-1002房间")
    private String gName;
    @ApiModelProperty(value = " 云店id t_store.id")
    private Long storeId;
    @ApiModelProperty(value = " 行业id t_business.bu_id，二级行业id")
    private Long buId;
    @ApiModelProperty(value = " 床型key  t_item.scope_value {1:大床房,2:双人床,3:海景房}" +
            "例如:取值为1")
    private Integer bedTypeKey;
    @ApiModelProperty(value = " 床型名称 例如大床房")
    private String bedTypeName;
    @ApiModelProperty(value = " 房型 1日租房 2分时房" +
            " ")
    private Long roomTypeId;
    @ApiModelProperty(value = " 日租房或分时房")
    private String roomTypeName;
    @ApiModelProperty(value = " 房间描述")
    private String roomDesc;
    @ApiModelProperty(value = " 入住人数")
    private Integer numberPeople;
    @ApiModelProperty(value = " 商品规格模 cs_template.id")
    private Long temId;
    @ApiModelProperty(value = " 配置项/规格属性[{" +
            " 基础设施: [{" +
            "  有停车场: 是" +
            " }, {" +
            "  有健身房: 是" +
            " }]" +
            "}, {" +
            " 配套设置: [{" +
            "  wifi: 有" +
            " }, {" +
            "  热水: 有" +
            " }]" +
            "}]   存放系统定义的商品规格字段，主要用于显示")
    private String itemAttr;
    @ApiModelProperty(value = " 配置项/规格属性-扩展字段 {是否含有表演:是,}用户可自己宽展商品的展示属性(无分组)")
    private String itemAttrExpand;
    @ApiModelProperty(value = " 房间状态 1:空闲 2：入住 3：维修")
    private Integer roomStatus;
    @ApiModelProperty(value = " 上架下架 0:下架 1：上架")
    private Integer saleStatus;
    @ApiModelProperty(value = " 删除状态 0未删除 1删除")
    private Integer delStatus;
    @ApiModelProperty(value = " 创建时间")
    private Date createTime;
    @ApiModelProperty(value = " 面积")
    private String area;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getGhcId() {
        return ghcId;
    }

    public void setGhcId(Long ghcId) {
        this.ghcId = ghcId;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName == null ? null : gName.trim();
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getBuId() {
        return buId;
    }

    public void setBuId(Long buId) {
        this.buId = buId;
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

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
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

    public Integer getNumberPeople() {
        return numberPeople;
    }

    public void setNumberPeople(Integer numberPeople) {
        this.numberPeople = numberPeople;
    }

    public Long getTemId() {
        return temId;
    }

    public void setTemId(Long temId) {
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

    public Integer getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(Integer roomStatus) {
        this.roomStatus = roomStatus;
    }

    public Integer getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(Integer saleStatus) {
        this.saleStatus = saleStatus;
    }

    public Integer getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}