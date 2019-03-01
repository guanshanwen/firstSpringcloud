package com.gbqd.pojo.goods;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Transient;

import java.util.Date;

    public  class CsStoreMember {
        @ApiModelProperty(value = " 主键ID")
        private Long id;
        @ApiModelProperty(value = " 店铺ID")
        private Long storeId;
        @ApiModelProperty(value = " 用户")
        private String memberId;
        @ApiModelProperty(value = " VIP级别表主键ID")
        private Long vipId;
        @ApiModelProperty(value = " 用户角色(1.会员 2.员工)")
        private Integer memberRole;
        @ApiModelProperty(value = " 创建时间")
        private Date createTime;
        @ApiModelProperty(value = " 修改时间")
        private Date updateTime;
        @ApiModelProperty(value = " 删除状态 0未删除 1删除")
        private Byte delStatus;
        @Transient
        @ApiModelProperty(value = " 电话")
        private  String phone;
        @Transient
        @ApiModelProperty(value = " 姓名")
        private  String nickName;
        @Transient
        @ApiModelProperty(value = " 性别(1.男 2.女)")
        private  Integer sex;
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

        public Long getVipId() {
            return vipId;
        }

        public void setVipId(Long vipId) {
            this.vipId = vipId;
        }

        public Integer getMemberRole() {
            return memberRole;
        }

        public void setMemberRole(Integer memberRole) {
            this.memberRole = memberRole;
        }
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
        public Date getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
        }

        public Byte getDelStatus() {
            return delStatus;
        }

        public void setDelStatus(Byte delStatus) {
            this.delStatus = delStatus;
        }

        public String getPhone() {
            return phone;
        }

        public String getNickName() {
            return nickName;
        }

        public Integer getSex() {
            return sex;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public void setSex(Integer sex) {
            this.sex = sex;
        }
    }