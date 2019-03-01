package com.gbqd.pojo.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Transient;

import java.util.Date;
import java.util.List;

public class CsStoreComment {
    @ApiModelProperty(value = " 主键ID")
    private Long id;
    @ApiModelProperty(value = " 店铺ID")
    private Long storeId;
    @ApiModelProperty(value = " 用户ID")
    private String memberId;
    @ApiModelProperty(value = " 评论内容")
    private String content;
    @ApiModelProperty(value = " 卫生打分")
    private Double healthScore;
    @ApiModelProperty(value = " 环境打分")
    private Double environmentScore;
    @ApiModelProperty(value = " 服务打分")
    private Double serviceScore;
    @ApiModelProperty(value = " 点赞数")
    private Integer clickPraise;
    @ApiModelProperty(value = " 评价的订单 一般在订单完成后评价打分")
    private Long orderId;
    @ApiModelProperty(value = " 创建日期")
    private Date createTime;
    @ApiModelProperty(value = " 评价属性(1.好评 2 中评. 3差评 )")
    private Integer goodOrBad;
    @ApiModelProperty(value = " 总评分")
    @Transient
    private Double score;
    @ApiModelProperty(value = " 一条评论下面有多少条回复")
    @Transient
    private Integer commentNumber;
    @ApiModelProperty(value = " 图片集合")
    @Transient
    private List<CsStoreCommentComponent>pictureList;

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

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Double getHealthScore() {
        return healthScore;
    }

    public void setHealthScore(Double healthScore) {
        this.healthScore = healthScore;
    }

    public Double getEnvironmentScore() {
        return environmentScore;
    }

    public void setEnvironmentScore(Double environmentScore) {
        this.environmentScore = environmentScore;
    }

    public Double getServiceScore() {
        return serviceScore;
    }

    public void setServiceScore(Double serviceScore) {
        this.serviceScore = serviceScore;
    }

    public Integer getClickPraise() {
        return clickPraise;
    }

    public void setClickPraise(Integer clickPraise) {
        this.clickPraise = clickPraise;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getGoodOrBad() {
        return goodOrBad;
    }

    public void setGoodOrBad(Integer goodOrBad) {
        this.goodOrBad = goodOrBad;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getCommentNumber() {
        return commentNumber;
    }

    public List<CsStoreCommentComponent> getPictureList() {
        return pictureList;
    }

    public void setCommentNumber(Integer commentNumber) {
        this.commentNumber = commentNumber;
    }

    public void setPictureList(List<CsStoreCommentComponent> pictureList) {
        this.pictureList = pictureList;
    }
}