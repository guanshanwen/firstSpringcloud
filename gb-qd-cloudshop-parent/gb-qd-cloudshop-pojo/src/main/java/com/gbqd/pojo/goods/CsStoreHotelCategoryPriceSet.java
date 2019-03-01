package com.gbqd.pojo.goods;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class CsStoreHotelCategoryPriceSet {
    @ApiModelProperty(value = " 主键ID")
    private Long id;
    @ApiModelProperty(value = " 客房的主键id")
    private Long hotelCategoryId;
    @ApiModelProperty(value = " 房间价格")
    private Double money;
    @ApiModelProperty(value = " vb的比例")
    private Double vb;
    @ApiModelProperty(value = " 开始日期")
    private Date beginTime;
    @ApiModelProperty(value = " 结束日期")
    private Date endTime;
    @ApiModelProperty(value = " 创建时间")
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHotelCategoryId() {
        return hotelCategoryId;
    }

    public void setHotelCategoryId(Long hotelCategoryId) {
        this.hotelCategoryId = hotelCategoryId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getVb() {
        return vb;
    }

    public void setVb(Double vb) {
        this.vb = vb;
    }
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}