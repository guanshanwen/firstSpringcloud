package com.gbqd.pojo.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gbqd.pojo.goods.CsStore;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

public class CsMember {
    @ApiModelProperty(value = " 主键ID")
    private Long id;
    @ApiModelProperty(value = " 用户ID")
    private String memberId;
    @ApiModelProperty(value = " 0无效 1有效")
    private Integer sysStatus;
    @ApiModelProperty(value = " 创建时间")
    private Date createTime;
    @ApiModelProperty(value = " 登陆时间")
    private Date logTime;
    @ApiModelProperty(value = " 下线时间")
    private Date logOutTime;
    @ApiModelProperty(value = " 是否实名认证 0:未认证 1:已认证")
    private Integer isAuth;
    @ApiModelProperty(value = " 邀请码 分润使用 兼容数字及字母")
    private String invitationCode;
    @ApiModelProperty(value = " 邀请人ID")
    private String inviterId;
    @ApiModelProperty(value = " 昵称")
    private String nickname;
    @ApiModelProperty(value = " 电话")
    private String phone;
    @ApiModelProperty(value = " 性别(1.男 2.女)")
    private Integer sex;
    @ApiModelProperty(value = " 头像URL")
    private String headPortraitUrl;
    @ApiModelProperty(value = " 密码")
    private String pwd;
    @ApiModelProperty(value = " 身份证号")
    private String idNo;
    @ApiModelProperty(value = " 家庭住址")
    private String address;
    @ApiModelProperty(value = " 真实姓名")
    private String realName;
    @ApiModelProperty(value = " 店铺列表")
    private List<CsStore> memberStroeList;
    @ApiModelProperty(value = " 用户云店列表")
    public List<CsStore> getMemberStroeList() {
        return memberStroeList;
    }

    public void setMemberStroeList(List<CsStore> memberStroeList) {
        this.memberStroeList = memberStroeList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    public Integer getSysStatus() {
        return sysStatus;
    }

    public void setSysStatus(Integer sysStatus) {
        this.sysStatus = sysStatus;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getLogOutTime() {
        return logOutTime;
    }

    public void setLogOutTime(Date logOutTime) {
        this.logOutTime = logOutTime;
    }

    public Integer getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(Integer isAuth) {
        this.isAuth = isAuth;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode == null ? null : invitationCode.trim();
    }

    public String getInviterId() {
        return inviterId;
    }

    public void setInviterId(String inviterId) {
        this.inviterId = inviterId == null ? null : inviterId.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getHeadPortraitUrl() {
        return headPortraitUrl;
    }

    public void setHeadPortraitUrl(String headPortraitUrl) {
        this.headPortraitUrl = headPortraitUrl == null ? null : headPortraitUrl.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo == null ? null : idNo.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }
}