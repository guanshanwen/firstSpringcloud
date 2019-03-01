package com.gbqd.pojo.goods;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class CsStoreBusinessHours {
    @ApiModelProperty(value = " 主键ID")
    private Long id;
    @ApiModelProperty(value = " 店铺ID")
    private Long storeId;
    @ApiModelProperty(value = " 是否重复(1.是 0.否)")
    private Integer orRepeat;

    @ApiModelProperty(value = " 开始时间")
    private String beginTime;

    @ApiModelProperty(value = " 结束时间")
    private String  endTime;
    @ApiModelProperty(value = " 日期(1,2,3,4,5,6,7)代表周一至周日")
    private String dateOf;
    @ApiModelProperty(value = " 创建时间")
    private Date createTime;
    @ApiModelProperty(value = " 开关(1.开  0.关)")
    private Integer onOff;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Integer getOrRepeat() {
        return orRepeat;
    }

    public void setOrRepeat(Integer orRepeat) {
        this.orRepeat = orRepeat;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String  beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String  endTime) {
        this.endTime = endTime;
    }

    public String getDateOf() {
        return dateOf;
    }

    public void setDateOf(String dateOf) {
        this.dateOf = dateOf == null ? null : dateOf.trim();
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getOnOff() {
        return onOff;
    }

    public void setOnOff(Integer onOff) {
        this.onOff = onOff;
    }
}