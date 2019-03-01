package com.gbqd.pojo.template;

import java.util.Date;

public class CsTemplateItem {
    private Long id;

    private Long temId;

    private String itemCode;

    private String itemDisplay;

    private Integer textType;

    private Integer textLength;

    private Integer textCanEmpt;

    private Integer selectNum;
    /**
     * 排序
     */
    private Integer sort;


    private Integer delStatus;

    private Date createTime;
    /**
     * 显示的键名称
     */
    private String itemName;
    /**
     * 取值范围
     */
    private String scopeValue;

    public String getScopeValue() {
        return scopeValue;
    }

    public void setScopeValue(String scopeValue) {
        this.scopeValue = scopeValue;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTemId() {
        return temId;
    }

    public void setTemId(Long temId) {
        this.temId = temId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode == null ? null : itemCode.trim();
    }

    public String getItemDisplay() {
        return itemDisplay;
    }

    public void setItemDisplay(String itemDisplay) {
        this.itemDisplay = itemDisplay == null ? null : itemDisplay.trim();
    }

    public Integer getTextType() {
        return textType;
    }

    public void setTextType(Integer textType) {
        this.textType = textType;
    }

    public Integer getTextLength() {
        return textLength;
    }

    public void setTextLength(Integer textLength) {
        this.textLength = textLength;
    }

    public Integer getTextCanEmpt() {
        return textCanEmpt;
    }

    public void setTextCanEmpt(Integer textCanEmpt) {
        this.textCanEmpt = textCanEmpt;
    }

    public Integer getSelectNum() {
        return selectNum;
    }

    public void setSelectNum(Integer selectNum) {
        this.selectNum = selectNum;
    }

    public Integer getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}