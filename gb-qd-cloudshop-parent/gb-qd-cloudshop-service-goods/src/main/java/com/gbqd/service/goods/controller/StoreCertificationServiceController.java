package com.gbqd.service.goods.controller;

import com.gbqd.common.utils.ResultCode;
import com.gbqd.pojo.goods.*;
import com.gbqd.service.goods.StoreCertificationService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @author MrWen
 * @create 2019-01-23-15:24
 */
@RestController
@RequestMapping("/store")
@Api(description = "企业认证和店铺管理")
public class StoreCertificationServiceController {
    @Autowired
    StoreCertificationService storeCertificationService;


    /**
     * @param companyName 企业名称
     * @param legalPerson 法人名称
     * @param licenseNo   营业执照号
     * @param storeName   商铺名称
     * @param memberId    用户ID
     * @param memberName  用户姓名
     * @param buPId       对应的行业id 一级
     * @param buId        对应的行业id 二级
     * @param storeId     店铺ID(如果没有 不用传)
     * @@describe: 企业认证接口
     * @author: MrWen
     * @return: com.alibaba.fastjson.JSONObject
     * @date: 2019/1/23 11:31
     */
    @ApiOperation(value = "企业认证提交", notes = "需要传递企业认证详细信息")

    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyName", value = "企业名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "legalPerson", value = "法人名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "licenseNo", value = "营业执照号", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "storeName", value = "商铺名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "memberId", value = "用户ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "memberName", value = "用户姓名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "buPId", value = "对应的行业id 一级", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "buId", value = "对应的行业id 二级", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "storeId", value = " 店铺ID(如果没有 不用传)  ", required = true, dataType = "Long", paramType = "query")

    })

    @RequestMapping(value = "/insertStore", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResultCode getMember(@RequestParam(value = "companyName", required = true) String companyName,
                                @RequestParam(value = "legalPerson", required = true) String legalPerson,
                                @RequestParam(value = "licenseNo", required = true) String licenseNo,
                                @RequestParam(value = "storeName", required = true) String storeName,
                                @RequestParam(value = "memberId", required = true) String memberId,
                                @RequestParam(value = "memberName", required = true) String memberName,
                                @RequestParam(value = "buPId", required = true) Long buPId,
                                @RequestParam(value = "buId", required = true) Long buId,
                                @RequestParam(value = "storeId", required = false) Long storeId,
                                @ApiIgnore MultipartHttpServletRequest request) {

        return storeCertificationService.insertStroeCertification(companyName, legalPerson, licenseNo, storeName, memberId, memberName, buPId, buId, storeId, request);

    }


    /**
     * @param id 企业店铺主键ID
     * @@describe: 查询企业资料详情
     * @author: MrWen
     * @return: com.alibaba.fastjson.JSONObject
     * @date: 2019/1/24 10:30
     */
    @ApiOperation(value = "企业资料", notes = "需要传递企业id")
    @ApiImplicitParam(name = "id", value = "企业店铺 id example:1524262555", required = true, dataType = "Long", paramType = "query")
    @RequestMapping(value = "/stroeDetails", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResultCode<CsStore> stroeDetails(Long id) {
        return storeCertificationService.stroeDetails(id);
    }


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
    @ApiOperation(value = "店铺信息修改和设置", notes = "需要传递店铺需要修改的信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "企业店铺 id example:1524262555", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "storeName", value = "云店名称", required = false, dataType = "String"),
            @ApiImplicitParam(name = "provinceCode", value = "省编码", required = false, dataType = "String"),
            @ApiImplicitParam(name = "cityCode", value = "市编码", required = false, dataType = "String"),
            @ApiImplicitParam(name = "zoneCode", value = "区/县编码", required = false, dataType = "String"),
            @ApiImplicitParam(name = "address", value = "店铺详细地址", required = false, dataType = "String"),
            @ApiImplicitParam(name = "longitude", value = "云店经度坐标", required = false, dataType = "String"),
            @ApiImplicitParam(name = "latitude", value = "云店纬度坐标", required = false, dataType = "String"),
            @ApiImplicitParam(name = "storeTel", value = "店铺的固定联系方式", required = false, dataType = "String"),
            @ApiImplicitParam(name = "averagePrice", value = "人均价格", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "orSharingTime", value = "是否允许分时(0.否.1.是)", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "minimumTime", value = "最低时间", required = false, dataType = "Double"),
            @ApiImplicitParam(name = "refundRate", value = "退款比例", required = false, dataType = "Double"),
            @ApiImplicitParam(name = "refundPolicy", value = "退款政策", required = false, dataType = "String"),
            @ApiImplicitParam(name = "recommended", value = "推荐", required = false, dataType = "String"),
            @ApiImplicitParam(name = "features", value = "特色", required = false, dataType = "String"),
            @ApiImplicitParam(name = "introduction", value = "简介", required = false, dataType = "String"),

    })

    @RequestMapping(value = "/stroeUpdate", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST/*,headers="content-type=multipart/form-data"*/)
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
                                  @RequestParam(value = "file", required = false) @ApiParam(value = "logo图片", required = false) MultipartFile file) {
        return storeCertificationService.stroeUpdate(id, storeName, provinceCode, cityCode, zoneCode, address, longitude, latitude, storeTel, averagePrice, orSharingTime, minimumTime, refundRate, refundPolicy, recommended, features, introduction, file);
    }


    /**
     * @param id 店铺ID
     * @@describe: 店铺设置详情
     * @author: MrWen
     * @return: com.alibaba.fastjson.JSONObject
     * @date: 2019/1/24 14:54
     */
    @ApiOperation(value = "店铺设置详情", notes = "需要传递店铺id")
    @ApiImplicitParam(name = "id", value = "企业店铺 id example:1524262555", required = true, dataType = "Long")
    @RequestMapping(value = "/storeSetupDetails", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResultCode<CsStore> storeSetupDetails(Long id) {
        return storeCertificationService.storeSetupDetails(id);
    }


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
    @ApiOperation(value = "店铺相册分组创建", notes = "需要分组相关信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupName", value = "分组名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "groupTitle", value = "分组标题", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "groupDesc", value = "分组描述", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "sort", value = "排序", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "createMemberId", value = "创建人ID", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "storeId", value = "店铺ID", required = true, dataType = "Long", paramType = "query"
            )
    })

    @RequestMapping(value = "/insertImageGroup", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResultCode imageGroup(@RequestParam(value = "groupName", required = true) String groupName,
                                 @RequestParam(value = "groupTitle", required = false) String groupTitle,
                                 @RequestParam(value = "groupDesc", required = false) String groupDesc,
                                 @RequestParam(value = "sort", required = false) Integer sort,
                                 @RequestParam(value = "createMemberId", required = false) String createMemberId,
                                 @RequestParam(value = "storeId", required = true) Long storeId) {
        return storeCertificationService.imageGroup(groupName, groupTitle, groupDesc, sort, createMemberId, storeId);
    }

    /**
     * @param memberId 企业店铺主键ID
     * @@describe: 通过用户ID 查询该用户名下认证信息
     * @author: MrWen
     * @return: com.alibaba.fastjson.JSONObject
     * @date: 2019/1/24 10:30
     */
    @ApiOperation(value = "查询该用户名下认证信息")
    @ApiImplicitParam(name = "memberId", value = "企业店铺主键ID", required = false, dataType = "String", paramType = "query")
    @RequestMapping(value = "/getStroeCertificationList", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResultCode<List<CsStore>> getStroeCertificationList(@RequestParam(value = "memberId", required = true) String memberId) {
        return storeCertificationService.getStroeCertificationList(memberId);
    }

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
    @ApiOperation(value = "店铺相册上传图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "storeId", value = "店铺 id example:1524262555", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "groupId", value = "图片组id", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "createMemberId", value = "创建人ID", required = true, dataType = "String", paramType = "query")
    })

    @RequestMapping(value = "/insertImage", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResultCode inserImage(@RequestParam(value = "storeId", required = false) Long storeId,
                                 @RequestParam(value = "groupId", required = false) Long groupId,
                                 @RequestParam(value = "createMemberId", required = false) String createMemberId,
                                 @ApiIgnore MultipartHttpServletRequest request) {
        return storeCertificationService.inserImage(storeId, groupId, createMemberId, request);
    }


    /**
     * @param storeId 店铺id
     * @@describe: List店铺分组集合
     * @author: MrWen
     * @return: List
     * @date: 2019/1/28 16:40
     */
    @ApiOperation(value = "List店铺分组集合(无分页)")
    @ApiImplicitParam(name = "storeId", value = "店铺 id example:1524262555 ", required = true, dataType = "Long", paramType = "query")
    @RequestMapping(value = "/imageGroupList", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResultCode<List<CsStoreImageGroup>> imageGroupList(@RequestParam(value = "storeId", required = true) Long storeId) {
        return storeCertificationService.imageGroupList(storeId);
    }


    /**
     * @param PageNum  页数
     * @param pageSize 每页展示多少条
     * @param storeId  店铺id
     * @@describe: List店铺分组集合
     * @author: MrWen
     * @return: List
     * @date: 2019/1/28 16:40
     */
    @ApiOperation(value = "List店铺分组集合(有分页给PC端用)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "PageNum", value = "页数", required = false, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页展示多少条", required = false, dataType = "int"),
            @ApiImplicitParam(name = "storeId", value = "店铺 id example:1524262555 ", required = true, dataType = "Long", paramType = "query")
    })
    @RequestMapping(value = "/imageGroupListPc", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResultCode<PageInfo<CsStoreImageGroup>> imageGroupListPc(@RequestParam(value = "storeId", required = true) Long storeId,
                                                                    @RequestParam(value = "PageNum", required = false, defaultValue = "1") Integer PageNum,
                                                                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return storeCertificationService.imageGroupListPc(storeId, PageNum, pageSize);
    }

    /**
     * @param groupId 图片相册组主键ID
     * @@describe: 相册详情
     * @author: MrWen
     * @return: java.util.List<com.gbqd.pojo.goods.CsStoreImage>
     * @date: 2019/1/29 9:14
     */
    @ApiOperation(value = "相册组详情")
    @ApiImplicitParam(name = "groupId", value = "图片相册组的主键ID", required = true, dataType = "Long", paramType = "query")
    @RequestMapping(value = "/storeImageList", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResultCode<List<CsStoreImage>> storeImageList(@RequestParam(value = "groupId", required = true) Long groupId) {
        return storeCertificationService.storeImageList(groupId);
    }


    /**
     * @param id 分组主键ID
     * @@describe: 删除分组信息
     * @author: MrWen
     * @return: ResultCode
     * @date: 2019/1/29 9:14
     */
    @ApiOperation(value = "删除分组信息")
    @ApiImplicitParam(name = "id", value = "图片相册组的主键ID", required = true, dataType = "Long", paramType = "query")
    @RequestMapping(value = "/deleteImageGroup", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResultCode deleteImageGroup(Long id) {
        return storeCertificationService.deleteImageGroup(id);
    }

    /**
     * 获取店铺主页信息
     *
     * @param memberId 用户id
     * @param storeId  商店id
     * @return
     */
    @ApiOperation(value = "店铺主页-未开发")
    @RequestMapping(value = "/mainStore", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResultCode mainStore(String memberId, Long storeId) {
        return ResultCode.getNewInstance();
    }


    /**
     * @param storeId 店铺ID
     * @param month   月份
     * @@describe: 电子钱包统计店铺收益和余额
     * @author: MrWen
     * @return: com.alibaba.fastjson.JSONObject
     * @date: 2019/1/30 16:52
     */
    @ApiOperation(value = "电子钱包统计店铺总收益和总余额,月销售额等-未完成(等需求再定)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "month", value = "月份 格式:2019-02(只传年月)", required = false, dataType = "String"),
            @ApiImplicitParam(name = "storeId", value = "店铺 id example:1524262555 ", required = true, dataType = "Long", paramType = "query")
    })
    @RequestMapping(value = "/earningsBalance", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResultCode<CsIncomeSpending> earningsBalance(@RequestParam(value = "storeId", required = true) Long storeId,
                                                        @RequestParam(value = "month", required = false) String month) {

        return storeCertificationService.earningsBalance(storeId, month);
    }

    /**
     * @param type    类型(1.收支记录 2.提现记录)
     * @param storeId 店铺ID
     * @@describe: 账单统计
     * @author: MrWen
     * @return: java.util.List
     * @date: 2019/1/30 16:42
     */
    @ApiOperation(value = "账单统计-未开发")
    @RequestMapping(value = "/billStatistics", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List billStatistics(@RequestParam(value = "type") Integer type,
                               @RequestParam(value = "storeId") Long storeId) {
        return null;
    }


    /**
     * @param id  需要变化位置组的主键ID
     * @param targeId  目标位置id
     * @param orRear 是否置底(1.是 0.否)
     * @@describe: 相册组排序变换
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/13 16:41
     */
    @ApiOperation(value = "相册组排序变换")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要变化位置组的主键ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "targeId", value = "目标位置ID ", required = true, dataType = "Long", paramType = "query"), @ApiImplicitParam(name = "orRear", value = "是否置底(1.是 0.否)  ", required = true, dataType = "int", paramType = "query")
    })
    @RequestMapping(value = "/updateSort", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResultCode updateSort(@RequestParam(value = "id") Long id,
                                 @RequestParam(value = "targeId") Long targeId,
                                 @RequestParam(value = "orRear") int orRear) {
        return storeCertificationService.updateSort(id,targeId,orRear);
    }
    /**
     * 获取POST请求中Body参数
     *
     * @param request
     * @return 字符串
     */
    public String getParm(HttpServletRequest request) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String line = null;
        StringBuilder sb = new StringBuilder();
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * @param storeId 店铺ID
     * @@describe: 登陆后选择店铺返回店铺信息
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/22 15:59
     */
    @ApiOperation(value = "登陆后选择店铺返回店铺信息")
    @ApiImplicitParam(name = "storeId", value = "店铺ID", required = true, dataType = "Long")
    @RequestMapping(value = "/getStoreDetails", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResultCode<CsStore>  getStoreDetails(@RequestParam(value = "storeId") Long storeId) throws ParseException {

        return storeCertificationService.getStoreDetails(storeId);
    }
}
