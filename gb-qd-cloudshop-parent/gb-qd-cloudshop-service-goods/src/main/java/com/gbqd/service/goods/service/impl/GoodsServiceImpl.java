package com.gbqd.service.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gbqd.common.utils.RandomCodeUtil;
import com.gbqd.common.utils.ResultCode;
import com.gbqd.common.utils.enums.ResultCodeStatus;
import com.gbqd.mapper.*;
import com.gbqd.pojo.Item.CsItem;
import com.gbqd.pojo.goods.*;
import com.gbqd.pojo.template.CsTemplateItem;
import com.gbqd.service.goods.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    CsStoreGoodsHotelCategoryMapper csStoreGoodsHotelCategoryMapper;
    @Autowired
    CsGoodsHotelMapper csGoodsHotelMapper;
    @Autowired
    CsGoodsHotelImageMapper csGoodsHotelImageMapper;
    @Autowired
    CsStoreHotelCategoryPriceSetMapper csStoreHotelCategoryPriceSetMapper;
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
     * 根据商铺获取商铺的商品层级
     *
     * @param storeId
     * @return
     */
    @Override
    public List<CsStoreGoodsHotelCategory> getCsStoreGoodsHotelCategoryByStoreId(Long storeId) {

        List<CsStoreGoodsHotelCategory> list = csStoreGoodsHotelCategoryMapper.getCsStoreGoodsHotelCategoryByStoreId(storeId);
        if (list != null && list.size() > 0) {
            int i = (1 == 1 ? 1 : 2);
            for (CsStoreGoodsHotelCategory cc : list) {
                switch (cc.getGoodsType()) {
                    case 1:
                        cc.setGoodsTypeName("日租房");
                        break;
                    case 2:
                        cc.setGoodsTypeName("分时房");
                        break;
                }
                switch (cc.getBedType()) {
                    case 1:
                        cc.setBedTypeName("大床房");
                    case 2:
                        cc.setBedTypeName("单人床");
                    case 3:
                        cc.setBedTypeName("海景房");
                }
            }
        }
        return list;
    }

    @Autowired
    CsTemplateItemMapper csTemplateItemMapper;

    /**
     * 根据商铺id获取添加"自定义商品添加模板"
     *
     * @param buId
     * @param temType
     * @return
     */
    @Override
    public String getCsStoreGoodsHotelCategoryAddTemp(Long buId, Integer temType) {
        //csStoreGoodsHotelCategoryMapper
        List<CsTemplateItem> list = csTemplateItemMapper.getCsTemplateItemByStoreIdAndTemType(buId, temType);
        String jsonStr = JSON.toJSONString(list);
        return jsonStr;
    }

    @Autowired
    CsItemMapper csItemMapper;
    @Autowired
    CsTemplateItemMapper CsTemplateItemMapper;

    /**
     * 获取商品类型
     *
     * @return
     */
    @Override
    public ResultCode getJsonRoomType(Long buId) {
        ResultCode rr = ResultCode.getNewInstance();
        Map<String, String> map = new HashMap<>();//总类别
        List<CsTemplateItem> list = csTemplateItemMapper.getCsTemplateItemByStoreIdAndTemType(buId, 4);
        list.sort((CsTemplateItem h1, CsTemplateItem h2) -> h1.getSort().compareTo(h2.getSort()));
        JSONArray ja = new JSONArray();//返回的数组
        for (CsTemplateItem cst : list) {
            JSONObject ja1 = JSON.parseObject(cst.getScopeValue());
            Iterator<Map.Entry<String, Object>> ja1mapIter = ja1.entrySet().iterator();
            while (ja1mapIter.hasNext()) {
                JSONObject jaObj = new JSONObject();
                Map.Entry<String, Object> entry = ja1mapIter.next();
                jaObj.put("k", cst.getItemCode() + "." + entry.getKey());
                jaObj.put("v", entry.getValue());
                jaObj.put("sort", cst.getSort() + "");
                ja.add(jaObj);
            }

        }
        // Map<String,String> mapFromJson = jaObj.toJavaObject(Map.class);
        //mapFromJson.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(e -> mapFromJson.put(e.getKey(), e.getValue()));
        //map排序后转数组

        rr.setContent(ja);
        rr.setStatus(ResultCodeStatus.SUCCESS);
        rr.setMsg("成功");
        rr.setNetStatus("200");
        return rr;
    }

    /**
     * 获取商品维护-房屋维护
     *
     * @param storeId 店铺id
     * @param Optpye  操作类型 当前根据itemcode前缀判断类型大类
     * @return
     */
    @Override
    public ResultCode<StoreMaintainModel> getCsStoreGoodsMaintain(Long storeId, String Optpye) {
        ResultCode<StoreMaintainModel> rr = ResultCode.getNewInstance();
        Integer bedType = null;
        Integer roomType = null;
        if (StringUtils.isNotBlank(Optpye)) {
            String[] arr = Optpye.split("\\.");
            if (Optpye.startsWith("2100001")) {
                bedType = Integer.parseInt(arr[1]);
            } else if (Optpye.startsWith("1000001")) {
                roomType = Integer.parseInt(arr[1]);
            }
        }
        StoreMaintainModel smm = new StoreMaintainModel();
        List<CsStoreGoodsHotelCategory> list = csStoreGoodsHotelCategoryMapper.getCsStoreGoodsHotelCategoryByBedTypeOrRoomType(storeId, bedType, roomType);
        smm.setGoodsList(list);
        rr.setContent(smm);
        rr.setStatus(ResultCodeStatus.SUCCESS);
        return rr;
    }

    /**
     * 修改房屋价格
     *
     * @param gcRoomId 房屋id
     * @param gcPrice  人民币价格
     * @param vbPrice  VB价格
     * @return
     */
    @Override
    public ResultCode updateGcRoomPrice(Long gcRoomId, Double gcPrice, Double vbPrice) {
        ResultCode rr = ResultCode.getNewInstance();
        CsStoreGoodsHotelCategory record = new CsStoreGoodsHotelCategory();
        record.setId(gcRoomId);

        record.setGcPrice(gcPrice);
        record.setVbPrice(vbPrice);
        int num = csStoreGoodsHotelCategoryMapper.updateByPrimaryKeySelective(record);
        rr.setStatus(ResultCodeStatus.SUCCESS);
        rr.setMsg("修改成功");
        rr.setContent(num);
        return rr;
    }

    /**
     * 更新  房间状态 1:空闲 2：入住 3：维修
     *
     * @param roomId
     * @param roomStatus
     * @return
     */
    @Override
    public ResultCode updateRoomStatus(Long roomId, Integer roomStatus) {
        ResultCode rr = ResultCode.getNewInstance();
        CsGoodsHotel record = new CsGoodsHotel();
        record.setId(roomId);
        record.setRoomStatus(roomStatus);
        int num = csGoodsHotelMapper.updateByPrimaryKeySelective(record);
        rr.setStatus(ResultCodeStatus.SUCCESS);
        rr.setContent(num);
        return rr;
    }

    /**
     * 根据层级房屋id 获取层架下房屋的列表
     *
     * @param gcRoomId 层级房屋id
     * @return
     */
    @Override
    public ResultCode<List<CsGoodsHotel>> getRoomFromGcId(Long gcRoomId) {
        ResultCode<List<CsGoodsHotel>> rr = ResultCode.getNewInstance();
        List<CsGoodsHotel> list = csGoodsHotelMapper.getRoomFromGcId(gcRoomId);
        rr.setContent(list);
        rr.setStatus(ResultCodeStatus.SUCCESS);
        return rr;
    }


    /**
     * @param gcName          商品类型名称
     * @param bedType         床型  {0:大床房,1:单人床,2:双人床}
     * @param itemCodeBedType 配置项编码,bed_type的code用来去字典表里找
     * @param orBreakfast     是否含早餐(1.是  0.否)
     * @param breakfastMoney  早餐价格
     * @param goodsType       商品类别 1:日租房 2:分时房
     * @param gcPrice         分类价格 商品层级定义的价格
     * @param vbPrice         VB百分比
     * @@describe: 客房管理添加
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/26 16:50
     */
    @Transactional
    public ResultCode insertGoodsCategory(@RequestParam(value = "storeId", required = true) Long storeId,
                                          @RequestParam(value = "gcName", required = true) String gcName,
                                          @RequestParam(value = "bedType", required = true) Integer bedType,
                                          @RequestParam(value = "itemCodeBedType", required = true) String itemCodeBedType,
                                          @RequestParam(value = "orBreakfast", required = true) Integer orBreakfast,
                                          @RequestParam(value = "breakfastMoney", required = false) Double breakfastMoney,
                                          @RequestParam(value = "goodsType", required = true) Integer goodsType, @RequestParam(value = "gcPrice", required = true) Double gcPrice,
                                          @RequestParam(value = "vbPrice", required = true) Double vbPrice,
                                          @RequestParam(value = "bedCharacter", required = true) String bedCharacter) {
        CsStoreGoodsHotelCategory csghc = new CsStoreGoodsHotelCategory();
        csghc.setId(RandomCodeUtil.getId());
        csghc.setGcName(gcName);
        csghc.setBedType(bedType);
        csghc.setItemCodeBedType(itemCodeBedType);
        csghc.setOrBreakfast(orBreakfast);
        csghc.setBreakfastMoney(breakfastMoney);
        csghc.setGoodsType(goodsType);
        csghc.setVbPrice(vbPrice);
        csghc.setBedCharacter(bedCharacter);
        csghc.setStoreId(storeId);
        csghc.setGcPrice(gcPrice);
        csStoreGoodsHotelCategoryMapper.insertSelective(csghc);
        ResultCode rc = new ResultCode();
        rc.setMsg("添加成功");
        rc.setStatus(ResultCodeStatus.SUCCESS);
        return rc;
    }

    /**
     * @param id 客房主键ID
     * @@describe: 客房删除功能(逻辑删除)
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/26 16:50
     */
    public ResultCode deleteGoodsCategory(@RequestParam(value = "id", required = true) Long id) {
        ResultCode rc = new ResultCode();
        //因为是逻辑删除  直接更新状态 改为删除状态
        CsStoreGoodsHotelCategory csStoreGoodsHotelCategory = csStoreGoodsHotelCategoryMapper.selectByPrimaryKey(id);
        if (csStoreGoodsHotelCategory != null) {
            csStoreGoodsHotelCategory.setDelStatus(0);
            csStoreGoodsHotelCategoryMapper.updateByPrimaryKeySelective(csStoreGoodsHotelCategory);
            rc.setMsg("删除成功");
            rc.setStatus(ResultCodeStatus.SUCCESS);
        } else {
            rc.setMsg("删除失败");
            rc.setStatus(ResultCodeStatus.FAIL);
        }
        return rc;
    }

    /**
     * @param id              客房主键ID
     * @param gcName          商品类型名称
     * @param bedType         床型  {0:大床房,1:单人床,2:双人床}
     * @param itemCodeBedType 配置项编码,bed_type的code用来去字典表里找
     * @param orBreakfast     是否含早餐(1.是  0.否)
     * @param breakfastMoney  早餐价格
     * @param goodsType       商品类别 1:日租房 2:分时房
     * @param gcPrice         分类价格 商品层级定义的价格
     * @param vbPrice         VB比例
     * @@describe: 客房管理修改
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/26 16:50
     */
    public ResultCode updateGoodsCategory(@RequestParam(value = "id", required = true) Long id,
                                          @RequestParam(value = "gcName", required = false) String gcName,
                                          @RequestParam(value = "bedType", required = false) Integer bedType,
                                          @RequestParam(value = "itemCodeBedType", required = false) String itemCodeBedType,
                                          @RequestParam(value = "orBreakfast", required = false) Integer orBreakfast,
                                          @RequestParam(value = "breakfastMoney", required = false) Double breakfastMoney,
                                          @RequestParam(value = "goodsType", required = false) Integer goodsType, @RequestParam(value = "gcPrice", required = false) Double gcPrice,
                                          @RequestParam(value = "vbPrice", required = false) Double vbPrice) {
        ResultCode rc = new ResultCode();
        CsStoreGoodsHotelCategory csStoreGoodsHotelCategory = csStoreGoodsHotelCategoryMapper.selectByPrimaryKey(id);
        if (csStoreGoodsHotelCategory != null) {
            csStoreGoodsHotelCategory.setItemCodeBedType(itemCodeBedType);
            csStoreGoodsHotelCategory.setGcName(gcName);
            csStoreGoodsHotelCategory.setBedType(bedType);
            csStoreGoodsHotelCategory.setOrBreakfast(orBreakfast);
            csStoreGoodsHotelCategory.setBreakfastMoney(breakfastMoney);
            csStoreGoodsHotelCategory.setGoodsType(goodsType);
            csStoreGoodsHotelCategory.setVbPrice(vbPrice);
            csStoreGoodsHotelCategoryMapper.updateByPrimaryKeySelective(csStoreGoodsHotelCategory);
            rc.setMsg("修改成功");
            rc.setStatus(ResultCodeStatus.SUCCESS);
        } else {
            rc.setMsg("修改失败");
            rc.setStatus(ResultCodeStatus.FAIL);
        }
        return rc;
    }


    /**
     * @param storeId 店铺ID
     * @@describe: 查询该店铺下的客房列表
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/26 16:50
     */
    public ResultCode<PageInfo<CsStoreGoodsHotelCategory>> getGoodsCategoryList(@RequestParam(value = "storeId", required = true) Long storeId,
                                                                                @RequestParam(value = "PageNum", required = false, defaultValue = "1") Integer PageNum,
                                                                                @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                                                @RequestParam(value = "gcName", required = false) String gcName,
                                                                                @RequestParam(value = "bedType", required = false) Integer bedType,
                                                                                @RequestParam(value = "goodsType", required = false) Integer goodsType) {
        ResultCode<PageInfo<CsStoreGoodsHotelCategory>> rc = new ResultCode<PageInfo<CsStoreGoodsHotelCategory>>();
        PageHelper.startPage(PageNum, pageSize);
        List<CsStoreGoodsHotelCategory> list = csStoreGoodsHotelCategoryMapper.getCsStoreGoodsHotelCategoryList(storeId, gcName, bedType, goodsType, 1);
        PageInfo<CsStoreGoodsHotelCategory> pageInfoList = new PageInfo<CsStoreGoodsHotelCategory>(list);
        rc.setContent(pageInfoList);
        rc.setStatus(ResultCodeStatus.SUCCESS);
        rc.setMsg("查询成功 ");
        return rc;
    }

    /**
     * @param id 客房主键ID
     * @@describe: 获取客房详情
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/26 16:50
     */
    public ResultCode<CsStoreGoodsHotelCategory> detailsGoodsCategory(@RequestParam(value = "id", required = true) Long id) {
        ResultCode<CsStoreGoodsHotelCategory> rc = new ResultCode<CsStoreGoodsHotelCategory>();
        CsStoreGoodsHotelCategory csStoreGoodsHotelCategory = csStoreGoodsHotelCategoryMapper.selectByPrimaryKey(id);
        rc.setContent(csStoreGoodsHotelCategory);
        rc.setStatus(ResultCodeStatus.SUCCESS);
        rc.setMsg("查询成功 ");
        return rc;
    }


    /**
     * @param storeId      店铺ID
     * @param ghcId        房间类别id (客房的主键)
     * @param gName        房间名称(1002)
     * @param buId         二级行业id (星级酒店)
     * @param roomDesc     房间描述
     * @param numberPeople 入住人数
     * @param temId        商品规格模id
     * @param itemAttr     配置项/规格属性
     * @param area         面积
     * @@describe: 房间添加
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/27 14:25
     */
    public ResultCode insertRoom(@RequestParam(value = "storeId", required = true) Long storeId,
                                 @RequestParam(value = "ghcId", required = true) Long ghcId,
                                 @RequestParam(value = "gName", required = true) String gName,
                                 @RequestParam(value = "buId", required = true) Long buId,
                                 @RequestParam(value = "roomDesc", required = true) String roomDesc,
                                 @RequestParam(value = "numberPeople", required = false) Integer numberPeople,
                                 @RequestParam(value = "temId", required = true) Long temId,
                                 @RequestParam(value = "itemAttr", required = true) String itemAttr,
                                 @RequestParam(value = "area", required = true) String area,
                                 @RequestParam(value = "bedTypeKey", required = false) Integer bedTypeKey,
                                 @RequestParam(value = "bedTypeName", required = false) String bedTypeName) {
        ResultCode rc = new ResultCode();
        CsGoodsHotel csGoodsHotel = new CsGoodsHotel();
        csGoodsHotel.setId(RandomCodeUtil.getId());
        csGoodsHotel.setGhcId(ghcId);
        csGoodsHotel.setStoreId(storeId);
        csGoodsHotel.setgName(gName);
        csGoodsHotel.setBuId(buId);
        csGoodsHotel.setRoomDesc(roomDesc);
        csGoodsHotel.setNumberPeople(numberPeople);
        csGoodsHotel.setTemId(temId);
        csGoodsHotel.setItemAttr(itemAttr);
        csGoodsHotel.setArea(area);
        csGoodsHotelMapper.insertSelective(csGoodsHotel);
        rc.setStatus(ResultCodeStatus.SUCCESS);
        rc.setMsg("添加成功");
        return rc;
    }

    /**
     * @param id
     * @@describe: 房间刪除
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/27 14:25
     */
    public ResultCode deleteRoom(@RequestParam(value = "id", required = true) Long id) {
        ResultCode rc = new ResultCode();
        CsGoodsHotel csGoodsHotel = csGoodsHotelMapper.selectByPrimaryKey(id);
        if (csGoodsHotel != null) {
            csGoodsHotel.setDelStatus(0);
            csGoodsHotelMapper.updateByPrimaryKeySelective(csGoodsHotel);
            rc.setStatus(ResultCodeStatus.SUCCESS);
            rc.setMsg("删除成功");
        } else {
            rc.setStatus(ResultCodeStatus.FAIL);
            rc.setMsg("删除失败");
        }


        return rc;
    }

    /**
     * @param id
     * @@describe: 房间详情
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/27 14:25
     */
    public ResultCode<CsGoodsHotel> roomDetails(@RequestParam(value = "id", required = true) Long id) {
        ResultCode<CsGoodsHotel> rc = new ResultCode<CsGoodsHotel>();
        CsGoodsHotel csGoodsHotel = csGoodsHotelMapper.selectByPrimaryKey(id);
        rc.setContent(csGoodsHotel);
        rc.setStatus(ResultCodeStatus.SUCCESS);
        rc.setMsg("查询成功");
        return rc;
    }

    /**
     * @param id
     * @param gName        房间名称(1002)
     * @param roomDesc     房间描述
     * @param numberPeople 入住人数
     * @param itemAttr     配置项/规格属性
     * @param area         面积
     * @@describe: 房间修改
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/27 14:25
     */
    public ResultCode updateRoom(@RequestParam(value = "id", required = true) Long id,
                                 @RequestParam(value = "gName", required = false) String gName,
                                 @RequestParam(value = "roomDesc", required = false) String roomDesc,
                                 @RequestParam(value = "numberPeople", required = false) Integer numberPeople,
                                 @RequestParam(value = "itemAttr", required = false) String itemAttr,
                                 @RequestParam(value = "area", required = false) String area) {
        ResultCode rc = new ResultCode();
        CsGoodsHotel csGoodsHotel = csGoodsHotelMapper.selectByPrimaryKey(id);
        if (csGoodsHotel != null) {
            csGoodsHotel.setgName(gName);
            csGoodsHotel.setRoomDesc(roomDesc);
            csGoodsHotel.setNumberPeople(numberPeople);
            csGoodsHotel.setItemAttr(itemAttr);
            csGoodsHotel.setArea(area);
            csGoodsHotelMapper.updateByPrimaryKeySelective(csGoodsHotel);
            rc.setStatus(ResultCodeStatus.SUCCESS);
            rc.setMsg("修改成功");
        } else {
            rc.setStatus(ResultCodeStatus.FAIL);
            rc.setMsg("添加失败");
        }
        return rc;
    }

    /**
     * @param id
     * @param saleStatus 上架下架 	0:下架 1：上架
     * @@describe: 房间下架上架
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/27 14:25
     */
    public ResultCode shelvesRoom(@RequestParam(value = "id", required = true) Long id,
                                  @RequestParam(value = "saleStatus", required = true) Integer saleStatus) {
        ResultCode rc = new ResultCode();
        CsGoodsHotel csGoodsHotel = csGoodsHotelMapper.selectByPrimaryKey(id);
        if (csGoodsHotel != null) {
            csGoodsHotel.setSaleStatus(saleStatus);
            csGoodsHotelMapper.updateByPrimaryKeySelective(csGoodsHotel);
            rc.setStatus(ResultCodeStatus.SUCCESS);
            rc.setMsg("成功");
        } else {
            rc.setStatus(ResultCodeStatus.FAIL);
            rc.setMsg("失败");
        }

        return rc;
    }

    /**
     * @param storeId 店铺ID
     * @param ghcId   房间类别id (客房的主键)
     * @@describe: 房间列表查询
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/27 14:25
     */
    public ResultCode<PageInfo<CsGoodsHotel>> getRoomList(@RequestParam(value = "PageNum", required = false, defaultValue = "1") Integer PageNum,
                                                          @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                          @RequestParam(value = "storeId", required = true) Long storeId,
                                                          @RequestParam(value = "ghcId", required = true) Long ghcId,
                                                          @RequestParam(value = "gName", required = false) String gName,
                                                          @RequestParam(value = "roomStatus", required = false) String roomStatus) {
        ResultCode<PageInfo<CsGoodsHotel>> rc = new ResultCode<PageInfo<CsGoodsHotel>>();
        PageHelper.startPage(PageNum, pageSize);
        List<CsGoodsHotel> list = csGoodsHotelMapper.getRoomList(storeId, ghcId, gName, roomStatus);
        PageInfo<CsGoodsHotel> pageInfoList = new PageInfo<CsGoodsHotel>(list);
        rc.setContent(pageInfoList);
        rc.setStatus(ResultCodeStatus.SUCCESS);
        rc.setMsg("  查询成功");
        return rc;
    }


    @Override
    /**
     * @param roomId 房间ID
     * @param memberId  创建人ID
     * @param request 图片集合
     * @@describe:
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/28 13:57
     */
    public ResultCode insertRommImage(@RequestParam(value = "roomId", required = true) Long roomId,
                                      @RequestParam(value = "memberId", required = true) String memberId,
                                      MultipartHttpServletRequest request) {
        ResultCode rc = new ResultCode();
        CsGoodsHotelImage csGoodsHotelImage = new CsGoodsHotelImage();
        csGoodsHotelImage.setId(RandomCodeUtil.getId());
        csGoodsHotelImage.setRoomId(roomId);
        csGoodsHotelImage.setCreateMemberId(memberId);
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
                            File localFile = new File(rootPath + storeImage + name);
                            try {
                                file.transferTo(localFile); // 保存文件
                                csGoodsHotelImage.setImageUrl(storeImage + name);
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
            csGoodsHotelImageMapper.insertSelective(csGoodsHotelImage);
        }
        return rc;
    }


    /**
     * @param id 图片主键ID
     * @@describe:
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/28 13:57
     */
    @Override
    public ResultCode deletetRommImage(@RequestParam(value = "id", required = true) Long id) {
        ResultCode rc = new ResultCode();
        csGoodsHotelImageMapper.deleteByPrimaryKey(id);
        rc.setStatus(ResultCodeStatus.SUCCESS);
        rc.setMsg("删除成功");
        return rc;
    }

    /**
     * @param roomId 房间主键ID
     * @@describe:
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/28 13:57
     */
    @Override
    public ResultCode<List<CsGoodsHotelImage>> selectRommImage(@RequestParam(value = "roomId", required = true) Long roomId) {
        ResultCode<List<CsGoodsHotelImage>> rc = new ResultCode<List<CsGoodsHotelImage>>();
        List<CsGoodsHotelImage> list = csGoodsHotelImageMapper.selectRoomList(roomId);
        rc.setContent(list);
        rc.setStatus(ResultCodeStatus.SUCCESS);
        rc.setMsg("查询成功");
        return rc;
    }

    /**
     * @param hotelCategoryId 客房的主键id
     * @param money           房间价格
     * @param vb              vb的比例
     * @param beginTime       开始日期
     * @param endTime         结束日期
     * @@describe: 添加客房周期价格管理设置
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/3/1 10:15
     */
    public ResultCode insertPriceSet(@RequestParam(value = "hotelCategoryId", required = true) Long hotelCategoryId,
                                     @RequestParam(value = "money", required = true) Double money,
                                     @RequestParam(value = "vb", required = true) Double vb,
                                     @RequestParam(value = "beginTime", required = true) String beginTime,
                                     @RequestParam(value = "endTime", required = true) String endTime) throws ParseException {
        ResultCode rc = new ResultCode();
        CsStoreHotelCategoryPriceSet sStoreHotelCategoryPriceSetc = new CsStoreHotelCategoryPriceSet();
        sStoreHotelCategoryPriceSetc.setId(RandomCodeUtil.getId());
        SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
        sStoreHotelCategoryPriceSetc.setBeginTime(sfd.parse(beginTime));
        sStoreHotelCategoryPriceSetc.setEndTime(sfd.parse(endTime));
        sStoreHotelCategoryPriceSetc.setHotelCategoryId(hotelCategoryId);
        sStoreHotelCategoryPriceSetc.setMoney(money);
        sStoreHotelCategoryPriceSetc.setVb(vb);
        csStoreHotelCategoryPriceSetMapper.insertSelective(sStoreHotelCategoryPriceSetc);
        rc.setStatus(ResultCodeStatus.SUCCESS);
        rc.setMsg("添加成功");
        return rc;
    }

    /**
     * @param id 客房的主键id
     * @@describe: 添加客房周期价格管理设置
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/3/1 10:15
     */
    @Transactional
    public ResultCode deletePriceSet(@RequestParam(value = "id[]", required = true) Long[] id) {
        ResultCode rc = new ResultCode();
        for (int i = 0; i < id.length; i++) {
            csStoreHotelCategoryPriceSetMapper.deleteByPrimaryKey(id[i]);
        }
        rc.setStatus(ResultCodeStatus.SUCCESS);
        rc.setMsg("删除成功");
        return rc;
    }

    /**
     * @param money     房间价格
     * @param vb        vb的比例
     * @param beginTime 开始日期
     * @param endTime   结束日期
     * @@describe: 修改客房周期价格管理设置
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/3/1 10:15
     */
    public ResultCode updatePriceSet(@RequestParam(value = "id", required = false) Long id,
                                     @RequestParam(value = "money", required = false) Double money,
                                     @RequestParam(value = "vb", required = false) Double vb,
                                     @RequestParam(value = "beginTime", required = false) String beginTime,
                                     @RequestParam(value = "endTime", required = false) String endTime) throws ParseException {
        ResultCode rc = new ResultCode();
        CsStoreHotelCategoryPriceSet csStoreHotelCategoryPriceSet = csStoreHotelCategoryPriceSetMapper.selectByPrimaryKey(id);
        csStoreHotelCategoryPriceSet.setMoney(money);
        csStoreHotelCategoryPriceSet.setVb(vb);
        SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtils.isNotBlank(beginTime)) {
            csStoreHotelCategoryPriceSet.setBeginTime(sfd.parse(beginTime));
        }
        if (StringUtils.isNotBlank(endTime)) {
            csStoreHotelCategoryPriceSet.setEndTime(sfd.parse(endTime));
        }
        rc.setStatus(ResultCodeStatus.SUCCESS);
        rc.setMsg("修改成功");
        return rc;
    }

    /**
     * @param hotelCategoryId 客房的主键id
     * @@describe: 查看客房周期价格管理列表
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/3/1 10:15
     */
    public ResultCode<PageInfo<CsStoreHotelCategoryPriceSet>> getPriceSetList(@RequestParam(value = "PageNum", required = false, defaultValue = "1") Integer PageNum,
                                                                              @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                                              @RequestParam(value = "hotelCategoryId", required = true) Long hotelCategoryId) {
        ResultCode<PageInfo<CsStoreHotelCategoryPriceSet>> rc = new ResultCode<PageInfo<CsStoreHotelCategoryPriceSet>>();
        PageHelper.startPage(PageNum, pageSize);
        List<CsStoreHotelCategoryPriceSet> list = csStoreHotelCategoryPriceSetMapper.getPriceSetList(hotelCategoryId);
        PageInfo<CsStoreHotelCategoryPriceSet> pageInfoList = new PageInfo<CsStoreHotelCategoryPriceSet>(list);
        rc.setContent(pageInfoList);
        rc.setStatus(ResultCodeStatus.SUCCESS);
        rc.setMsg("  查询成功 ");
        return rc;
    }
}
