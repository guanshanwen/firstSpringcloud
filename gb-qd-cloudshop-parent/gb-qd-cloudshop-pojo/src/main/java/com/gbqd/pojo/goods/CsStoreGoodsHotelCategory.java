package com.gbqd.pojo.goods;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import sun.security.provider.certpath.PKIXTimestampParameters;

import java.util.Date;

public class CsStoreGoodsHotelCategory {
    @ApiModelProperty(value = " 主键ID")
    private Long id;
    @ApiModelProperty(value = " 父类id")
    private Long pId;
    @ApiModelProperty(value = " 商品类型名称 kity主题房 例如在大床房下配置房屋种类")
    private String gcName;
    @ApiModelProperty(value = " 商品类别 1:日租房 2:分时房")
    private Integer goodsType;
    @ApiModelProperty(value = " 所属层级 层级可实现排序、显示等功能")
    private Integer gcLevel;
    @ApiModelProperty(value = " 商铺code cs_store.id")
    private Long storeId;
    @ApiModelProperty(value = " 床型  {0:大床房,1:单人床,2:双人床}")
    private Integer bedType;
    @ApiModelProperty(value = " 分类价格 商品层级定义的价格")
    private Double gcPrice;
    @ApiModelProperty(value = "  VB价")
    private Double vbPrice;
    @ApiModelProperty(value = " 删除状态 1未删除 0删除")
    private Integer delStatus;
    @ApiModelProperty(value = " 创建时间")
    private Date createTime;
    @ApiModelProperty(value = " 是否上架(1.上架  0.下架)")
    private Integer shelves;
    /**
     * 房间型号
     */
    private String goodsTypeName;
    /**
     * 床型
     */
    private String bedTypeName;
    /**
     * 属性标签
     */
    private String itemAttr;
    /**
     * 图片
     */
    private String imageUrl;
    /**
     * vrUrl vr链接
     */
    @ApiModelProperty(value = " bedType对应的床型汉字 (大床房 小床房)展示用")
    private String bedCharacter;
    private String vrUrl;
    @ApiModelProperty(value = " 配置项编码,bed_type的code用来去字典表里找")
    private String itemCodeBedType;
    @ApiModelProperty(value = " 是否含早餐(1.是  0.否)")
    private Integer orBreakfast;
    @ApiModelProperty(value = " 早餐价格")
    private Double breakfastMoney;
    public String getVrUrl() {
        return vrUrl;
    }

    public void setVrUrl(String vrUrl) {
        this.vrUrl = vrUrl;
    }

    public String getItemAttr() {
        return itemAttr;
    }

    public void setItemAttr(String itemAttr) {
        this.itemAttr = itemAttr;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName;
    }

    public String getBedTypeName() {
        return bedTypeName;
    }

    public void setBedTypeName(String bedTypeName) {
        this.bedTypeName = bedTypeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getGcName() {
        return gcName;
    }

    public void setGcName(String gcName) {
        this.gcName = gcName == null ? null : gcName.trim();
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public Integer getGcLevel() {
        return gcLevel;
    }

    public void setGcLevel(Integer gcLevel) {
        this.gcLevel = gcLevel;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Integer getBedType() {
        return bedType;
    }

    public void setBedType(Integer bedType) {
        this.bedType = bedType;
    }

    public Double getGcPrice() {
        return gcPrice;
    }

    public void setGcPrice(Double gcPrice) {
        this.gcPrice = gcPrice;
    }

    public Double getVbPrice() {
        return vbPrice;
    }

    public void setVbPrice(Double vbPrice) {
        this.vbPrice = vbPrice;
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

    public String getItemCodeBedType() {
        return itemCodeBedType;
    }

    public Integer getOrBreakfast() {
        return orBreakfast;
    }

    public Double getBreakfastMoney() {
        return breakfastMoney;
    }

    public void setItemCodeBedType(String itemCodeBedType) {
        this.itemCodeBedType = itemCodeBedType;
    }

    public void setOrBreakfast(Integer orBreakfast) {
        this.orBreakfast = orBreakfast;
    }

    public void setBreakfastMoney(Double breakfastMoney) {
        this.breakfastMoney = breakfastMoney;
    }

    public String getBedCharacter() {
        return bedCharacter;
    }

    public void setBedCharacter(String bedCharacter) {
        this.bedCharacter = bedCharacter;
    }

    public Integer getShelves() {
        return shelves;
    }

    public void setShelves(Integer shelves) {
        this.shelves = shelves;
    }
}