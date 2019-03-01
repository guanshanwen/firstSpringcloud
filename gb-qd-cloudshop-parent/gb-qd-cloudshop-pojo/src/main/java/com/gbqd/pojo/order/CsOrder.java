package com.gbqd.pojo.order;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class CsOrder {
    @ApiModelProperty(value = " 主键ID")
    private Long id;
    @ApiModelProperty(value = " 有规则的订单号-适应订单修改。在订单多次修改时 可作为批次号。")
    private String orderCode;
    @ApiModelProperty(value = " 订单金额")
    private Double amountMoney;
    @ApiModelProperty(value = " 相对于用户状态 0:未付款 1:已付款  2:退款中 3:完成退款 4:已完成 ")
    private Integer customerStatus;
    @ApiModelProperty(value = " 相对于商家状态 订单状态 0:未付款 1:已付款 2:待取消(退款) 3:已取消 4:已完成")
    private Integer merchantStatus;
    @ApiModelProperty(value = " 创建时间 下单时间")
    private Date createTime;
    @ApiModelProperty(value = " 付款方式 1:银联 2:微信 3:支付宝 4:现金 5:其他")
    private Integer payType;
    @ApiModelProperty(value = " 是否含有发票0:无 1:有")
    private Integer ifInvoice;
    @ApiModelProperty(value = " 店铺id")
    private Long storeId;
    @ApiModelProperty(value = " 用户ID")
    private String memberId;
    @ApiModelProperty(value = " 折扣数")
    private Double discountNum;
    @ApiModelProperty(value = " 订单开始时间-可入住的时间")
    private Date orderBeginTime;
    @ApiModelProperty(value = " 订单结束时间")
    private Date endBeginTime;
    @ApiModelProperty(value = " 订单预留电话号码 默认可为用户电话")
    private String telephone;
    @ApiModelProperty(value = " 订单类型: 10001:定时房 10002:分时房 20001:即时付款 30001:退款")
    private Integer orderType;
    @ApiModelProperty(value = " 订单状态 1:有效 0:作废(待定)")
    private Integer status;
    @ApiModelProperty(value = " 实际入住时间")
    private Date actualInTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public Double getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(Double amountMoney) {
        this.amountMoney = amountMoney;
    }

    public Integer getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(Integer customerStatus) {
        this.customerStatus = customerStatus;
    }

    public Integer getMerchantStatus() {
        return merchantStatus;
    }

    public void setMerchantStatus(Integer merchantStatus) {
        this.merchantStatus = merchantStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getIfInvoice() {
        return ifInvoice;
    }

    public void setIfInvoice(Integer ifInvoice) {
        this.ifInvoice = ifInvoice;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    public Double getDiscountNum() {
        return discountNum;
    }

    public void setDiscountNum(Double discountNum) {
        this.discountNum = discountNum;
    }

    public Date getOrderBeginTime() {
        return orderBeginTime;
    }

    public void setOrderBeginTime(Date orderBeginTime) {
        this.orderBeginTime = orderBeginTime;
    }

    public Date getEndBeginTime() {
        return endBeginTime;
    }

    public void setEndBeginTime(Date endBeginTime) {
        this.endBeginTime = endBeginTime;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getActualInTime() {
        return actualInTime;
    }

    public void setActualInTime(Date actualInTime) {
        this.actualInTime = actualInTime;
    }
}