package com.gbqd.service.goods;

import com.alibaba.fastjson.JSONObject;
import com.gbqd.common.utils.GenericResponse;
import com.gbqd.common.utils.ResultCode;
import com.gbqd.pojo.goods.*;

import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 企业认证接口
 *
 * @author MrWen
 * @create 2019-01-23-10:55
 */
public interface StoreCertificationService {

    /**
     * @param companyName 企业名称
     * @param legalPerson 法人名称
     * @param licenseNo   营业执照号
     * @param storeName   商铺名称
     * @param memberId    用户ID
     * @param memberName  用户姓名
     * @param buPId       对应的行业id 一级
     * @param buId        对应的行业id 二级
     * @@describe: 企业认证接口
     * @author: MrWen
     * @return: com.alibaba.fastjson.JSONObject
     * @date: 2019/1/23 11:31
     */
    public ResultCode insertStroeCertification(
            @RequestParam(value = "companyName", required = true) String companyName,
            @RequestParam(value = "legalPerson", required = true) String legalPerson,
            @RequestParam(value = "licenseNo", required = true) String licenseNo,
            @RequestParam(value = "storeName", required = true) String storeName,
            @RequestParam(value = "memberId", required = true) String memberId,
            @RequestParam(value = "memberName", required = true) String memberName,
            @RequestParam(value = "buPId", required = true) Long buPId,
            @RequestParam(value = "buId", required = true) Long buId,
            @RequestParam(value = "storeId", required = false) Long storeId,
            MultipartHttpServletRequest request);

    /**
     * @param memberId 企业店铺主键ID
     * @@describe: 通过用户ID 查询该用户名下认证信息
     * @author: MrWen
     * @return: com.alibaba.fastjson.JSONObject
     * @date: 2019/1/24 10:30
     */
    public ResultCode<List<CsStore>> getStroeCertificationList(@RequestParam(value = "memberId", required = true) String memberId);

    /**
     * @param id 企业店铺主键ID
     * @@describe: 查询企业资料详情
     * @author: MrWen
     * @return: com.alibaba.fastjson.JSONObject
     * @date: 2019/1/24 10:30
     */
    public ResultCode<CsStore> stroeDetails(@RequestParam(value = "id", required = true) Long id);

    /**
     * @param id            店铺ID
     * @param storeName     云店名称
     * @param provinceCode  省编码
     * @param cityCode      市编码
     * @param zoneCode      区/县编码
     * @param address       店铺详细地址
     * @param longitude     云店经度坐标
     * @param latitude      云店纬度坐标
     * @param storeTel      店铺的固定联系方式
     * @param averagePrice  人均价格
     * @param orSharingTime 是否允许分时(0.否.1.是)
     * @param minimumTime   最低时间
     * @param refundRate    退款比例
     * @param refundPolicy  退款政策
     * @param recommended   推荐
     * @param features      特色
     * @param introduction  简介
     * @param file          logo图片
     * @@describe: 店铺设置和修改
     * @author: MrWen
     * @return: com.alibaba.fastjson.JSONObject
     * @date: 2019/1/24 14:54
     */
    public ResultCode stroeUpdate(@RequestParam(value = "id", required = true) Long id,
                                  @RequestParam(value = "storeName", required = false) String storeName,
                                  @RequestParam(value = "provinceCode", required = false) String provinceCode,
                                  @RequestParam(value = "cityCode", required = false) String cityCode,
                                  @RequestParam(value = "zoneCode", required = false) String zoneCode,
                                  @RequestParam(value = "address", required = false) String address,
                                  @RequestParam(value = "longitude", required = false) String longitude,
                                  @RequestParam(value = "latitude", required = false) String latitude,
                                  @RequestParam(value = "storeTel", required = false) String storeTel,
                                  @RequestParam(value = "averagePrice", required = false) Integer averagePrice,
                                  @RequestParam(value = "orSharingTime", required = false) Integer orSharingTime,
                                  @RequestParam(value = "minimumTime", required = false) Double minimumTime,
                                  @RequestParam(value = "refundRate", required = false) Double refundRate,
                                  @RequestParam(value = "refundPolicy", required = false) String refundPolicy,
                                  @RequestParam(value = "recommended", required = false) String recommended,
                                  @RequestParam(value = "features", required = false) String features,
                                  @RequestParam(value = "introduction", required = false) String introduction,
                                  @RequestParam(value = "file", required = false) MultipartFile file);

    /**
     * @param id 店铺ID
     * @@describe: 店铺设置详情
     * @author: MrWen
     * @return: com.alibaba.fastjson.JSONObject
     * @date: 2019/1/24 14:54
     */
    public ResultCode<CsStore> storeSetupDetails(@RequestParam(value = "id", required = false) Long id);


    /**
     * @param groupName      分组名称
     * @param groupTitle     分组标题
     * @param groupDesc      分组描述
     * @param sort           排序
     * @param createMemberId 创建人ID
     * @param storeId        店铺ID
     * @@describe: 店铺相册分组接口
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/1/28 16:22
     */
    public ResultCode imageGroup(@RequestParam(value = "groupName", required = false) String groupName,
                                 @RequestParam(value = "groupTitle", required = false) String groupTitle,
                                 @RequestParam(value = "groupDesc", required = false) String groupDesc,
                                 @RequestParam(value = "sort", required = false) Integer sort,
                                 @RequestParam(value = "createMemberId", required = false) String createMemberId,
                                 @RequestParam(value = "storeId", required = true) Long storeId);

    /**
     * @param storeId        店铺id
     * @param groupId        图片组id
     * @param createMemberId 创建人id
     * @param request        图片集合
     * @@describe: 店铺相册上传图片
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/1/28 16:40
     */
    public ResultCode inserImage(@RequestParam(value = "storeId", required = true) Long storeId,
                                 @RequestParam(value = "groupId", required = true) Long groupId,
                                 @RequestParam(value = "createMemberId", required = true) String createMemberId,
                                 MultipartHttpServletRequest request);

    /**
     * @param storeId 店铺id
     * @@describe: List店铺分组集合(无分页)
     * @author: MrWen
     * @return: List
     * @date: 2019/1/28 16:40
     */
    public ResultCode<List<CsStoreImageGroup>> imageGroupList(@RequestParam(value = "storeId", required = true) Long storeId);
    /**
     * @param storeId 店铺id
     * @@describe: List店铺分组集合(有分页给PC端用)
     * @author: MrWen
     * @return: List
     * @date: 2019/1/28 16:40
     */
    public ResultCode<PageInfo<CsStoreImageGroup>> imageGroupListPc(@RequestParam(value = "storeId", required = true) Long storeId,
                                                                    @RequestParam(value = "PageNum", required = false, defaultValue = "1") Integer PageNum,
                                                                    @RequestParam(value = "pageSize", required = false,defaultValue = "10") Integer pageSize);

    /**
     * @param groupId 图片相册组主键ID
     * @@describe: 相册详情
     * @author: MrWen
     * @return: java.util.List<com.gbqd.pojo.goods.CsStoreImage>
     * @date: 2019/1/29 9:14
     */
    public ResultCode<List<CsStoreImage>> storeImageList(@RequestParam(value = "groupId", required = true) Long groupId);

    /**
     * @param id 分组主键ID
     * @@describe: 删除分组信息
     * @author: MrWen
     * @return: ResultCode
     * @date: 2019/1/29 9:14
     */
    public ResultCode deleteImageGroup(@RequestParam(value = "id", required = true) Long id);

    /**
     * @param storeId 店铺ID
     * @param month   月份
     * @@describe: 电子钱包统计店铺收益和余额
     * @author: MrWen
     * @return: com.alibaba.fastjson.JSONObject
     * @date: 2019/1/30 16:52
     */
    public ResultCode<CsIncomeSpending> earningsBalance(@RequestParam(value = "storeId", required = true) Long storeId,
                                                        @RequestParam(value = "month", required = false) String month);

    /**
     * @param id      需要变化位置组的主键ID
     * @param targeId 目标位置id
     * @param orRear 是否置底(1.是 0.否)
     * @@describe: 批量更新排序
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/13 16:41
     */
    public ResultCode updateSort(@RequestParam(value = "id") Long id,
                                 @RequestParam(value = "targeId") Long targeId,
                                 @RequestParam(value = "orRear") int orRear);
    /**
     * @param storeId 店铺ID
     * @@describe: 登陆后选择店铺返回店铺信息
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/22 15:59
     */
    public ResultCode<CsStore>  getStoreDetails(@RequestParam(value = "storeId") Long storeId)throws ParseException;
}
