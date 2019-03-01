package com.gbqd.pojo;

import java.util.Date;

public class CsBusinessHierarchy {
    private Long id;

    private String buCode;

    private Long pId;

    private String buName;

    private Integer buLevel;

    private Integer delStatus;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuCode() {
        return buCode;
    }

    public void setBuCode(String buCode) {
        this.buCode = buCode == null ? null : buCode.trim();
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getBuName() {
        return buName;
    }

    public void setBuName(String buName) {
        this.buName = buName == null ? null : buName.trim();
    }

    public Integer getBuLevel() {
        return buLevel;
    }

    public void setBuLevel(Integer buLevel) {
        this.buLevel = buLevel;
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
}