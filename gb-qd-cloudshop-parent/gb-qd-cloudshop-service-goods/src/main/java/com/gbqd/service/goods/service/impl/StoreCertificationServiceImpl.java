package com.gbqd.service.goods.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gbqd.common.utils.RandomCodeUtil;
import com.gbqd.common.utils.ResultCode;
import com.gbqd.common.utils.enums.ResultCodeStatus;
import com.gbqd.mapper.*;
import com.gbqd.pojo.CsBusinessHierarchy;
import com.gbqd.pojo.goods.*;
import com.gbqd.service.goods.StoreCertificationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author MrWen
 * @create 2019-01-23-13:35
 */
@RestController
public class StoreCertificationServiceImpl implements StoreCertificationService {
    @Autowired
    CsStoreMapper csStoreMapper;
    @Autowired
    CsBusinessHierarchyMapper csBusinessHierarchyMapper;
    @Autowired
    CsStoreImageGroupMapper csStoreImageGroupMapper;
    @Autowired
    CsStoreImageMapper csStoreImageMapper;
    @Autowired
    CsIncomeSpendingMapper csIncomeSpendingMapper;
    @Autowired
    CsStoreHolidaySettingsMapper csStoreHolidaySettingsMapper;
    @Autowired
    CsStoreBusinessHoursMapper csStoreBusinessHoursMapper;
    @Value("${imageUrl}")
    //图片存放根路径
            String rootPath;
    @Value("${enterpriseImage}")
    //存放企业认证图片，店铺logo还有用户身份证的文件夹
            String enterpriseImagePath;
    @Value("${storeImage}")
    //存放店铺相册的文件夹
            String storeImage;

    /**
     * @param companyName 企业名称
     * @param legalPerson 法人名称
     * @param licenseNo   营业执照号
     * @param storeName   商铺名称
     * @param memberId    用户ID
     * @param memberName  用户姓名
     * @param buPId       对应的行业id 一级
     * @param buId        对应的行业id 二级
     * @@describe: 企业认证实现类
     * @author: MrWen
     * @return: com.alibaba.fastjson.JSONObject
     * @date: 2019/1/23 11:31
     */
    @Override
    @Transactional
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
            MultipartHttpServletRequest request) {
        CsStore csStore = new CsStore();
        if (storeId != null) {
            //不等于空 说明是要修改  删掉原来的 创建新的
            CsStore cs = csStoreMapper.selectByPrimaryKey(storeId);
            csStore.setId(cs.getId());//复用之前的ID
            csStoreMapper.deleteByPrimaryKey(storeId);
        } else {
            csStore.setId(RandomCodeUtil.getId());
        }
        ResultCode rc = new ResultCode();
        //通过营业执照号 查询未驳回的店铺申请记录
       /* List<CsStore>cslist=csStoreMapper.selectByLicenseNo(licenseNo);
        if(cslist.size()>0){
            rc.setStatus(ResultCodeStatus.FAIL);
            rc.setMsg("已经提交认证过资料");
            return rc;
        }*/
        //封装对象
        csStore.setCompanyName(companyName);
        csStore.setLegalPerson(legalPerson);
        csStore.setLicenseNo(licenseNo);
        csStore.setStoreName(storeName);
        csStore.setMemberId(memberId);
        csStore.setMemberName(memberName);
        csStore.setBuPId(buPId);
        csStore.setBuId(buId);
        rc.setStatus(ResultCodeStatus.FAIL);
        rc.setMsg("上传失败");
        // 上传图片
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());

        // 检查form是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = request;
            Iterator<String> iter = multiRequest.getFileNames();

            while (iter.hasNext()) {
                // 由CommonsMultipartFile继承而来,拥有上面的方法.
                // MultipartFile file = multiRequest.getFile(iter.next());
                List<MultipartFile> files = multiRequest.getFiles(iter.next());
                for (MultipartFile file : files) {
                    if (file != null) {
                        String realName = file.getOriginalFilename();
                        String names = file.getName();//取出文件名字
                        //与前端约定 names里包含什么名字 数据库对应存储对应的字段
                        if (StringUtils.isNotBlank(realName)) {
                            String type = realName.substring(realName.lastIndexOf('.'));
                            String name = RandomCodeUtil.getRandEnglishCode(11) + type;
                            File localFile = new File(rootPath + enterpriseImagePath + name);
                            try {
                                file.transferTo(localFile); // 保存文件
                                if (names.equals("front")) {
                                    //身份证正面
                                    csStore.setIdcardImageFront(enterpriseImagePath + name);
                                } else if (names.equals("opposite")) {
                                    //身份证反面
                                    csStore.setIdcardImageOpposite(enterpriseImagePath + name);
                                } else if (names.equals("license")) {
                                    //执照图片
                                    csStore.setLicenseImgage(enterpriseImagePath + name);
                                } else if (names.equals("log")) {
                                    //log的url图片链接
                                    csStore.setStoreImageLog(enterpriseImagePath + name);
                                } else if (names.equals("xkz")) {
                                    //云店经营许可证图片链接
                                    csStore.setStoreImageXkz(enterpriseImagePath + name);
                                }
                                rc.setStatus(ResultCodeStatus.SUCCESS);
                                rc.setMsg("上传成功");
                            } catch (Exception e) {
                                e.printStackTrace();
                                rc.setStatus(ResultCodeStatus.FAIL);
                                rc.setMsg("数据异常");
                                throw new RuntimeException();

                            }
                        }
                    }
                }

            }
            csStoreMapper.insertSelective(csStore);
        }

        return rc;


    }

    /**
     * @param memberId 用户主键ID
     * @@describe: 通过用户ID 查询该用户名下认证信息
     * @author: MrWen
     * @return: com.alibaba.fastjson.JSONObject
     * @date: 2019/1/24 10:30
     */
    public ResultCode<List<CsStore>> getStroeCertificationList(@RequestParam(value = "memberId", required = true) String memberId) {
        ResultCode<List<CsStore>> rc = new ResultCode<List<CsStore>>();
        List<CsStore> list = csStoreMapper.selectByMemberId(memberId);
        rc.setStatus(ResultCodeStatus.SUCCESS);
        rc.setMsg("查询成功");
        rc.setContent(list);
        return rc;
    }

    /**
     * @param id 企业店铺主键ID
     * @@describe: 查询企业资料详情
     * @author: MrWen
     * @return: com.alibaba.fastjson.JSONObject
     * @date: 2019/1/24 10:30
     */
    @Override
    public ResultCode<CsStore> stroeDetails(@RequestParam(value = "id", required = true) Long id) {
        ResultCode<CsStore> rc = new ResultCode<CsStore>();
        //根据店铺主键ID 获取店铺和企业的信息详情
        CsStore cs = csStoreMapper.selectByPrimaryKey(id);
        //根据行业ID 查询行业名称
        CsBusinessHierarchy csBusinessHierarchy = csBusinessHierarchyMapper.selectByPrimaryKey(cs.getBuId());
        if (cs != null) {
            cs.setStoreImageLog(rootPath + cs.getStoreImageLog());
            cs.setStoreImageXkz(rootPath + cs.getStoreImageXkz());
            cs.setIdcardImageFront(rootPath + cs.getIdcardImageFront());
            cs.setIdcardImageOpposite(rootPath + cs.getIdcardImageOpposite());
            cs.setLicenseImgage(rootPath + cs.getLicenseImgage());

            if (csBusinessHierarchy != null) {
                cs.setTname(csBusinessHierarchy.getBuName());
            }

        }
        rc.setStatus(ResultCodeStatus.SUCCESS);
        rc.setMsg("查询成功");
        rc.setContent(cs);
        return rc;
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
    @Override
    @Transactional
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
                                  @RequestParam(value = "file", required = false) MultipartFile file) {
        ResultCode rc = new ResultCode();

        //根据店铺主键ID 获取店铺和企业的信息详情
        CsStore cs = csStoreMapper.selectByPrimaryKey(id);
        cs.setStoreName(storeName);
        cs.setProvinceCode(provinceCode);
        cs.setCityCode(cityCode);
        cs.setZoneCode(zoneCode);
        cs.setAddress(address);
        cs.setLongitude(longitude);
        cs.setLatitude(latitude);
        cs.setStoreTel(storeTel);
        cs.setAveragePrice(averagePrice);
        cs.setOrSharingTime(orSharingTime);
        cs.setMinimumTime(minimumTime);
        cs.setRefundRate(refundRate);
        cs.setRefundPolicy(refundPolicy);
        cs.setRecommended(recommended);
        cs.setFeatures(features);
        cs.setIntroduction(introduction);
        if (file != null) {
            String realName = file.getOriginalFilename();
            if (StringUtils.isNotBlank(realName)) {
                String type = realName.substring(realName.lastIndexOf('.'));
                String name = RandomCodeUtil.getRandEnglishCode(11) + type;
                File localFile = new File(rootPath + enterpriseImagePath + name);

                try {
                    file.transferTo(localFile); // 保存文件
                    /*     AliyunOSSClientUtil.uploadObject2OSS(AliyunOSSClientUtil.getOSSClient(),file,"gbqd_cloudshop",enterpriseImagePath);*/
                    cs.setStoreImageLog(enterpriseImagePath + name);

                } catch (Exception e) {
                    e.printStackTrace();
                    rc.setStatus(ResultCodeStatus.FAIL);
                    rc.setMsg("修改失败");
                    throw new RuntimeException();
                }
            }
        }


        csStoreMapper.updateByPrimaryKeySelective(cs);
        rc.setStatus(ResultCodeStatus.SUCCESS);
        rc.setMsg("上传成功");
        return rc;
    }


    /**
     * @param id 店铺ID
     * @@describe: 店铺设置详情
     * @author: MrWen
     * @return: com.alibaba.fastjson.JSONObject
     * @date: 2019/1/24 14:54
     */
    @Override
    public ResultCode<CsStore> storeSetupDetails(Long id) {
        ResultCode<CsStore> rc = new ResultCode<CsStore>();
        //如果id不為空 證明以前設置過店鋪信息

        if (id != null) {
            //根据店铺主键ID 获取店铺和企业的信息详情
            CsStore cs = csStoreMapper.selectByStoreKey(id);
            rc.setStatus(ResultCodeStatus.SUCCESS);
            rc.setMsg("查询成功");
            rc.setContent(cs);
        } else {
            rc.setStatus(ResultCodeStatus.FAIL);
            rc.setMsg("查询失败");
            return rc;
        }
        return rc;
    }


    /**
     * @param groupName      分组名称
     * @param groupTitle     分组标题
     * @param groupDesc      分组描述
     * @param sort           排序
     * @param createMemberId 创建人ID
     * @@describe: 店铺相册分组接口
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/1/28 16:22
     */
    @Override
    @Transactional
    public ResultCode imageGroup(@RequestParam(value = "groupName", required = false) String groupName,
                                 @RequestParam(value = "groupTitle", required = false) String groupTitle,
                                 @RequestParam(value = "groupDesc", required = false) String groupDesc,
                                 @RequestParam(value = "sort", required = false) Integer sort,
                                 @RequestParam(value = "createMemberId", required = false) String createMemberId,
                                 @RequestParam(value = "storeId", required = false) Long storeId) {
        ResultCode rc = new ResultCode();
        CsStoreImageGroup csig = new CsStoreImageGroup();
        csig.setId(RandomCodeUtil.getId());
        csig.setCreateMemberId(createMemberId);
        csig.setCreateTime(new Date());
        csig.setGroupDesc(groupDesc);
        csig.setGroupName(groupName);
        csig.setGroupTitle(groupTitle);
        csig.setSort(sort);
        csig.setStoreId(storeId);
        if (sort == null) {
            List<CsStoreImageGroup> list = csStoreImageGroupMapper.getCsStoreImageGroupList(storeId);//根据店铺ID 查询店铺下的相册分组集合
            if (list.size() > 0) {
                csig.setSort(list.get(0).getSort() + 1);
            } else {
                csig.setSort(1);
            }
        }
        csStoreImageGroupMapper.insertSelective(csig);
        rc.setStatus(ResultCodeStatus.SUCCESS);
        rc.setMsg("创建成功");
        return rc;

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
    @Override
    @Transactional
    public ResultCode inserImage(@RequestParam(value = "storeId", required = false) Long storeId,
                                 @RequestParam(value = "groupId", required = false) Long groupId,
                                 @RequestParam(value = "createMemberId", required = false) String createMemberId,
                                 MultipartHttpServletRequest request) {
        ResultCode rc = new ResultCode();
        //通过分组ID获取图片集合  删除原有的图片集合
      /*  List<CsStoreImage> list = csStoreImageMapper.getCsStoreImageList(groupId);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                csStoreImageMapper.deleteByPrimaryKey(list.get(i).getId());
                //删除服务器上的照片
                File f = new File(rootPath + list.get(i).getImageUrl());
                f.delete();
            }
        }


        //删除图片 重新插入

        rc.setStatus(ResultCodeStatus.FAIL);*/
        // 上传图片
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());

        // 检查form是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = request;
            Iterator<String> iter = multiRequest.getFileNames();

            while (iter.hasNext()) {
                // 由CommonsMultipartFile继承而来,拥有上面的方法.
                // MultipartFile file = multiRequest.getFile(iter.next());
                List<MultipartFile> files = multiRequest.getFiles(iter.next());
                for (MultipartFile file : files) {
                    if (file != null) {
                        String realName = file.getOriginalFilename();
                        String names = file.getName();//取出文件名字
                        //与前端约定 names里包含什么名字 数据库对应存储对应的字段
                        if (StringUtils.isNotBlank(realName)) {
                            String type = realName.substring(realName.lastIndexOf('.'));
                            String name = RandomCodeUtil.getRandEnglishCode(11) + type;
                            File localFile = new File(rootPath + storeImage + name);
                            try {
                                file.transferTo(localFile); // 保存文件
                                CsStoreImage csStoreImage = new CsStoreImage();
                                csStoreImage.setId(RandomCodeUtil.getId());
                                csStoreImage.setCreateMemberId(createMemberId);
                                csStoreImage.setCreateTime(new Date());
                                csStoreImage.setGroupId(groupId);
                                csStoreImage.setStoreId(storeId);
                                csStoreImage.setImageUrl(storeImage + name);
                                csStoreImageMapper.insertSelective(csStoreImage);
                                rc.setStatus(ResultCodeStatus.SUCCESS);
                            } catch (Exception e) {
                                e.printStackTrace();
                                rc.setStatus(ResultCodeStatus.FAIL);
                                throw new RuntimeException("");


                            }
                        }
                    }
                }

            }

        }

        return rc;
    }

    /**
     * @param storeId 店铺id
     * @@describe: List店铺分组集合(无分页)
     * @author: MrWen
     * @return: List
     * @date: 2019/1/28 16:40
     */
    @Override
    public ResultCode<List<CsStoreImageGroup>> imageGroupList(@RequestParam(value = "storeId", required = true) Long storeId) {
        ResultCode<List<CsStoreImageGroup>> rc = new ResultCode<List<CsStoreImageGroup>>();

        List<CsStoreImageGroup> list = csStoreImageGroupMapper.getCsStoreImageGroupList(storeId);
        rc.setContent(list);
        rc.setStatus(ResultCodeStatus.SUCCESS);
        rc.setMsg("查询成功");
        return rc;

    }

    /**
     * @param storeId 店铺id
     * @@describe: List店铺分组集合(有分页给PC端用)
     * @author: MrWen
     * @return: List
     * @date: 2019/1/28 16:40
     */

    public ResultCode<PageInfo<CsStoreImageGroup>> imageGroupListPc(@RequestParam(value = "storeId", required = true) Long storeId,
                                                                    @RequestParam(value = "PageNum", required = false, defaultValue = "1") Integer PageNum,
                                                                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        ResultCode<PageInfo<CsStoreImageGroup>> rc = new ResultCode<PageInfo<CsStoreImageGroup>>();
        PageHelper.startPage(PageNum, pageSize);
        List<CsStoreImageGroup> list = csStoreImageGroupMapper.getCsStoreImageGroupList(storeId);
        PageInfo<CsStoreImageGroup> pageInfoList = new PageInfo<CsStoreImageGroup>(list);
        rc.setContent(pageInfoList);
        rc.setStatus(ResultCodeStatus.SUCCESS);
        rc.setMsg("查询成功");
        return rc;

    }


    /**
     * @param groupId 图片相册组主键ID
     * @@describe: 相册详情
     * @author: MrWen
     * @return: java.util.List<com.gbqd.pojo.goods.CsStoreImage>
     * @date: 2019/1/29 9:14
     */
    @Override
    public ResultCode<List<CsStoreImage>> storeImageList(@RequestParam(value = "groupId", required = true) Long groupId) {
        ResultCode<List<CsStoreImage>> rc = new ResultCode<List<CsStoreImage>>();
        List<CsStoreImage> list = csStoreImageMapper.getCsStoreImageList(groupId);
        rc.setStatus(ResultCodeStatus.SUCCESS);
        rc.setMsg("查询成功");
        rc.setContent(list);
        return rc;
    }

    /**
     * @param id 分组主键ID
     * @@describe: 删除分组信息
     * @author: MrWen
     * @return: ResultCode
     * @date: 2019/1/29 9:14
     */
    @Override
    @Transactional
    public ResultCode deleteImageGroup(Long id) {
        ResultCode rc = new ResultCode();
        //获取要删除的对象
        CsStoreImageGroup csStoreImageGroup=csStoreImageGroupMapper.selectByPrimaryKey(id);
        if(csStoreImageGroup==null){
            rc.setStatus(ResultCodeStatus.FAIL);
            rc.setMsg("数据有误");
            return rc;
        }
        List<CsStoreImageGroup> listD   =csStoreImageGroupMapper.getCsStoreImageGroupDelete(csStoreImageGroup.getStoreId(),csStoreImageGroup.getSort());
        if(listD.size()>0){
            for (int n = 0; n <listD.size() ; n++) {
                listD.get(n).setSort( listD.get(n).getSort()-1);
                csStoreImageGroupMapper.updateByPrimaryKey(listD.get(n));
            }
        }
        //删除分组信息
        int i = csStoreImageGroupMapper.deleteByPrimaryKey(id);

        if (i > 0) {
            //循环删除子表图片
            List<CsStoreImage> list = csStoreImageMapper.getCsStoreImageList(id);
            if (list.size() > 0) {
                list.forEach(n -> {
                    csStoreImageMapper.deleteByPrimaryKey(n.getId());
                    //删除服务器上的照片
                    File f = new File(rootPath + n.getImageUrl());
                    f.delete();
                });
            }
            rc.setStatus(ResultCodeStatus.SUCCESS);
            rc.setMsg("删除成功");
        } else {
            rc.setStatus(ResultCodeStatus.FAIL);
            rc.setMsg("删除失败");
        }

        return rc;
    }

    /**
     * @param storeId 店铺ID
     * @param month   月份
     * @@describe: 电子钱包统计店铺收益和余额
     * @author: MrWen
     * @return: com.alibaba.fastjson.JSONObject
     * @date: 2019/1/30 16:52
     */
    public ResultCode<CsIncomeSpending> earningsBalance(@RequestParam(value = "storeId", required = true) Long storeId,
                                                        @RequestParam(value = "month", required = false) String month) {
        ResultCode<CsIncomeSpending> rc = new ResultCode<CsIncomeSpending>();
        //获取店铺对象
        CsStore csStore = csStoreMapper.selectByPrimaryKey(storeId);
        if (csStore == null) {
            rc.setMsg("没有店铺");
            rc.setStatus(ResultCodeStatus.FAIL);
            return rc;
        }

        if (StringUtils.isBlank(month)) {
            //如果月份为空 默认查询当月数据
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            month = sdf.format(date);
        }

        //获取总销售额(收入)
        CsIncomeSpending is = csIncomeSpendingMapper.selectTotal(storeId);
        //获取月销售额(收入)
        CsIncomeSpending isM = csIncomeSpendingMapper.selectTotalMonth(storeId, month);
        isM.setTotalEarnings(is.getTotalEarnings());//总收益
        isM.setTotalSales(is.getTotalSales());//总销售额
        isM.setBalance(csStore.getAvailableAccount());//总余额
        rc.setStatus(ResultCodeStatus.SUCCESS);
        rc.setMsg("查询成功");
        rc.setContent(isM);
        return rc;
    }

    /**
     * @param id      需要变化位置组的主键ID
     * @param targeId 目标位置id
     * @param orRear  是否置底(1.是 0.否)
     * @@describe: 批量更新排序
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/13 16:41
     */
    public ResultCode updateSort(@RequestParam(value = "id") Long id,
                                 @RequestParam(value = "targeId") Long targeId,
                                 @RequestParam(value = "orRear") int orRear) {
        ResultCode rc = new ResultCode();
        //通过主键ID 拿到需要变换位置的对象
        CsStoreImageGroup csStoreImageGroup = csStoreImageGroupMapper.selectByPrimaryKey(id);
        //通过目标位置ID 找到目标位置对象
        CsStoreImageGroup csStoreImageGroupTarge = csStoreImageGroupMapper.selectByPrimaryKey(targeId);
        //如果两个对象都不为空
        if (csStoreImageGroup != null && csStoreImageGroupTarge != null) {
            if (orRear == 1) {
                //如果是置底,获取除了目标位置的所有数据的集合 全体+1
                List<CsStoreImageGroup> noList = csStoreImageGroupMapper.getCsStoreImageGroupNo(csStoreImageGroupTarge.getStoreId(), csStoreImageGroupTarge.getSort(),csStoreImageGroup.getSort());
                csStoreImageGroup.setSort(csStoreImageGroupTarge.getSort());
                csStoreImageGroupMapper.updateByPrimaryKey(csStoreImageGroup);
                for (int i = 0; i < noList.size(); i++) {
                    noList.get(i).setSort(noList.get(i).getSort() + 1);
                    csStoreImageGroupMapper.updateByPrimaryKey(noList.get(i));
                }
            } else {
                if (csStoreImageGroup.getSort() - csStoreImageGroupTarge.getSort() == 1 || csStoreImageGroupTarge.getSort() - csStoreImageGroup.getSort() == 1) {
                    //如果只大于1  相互调换位置
                    int number=csStoreImageGroupTarge.getSort();
                    int numbers=csStoreImageGroup.getSort();
                    csStoreImageGroup.setSort(number);
                    csStoreImageGroupTarge.setSort(numbers);
                    csStoreImageGroupMapper.updateByPrimaryKey(csStoreImageGroup);
                    csStoreImageGroupMapper.updateByPrimaryKey(csStoreImageGroupTarge);
                } else if (csStoreImageGroup.getSort() > csStoreImageGroupTarge.getSort()) {
                    //如果当前位置变换大于目标位置  说明往下排
                    //取 小于当前位置  大于等于目标位置+1的区间进行+1更新
                    List<CsStoreImageGroup> downList = csStoreImageGroupMapper.getCsStoreImageGroupDown(csStoreImageGroup.getStoreId(), csStoreImageGroupTarge.getSort() + 1, csStoreImageGroup.getSort());
                    csStoreImageGroup.setSort(csStoreImageGroupTarge.getSort() + 1);
                    csStoreImageGroupMapper.updateByPrimaryKey(csStoreImageGroup);
                    for (int i = 0; i < downList.size(); i++) {
                        downList.get(i).setSort(downList.get(i).getSort() + 1);
                        csStoreImageGroupMapper.updateByPrimaryKey(downList.get(i));
                    }
                } else if (csStoreImageGroup.getSort() < csStoreImageGroupTarge.getSort()) {
                    //如果当前位置变换小于目标位置  说明往上排
                    List<CsStoreImageGroup> upList = csStoreImageGroupMapper.getCsStoreImageGroupUp(csStoreImageGroup.getStoreId(), csStoreImageGroupTarge.getSort(), csStoreImageGroup.getSort());
                    csStoreImageGroup.setSort(csStoreImageGroupTarge.getSort());
                    csStoreImageGroupMapper.updateByPrimaryKey(csStoreImageGroup);
                    for (int i = 0; i < upList.size(); i++) {
                        upList.get(i).setSort(upList.get(i).getSort() - 1);
                        csStoreImageGroupMapper.updateByPrimaryKey(upList.get(i));
                    }
                }
            }
            rc.setMsg("更新成功");
            rc.setStatus(ResultCodeStatus.SUCCESS);
            return rc;
        } else {
            rc.setMsg("更新失败");
            rc.setStatus(ResultCodeStatus.FAIL);
            return rc;
        }
    }


    /**
     * @param storeId 店铺ID
     * @@describe: 登陆后选择店铺返回店铺信息
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/22 15:59
     */
    public ResultCode<CsStore> getStoreDetails(@RequestParam(value = "storeId") Long storeId) throws ParseException {
        //查询店铺信息
        CsStore csStore = csStoreMapper.selectByPrimaryKey(storeId);
        ResultCode<CsStore> rc = new ResultCode<CsStore>();
        if (csStore != null) {
            if (csStore.getManagementState() == 1) {
                List<CsStoreHolidaySettings> list = csStoreHolidaySettingsMapper.getOnOffStoreList(1, storeId);//查询该店铺下面所有在区间内的假期时间
                if (list.size() > 0) {
                    //如果有区间设置 证明现在店铺是在假期活动中 暂时歇业
                    csStore.setManagementState(3);
                } else {
                    //如果没有区间设置  那继续查询下是否有营业时间区间设置列表
                    List<CsStoreBusinessHours> listH = csStoreBusinessHoursMapper.getOnOffStoreList(1, storeId);
                    if (listH.size() > 0) {
                        //如果有营业时间设置,循环判断是否在区间内
                        for (int i = 0; i < listH.size(); i++) {
                            Date date = new Date();
                            //用当前时间对比营业设置时间
                            SimpleDateFormat sfd = new SimpleDateFormat("HH:mm");
                            //根据周几来判断,先获取几天是周几
                            Calendar c = Calendar.getInstance();
                            c.setTime(date);
                            int weekday = c.get(Calendar.DAY_OF_WEEK);//获取今天是周几  1=周日  2=周一  7=周六
                            //判断是否包含今天的日期
                            boolean status = listH.get(i).getDateOf().contains(String.valueOf(weekday));
                            if (status) {
                                Date begin = sfd.parse(listH.get(i).getBeginTime());
                                Date end = sfd.parse(listH.get(i).getEndTime());
                                Date newDate = sfd.parse(sfd.format(date));
                                if (newDate.getTime() < begin.getTime() || newDate.getTime() > end.getTime()) {
                                    csStore.setManagementState(2);
                                    break;
                                } else if (newDate.getTime() >= begin.getTime() && newDate.getTime() <= end.getTime()) {
                                    csStore.setManagementState(1);
                                    break;
                                }
                            } else {
                                csStore.setManagementState(2);
                            }

                        }
                    }
                }
            }
        }


        rc.setContent(csStore);
        rc.setMsg("更新成功");
        rc.setStatus(ResultCodeStatus.SUCCESS);
        return rc;
    }
}