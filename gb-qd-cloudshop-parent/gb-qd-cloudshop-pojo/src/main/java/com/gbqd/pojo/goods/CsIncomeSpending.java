package com.gbqd.pojo.goods;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Transient;

import java.util.Date;

public class CsIncomeSpending {
    @ApiModelProperty(value = " 主键ID")
    private Long id;
    @ApiModelProperty(value = " 其他表的主键(比如订单表主键来关联订单表)")
    private Long mainId;
    @ApiModelProperty(value = " 销售额")
    private Double sales;
    @ApiModelProperty(value = " 交易产生的VB")
    private Double vbMoney;
    @ApiModelProperty(value = " 收入支出(1.收入 2.支出)")
    private Integer type;
    @ApiModelProperty(value = " 创建时间")
    private Date createTime;
    @ApiModelProperty(value = " 店铺ID")
    private Long storeId;
    @ApiModelProperty(value = " 月收益额")
    @Transient
    private  Double earnings;
    @ApiModelProperty(value = " 总余额")
    @Transient
    private  Double balance;
    @ApiModelProperty(value = " 总销售额")
    @Transient
    private  Double totalSales;
    @ApiModelProperty(value = " 总收益额")
    @Transient
    private  Double totalEarnings;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMainId() {
        return mainId;
    }

    public void setMainId(Long mainId) {
        this.mainId = mainId;
    }

    public Double getSales() {
        return sales;
    }

    public void setSales(Double sales) {
        this.sales = sales;
    }

    public Double getVbMoney() {
        return vbMoney;
    }

    public void setVbMoney(Double vbMoney) {
        this.vbMoney = vbMoney;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Double getEarnings() {
        return earnings;
    }

    public void setEarnings(Double earnings) {
        this.earnings = earnings;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getTotalSales() {
        return totalSales;
    }

    public Double getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalSales(Double totalSales) {
        this.totalSales = totalSales;
    }

    public void setTotalEarnings(Double totalEarnings) {
        this.totalEarnings = totalEarnings;
    }
}