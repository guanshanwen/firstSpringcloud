package com.gbqd.pojo.goods;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Transient;

import java.util.Date;
@ApiModel(value = "类")
public class CsStore {
    @ApiModelProperty(value = " 店铺ID")
    private Long id;
    @ApiModelProperty(value = "自定义的规则号")
    private String storeCode;
    @ApiModelProperty(value = "云店名称")
    private String storeName;
    @ApiModelProperty(value = "用户ID")
    private String memberId;
    @ApiModelProperty(value = "用户姓名")
    private String memberName;
    @ApiModelProperty(value = "门店类目一级ID")
    private Long buPId;
    @ApiModelProperty(value = "门店类目二级ID")
    private Long buId;
    @ApiModelProperty(value = "门店logo")
    private String storeImageLog;
    @ApiModelProperty(value = "云店描述")
    private String storeDesc;
    @ApiModelProperty(value = "云店经营许可证图片链接")
    private String storeImageXkz;
    @ApiModelProperty(value = "店铺联系方式")
    private String storeTel;
    @ApiModelProperty(value = "申请人身份证号")
    private String identyNo;
    @ApiModelProperty(value = "申请人电话号码")
    private String telepNo;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "店铺详细地址")
    private String address;
    @ApiModelProperty(value = "省编码")
    private String provinceCode;
    @ApiModelProperty(value = "市编码")
    private String cityCode;
    @ApiModelProperty(value = "区/县编码")
    private String zoneCode;
    @ApiModelProperty(value = "企业名称")
    private String companyName;
    @ApiModelProperty(value = "法人名称")
    private String legalPerson;
    @ApiModelProperty(value = "身份证正面url")
    private String idcardImageFront;
    @ApiModelProperty(value = "身份证反面url")
    private String idcardImageOpposite;
    @ApiModelProperty(value = "执照图片")
    private String licenseImgage;
    @ApiModelProperty(value = "营业执照号")
    private String licenseNo;
    @ApiModelProperty(value = "审核流水")
    private Long appId;
    @ApiModelProperty(value = "审核状态 0:审核中 1：审核通过 2：驳回")
    private Integer appStatus;
    @ApiModelProperty(value = "人民币账户金额")
    private Double rmbAccount;
    @ApiModelProperty(value = "vb账户金额")
    private Double vbAccount;
    @ApiModelProperty(value = "云店经度坐标")
    private String longitude;
    @ApiModelProperty(value = "云店纬度坐标")
    private String latitude;
    @ApiModelProperty(value = "云店综合分值")
    private Double creditScore;
    @ApiModelProperty(value = "卫生打分")
    private Double healthScore;
    @ApiModelProperty(value = "环境打分")
    private Double environmentScore;
    @ApiModelProperty(value = "服务打分")
    private Double serviceScore;
    @ApiModelProperty(value = "云店经营状态 1:营业中 2:打烊 3:歇业 4:封停/冻结")
    private Integer managementState;
    @ApiModelProperty(value = "账户剩余金额，包含冻结金额")
    private Double availableAccount;
    @ApiModelProperty(value = "冻结金额，信用卡充值时会锁定30% 只有信用卡才有冻结金额")
    private Double frozenAccount;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @ApiModelProperty(value = "删除状态 0未删除 1删除")
    private Integer delStatus;
    @ApiModelProperty(value = "人均价格")
    private Integer averagePrice;
    @ApiModelProperty(value = "是否允许分时(0.否.1.是)")
    private Integer orSharingTime;
    @ApiModelProperty(value = "是否公司自有(0.否 1.是)")
    private Integer orTheirOwn;
    @ApiModelProperty(value = "最低时间")
    private Double minimumTime;
    @ApiModelProperty(value = "退款比例")
    private Double refundRate;
    @ApiModelProperty(value = "退款政策")
    private String refundPolicy;
    @ApiModelProperty(value = "推荐")
    private String recommended;
    @ApiModelProperty(value = "特色")
    private String features;
    @ApiModelProperty(value = "简介")
    private String introduction;
    @Transient
    @ApiModelProperty(value = "门店类目一级")
    private String fname;
    @Transient
    @ApiModelProperty(value = "门店类目二级")
    private String tname;
    @ApiModelProperty(value = "店铺折扣")
    private Double discount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode == null ? null : storeCode.trim();
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    public Long getBuPId() {
        return buPId;
    }

    public void setBuPId(Long buPId) {
        this.buPId = buPId;
    }

    public Long getBuId() {
        return buId;
    }

    public void setBuId(Long buId) {
        this.buId = buId;
    }

    public String getStoreImageLog() {
        return storeImageLog;
    }

    public void setStoreImageLog(String storeImageLog) {
        this.storeImageLog = storeImageLog == null ? null : storeImageLog.trim();
    }

    public String getStoreDesc() {
        return storeDesc;
    }

    public void setStoreDesc(String storeDesc) {
        this.storeDesc = storeDesc == null ? null : storeDesc.trim();
    }

    public String getStoreImageXkz() {
        return storeImageXkz;
    }

    public void setStoreImageXkz(String storeImageXkz) {
        this.storeImageXkz = storeImageXkz == null ? null : storeImageXkz.trim();
    }

    public String getStoreTel() {
        return storeTel;
    }

    public void setStoreTel(String storeTel) {
        this.storeTel = storeTel == null ? null : storeTel.trim();
    }

    public String getIdentyNo() {
        return identyNo;
    }

    public void setIdentyNo(String identyNo) {
        this.identyNo = identyNo == null ? null : identyNo.trim();
    }

    public String getTelepNo() {
        return telepNo;
    }

    public void setTelepNo(String telepNo) {
        this.telepNo = telepNo == null ? null : telepNo.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode == null ? null : zoneCode.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson == null ? null : legalPerson.trim();
    }

    public String getIdcardImageFront() {
        return idcardImageFront;
    }

    public void setIdcardImageFront(String idcardImageFront) {
        this.idcardImageFront = idcardImageFront == null ? null : idcardImageFront.trim();
    }

    public String getIdcardImageOpposite() {
        return idcardImageOpposite;
    }

    public void setIdcardImageOpposite(String idcardImageOpposite) {
        this.idcardImageOpposite = idcardImageOpposite == null ? null : idcardImageOpposite.trim();
    }

    public String getLicenseImgage() {
        return licenseImgage;
    }

    public void setLicenseImgage(String licenseImgage) {
        this.licenseImgage = licenseImgage == null ? null : licenseImgage.trim();
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo == null ? null : licenseNo.trim();
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Integer getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(Integer appStatus) {
        this.appStatus = appStatus;
    }

    public Double getRmbAccount() {
        return rmbAccount;
    }

    public void setRmbAccount(Double rmbAccount) {
        this.rmbAccount = rmbAccount;
    }

    public Double getVbAccount() {
        return vbAccount;
    }

    public void setVbAccount(Double vbAccount) {
        this.vbAccount = vbAccount;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public Double getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Double creditScore) {
        this.creditScore = creditScore;
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

    public Integer getManagementState() {
        return managementState;
    }

    public void setManagementState(Integer managementState) {
        this.managementState = managementState;
    }

    public Double getAvailableAccount() {
        return availableAccount;
    }

    public void setAvailableAccount(Double availableAccount) {
        this.availableAccount = availableAccount;
    }

    public Double getFrozenAccount() {
        return frozenAccount;
    }

    public void setFrozenAccount(Double frozenAccount) {
        this.frozenAccount = frozenAccount;
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

    public Integer getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }

    public Integer getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Integer averagePrice) {
        this.averagePrice = averagePrice;
    }

    public Integer getOrSharingTime() {
        return orSharingTime;
    }

    public void setOrSharingTime(Integer orSharingTime) {
        this.orSharingTime = orSharingTime;
    }

    public Integer getOrTheirOwn() {
        return orTheirOwn;
    }

    public void setOrTheirOwn(Integer orTheirOwn) {
        this.orTheirOwn = orTheirOwn;
    }

    public Double getMinimumTime() {
        return minimumTime;
    }

    public void setMinimumTime(Double minimumTime) {
        this.minimumTime = minimumTime;
    }

    public Double getRefundRate() {
        return refundRate;
    }

    public void setRefundRate(Double refundRate) {
        this.refundRate = refundRate;
    }

    public String getRefundPolicy() {
        return refundPolicy;
    }

    public void setRefundPolicy(String refundPolicy) {
        this.refundPolicy = refundPolicy == null ? null : refundPolicy.trim();
    }

    public String getRecommended() {
        return recommended;
    }

    public void setRecommended(String recommended) {
        this.recommended = recommended == null ? null : recommended.trim();
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features == null ? null : features.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getFname() {
        return fname;
    }

    public String getTname() {
        return tname;
    }

    public void setFnamen(String fname) {
        this.fname = fname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}