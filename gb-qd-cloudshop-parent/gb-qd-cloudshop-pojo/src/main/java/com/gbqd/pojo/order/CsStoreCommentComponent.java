package com.gbqd.pojo.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class CsStoreCommentComponent {
    @ApiModelProperty(value = " 主键ID")
    private Long id;
    @ApiModelProperty(value = " 图片类型 1:图片 2:视频")
    private Integer type;
    @ApiModelProperty(value = " 图片或视频链接地址")
    private String componentUrl;
    @ApiModelProperty(value = " 评论id")
    private Long commentId;
    @ApiModelProperty(value = " 创建日期")
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getComponentUrl() {
        return componentUrl;
    }

    public void setComponentUrl(String componentUrl) {
        this.componentUrl = componentUrl == null ? null : componentUrl.trim();
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}