package com.gbqd.pojo.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Transient;

import java.util.Date;

public class CsStoreCommentReply {
    @ApiModelProperty(value = " 主键ID")
    private Long id;
    @ApiModelProperty(value = " 支持多层评价 评价父级")
    private Long pId;
    @ApiModelProperty(value = " 对应的评价id")
    private Integer commentId;
    @ApiModelProperty(value = " 评价人id")
    private String memberId;
    @ApiModelProperty(value = " 评价内容")
    private String content;
    @ApiModelProperty(value = " 点赞数")
    private Integer clickPraise;
    @ApiModelProperty(value = " 创建日期")
    private Date createTime;
    @ApiModelProperty(value = " 回复评论人的id")
    private String replyMemberId;
    @ApiModelProperty(value = " 评论人昵称")
    @Transient
    private  String memberName;
    @ApiModelProperty(value = " 回复人昵称")
    @Transient
    private  String replyMemberName;
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

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getClickPraise() {
        return clickPraise;
    }

    public void setClickPraise(Integer clickPraise) {
        this.clickPraise = clickPraise;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getReplyMemberId() {
        return replyMemberId;
    }

    public void setReplyMemberId(String replyMemberId) {
        this.replyMemberId = replyMemberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getReplyMemberName() {
        return replyMemberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setReplyMemberName(String replyMemberName) {
        this.replyMemberName = replyMemberName;
    }
}