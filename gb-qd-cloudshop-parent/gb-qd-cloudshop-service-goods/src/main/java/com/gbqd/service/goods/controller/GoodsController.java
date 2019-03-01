package com.gbqd.service.goods.controller;

import com.alibaba.fastjson.JSON;
import com.gbqd.common.utils.ResultCode;
import com.gbqd.common.utils.enums.ResultCodeStatus;
import com.gbqd.pojo.goods.*;
import com.gbqd.service.goods.GoodsService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/goods")
@Api(description = "商品信息")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @ApiOperation(value = "商品层级查询", notes = "商铺自定义商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "storeId", value = "店铺id ex:1524262555", required = true, dataType = "Long", paramType = "query"),
    })
    @RequestMapping(value = "/getCsStoreGoodsHotelCategory", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.GET})
    public List<CsStoreGoodsHotelCategory> getCsStoreGoodsHotelCategory(Long storeId) {

        return goodsService.getCsStoreGoodsHotelCategoryByStoreId(storeId);
    }


    /**
     * 获取行业商品添加模板
     */
    @ApiOperation(value = "商品添加模板", notes = "获取自定义商品添加模板")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "buId", value = "行业id ex:1596353226", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "temType", value = "模板类型 ex:2", required = true, dataType = "int", paramType = "query"),
    })
    @RequestMapping(value = "/getCsStoreGoodsHotelCategoryAddTemp", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.GET})
    public String getCsStoreGoodsHotelCategoryAddTemp(@RequestParam(value = "buId", required = true) Long buId, @RequestParam(value = "temType", required = true) Integer temType) {
        return goodsService.getCsStoreGoodsHotelCategoryAddTemp(buId, temType);
    }


    /**
     * 获取主页数据
     *
     * @param storeId 1524262555
     * @param opType
     * @return
     */
    @ApiOperation(value = "店铺维护主页", notes = "店铺维护列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "storeId", value = "店铺id ex:1524262555", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "opType", value = "操作类型 ex:空 ", required = false, dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/getCsStoreGoodsMaintain", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.GET})
    public ResultCode<StoreMaintainModel> getCsStoreGoodsMaintain(@RequestParam(value = "storeId", required = true) Long storeId,
                                                                  @RequestParam(value = "opType", required = false) String opType) {
        return goodsService.getCsStoreGoodsMaintain(storeId, opType);
    }

    /**
     * 获取商品类型店铺类型
     */
    @ApiOperation(value = "店铺维护-房屋类型", notes = "获取店铺维护-房屋类型列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "buId", value = "行业id ex:1596353226", required = true, dataType = "Long", paramType = "query")
    })
    @RequestMapping(value = "/getJsonRoomType", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.GET})
    public ResultCode getJsonRoomType(Long buId) {
        ResultCode rr = goodsService.getJsonRoomType(buId);
        return rr;
    }


    @ApiOperation(value = "店铺维护-房屋改价", notes = "改房屋价格和VB")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gcRoomId", value = "房屋层级id", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "gcPrice", value = "人民币价格", required = false, dataType = "Double", paramType = "query"),
            @ApiImplicitParam(name = "vbPrice", value = "VB价格", required = false, dataType = "Double", paramType = "query")
    })
    @RequestMapping(value = "/updateGcRoomPrice", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.GET})
    public ResultCode updateRoomPrice(Long gcRoomId, Double gcPrice, Double vbPrice) {
        ResultCode rr = goodsService.updateGcRoomPrice(gcRoomId, gcPrice, vbPrice);
        return rr;
    }


    @ApiOperation(value = "店铺维护-房屋状态维护", notes = "房屋状态修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roomId", value = "房屋id", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "roomStatus", value = "房间状态 1:空闲 2：入住 3：维修", required = true, dataType = "Long", paramType = "query")
    })
    @RequestMapping(value = "/updateRoomStatus", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.GET})
    public ResultCode updateRoomStatus(Long roomId, Integer roomStatus) {
        ResultCode rr = goodsService.updateRoomStatus(roomId, roomStatus);
        return rr;
    }


    @ApiOperation(value = "店铺维护-房屋列表获取", notes = "房屋列表获取,房间状态 1:空闲 2：入住 3：维修")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gcRoomId", value = "房屋层级id", required = true, dataType = "Long", paramType = "query")
    })
    @RequestMapping(value = "/getRoomFromGcId", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.GET})
    public ResultCode<List<CsGoodsHotel>> getRoomFromGcId(Long gcRoomId) {
        ResultCode<List<CsGoodsHotel>> rr = goodsService.getRoomFromGcId(gcRoomId);
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
     * @param vbPrice         VB价
     * @@describe: 客房管理添加
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/26 16:50
     */
    @ApiOperation(value = "客房管理添加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gcName", value = "商品类型名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "bedType", value = "床型 {0:大床房,1:单人床,2:双人床}", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "itemCodeBedType", value = "配置项编码,bed_type的code用来去字典表里找", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "orBreakfast", value = "是否含早餐(1.是  0.否)", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "breakfastMoney", value = "早餐价格", required = false, dataType = "Double", paramType = "query"),
            @ApiImplicitParam(name = "goodsType", value = "商品类别 1:日租房 2:分时房", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "vbPrice", value = "  VB百分比", required = true, dataType = "Double", paramType = "query"),
            @ApiImplicitParam(name = "storeId", value = "  店铺ID", required = true, dataType = "Long", paramType = "query"), @ApiImplicitParam(name = "bedCharacter", value = " bedType对应的床型汉字 (大床房 小床房)展示用", required = true, dataType = "String", paramType = "query")
    })

    @RequestMapping(value = "/insertGoodsCategory", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.POST})
    public ResultCode insertGoodsCategory(@RequestParam(value = "storeId", required = true) Long storeId,
                                          @RequestParam(value = "gcName", required = true) String gcName,
                                          @RequestParam(value = "bedType", required = true) Integer bedType,
                                          @RequestParam(value = "itemCodeBedType", required = true) String itemCodeBedType,
                                          @RequestParam(value = "orBreakfast", required = true) Integer orBreakfast,
                                          @RequestParam(value = "breakfastMoney", required = false) Double breakfastMoney,
                                          @RequestParam(value = "goodsType", required = true) Integer goodsType, @RequestParam(value = "gcPrice", required = true) Double gcPrice,
                                          @RequestParam(value = "vbPrice", required = true) Double vbPrice, @RequestParam(value = "bedCharacter", required = true) String bedCharacter) {

        return goodsService.insertGoodsCategory(storeId, gcName, bedType, itemCodeBedType, orBreakfast, breakfastMoney, goodsType, gcPrice, vbPrice, bedCharacter);
    }


    /**
     * @param id 客房主键ID
     * @@describe: 客房删除功能(逻辑删除)
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/26 16:50
     */
    @ApiOperation(value = "客房删除功能(逻辑删除)")
    @ApiImplicitParam(name = "id", value = "  客房主键ID", required = true, dataType = "Long", paramType = "query")
    @RequestMapping(value = "/deleteGoodsCategory", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.POST})
    public ResultCode deleteGoodsCategory(@RequestParam(value = "id", required = true) Long id) {
        return goodsService.deleteGoodsCategory(id);
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
    @ApiOperation(value = "客房管理修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "客房主键ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "gcName", value = "商品类型名称", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "bedType", value = "床型 {0:大床房,1:单人床,2:双人床}", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "itemCodeBedType", value = "配置项编码,bed_type的code用来去字典表里找", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "orBreakfast", value = "是否含早餐(1.是  0.否)", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "breakfastMoney", value = "早餐价格", required = false, dataType = "Double", paramType = "query"),
            @ApiImplicitParam(name = "goodsType", value = "商品类别 1:日租房 2:分时房", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "vbPrice", value = "  VB百分比", required = false, dataType = "Double", paramType = "query")

    })

    @RequestMapping(value = "/updateGoodsCategory", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.POST})
    public ResultCode updateGoodsCategory(@RequestParam(value = "id", required = true) Long id,
                                          @RequestParam(value = "gcName", required = false) String gcName,
                                          @RequestParam(value = "bedType", required = false) Integer bedType,
                                          @RequestParam(value = "itemCodeBedType", required = false) String itemCodeBedType,
                                          @RequestParam(value = "orBreakfast", required = false) Integer orBreakfast,
                                          @RequestParam(value = "breakfastMoney", required = false) Double breakfastMoney,
                                          @RequestParam(value = "goodsType", required = false) Integer goodsType, @RequestParam(value = "gcPrice", required = false) Double gcPrice,
                                          @RequestParam(value = "vbPrice", required = false) Double vbPrice) {
        return goodsService.updateGoodsCategory(id, gcName, bedType, itemCodeBedType, orBreakfast, breakfastMoney, goodsType, gcPrice, vbPrice);
    }


    /**
     * @param storeId 店铺ID
     * @@describe: 查询该店铺下的客房列表
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/26 16:50
     */
    @ApiOperation(value = "查询该店铺下的客房列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "storeId", value = "店铺 id example:1524262555 ", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "PageNum", value = "页数", required = false, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页展示多少条", required = false, dataType = "int"),
            @ApiImplicitParam(name = "gcName", value = "客房名称", required = false, dataType = "String"),
            @ApiImplicitParam(name = "bedType", value = "房型", required = false, dataType = "int"),
            @ApiImplicitParam(name = "goodsType", value = "商品类别 1:日租房 2:分时房", required = false, dataType = "int")
    })
    @RequestMapping(value = "/getGoodsCategoryList", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResultCode<PageInfo<CsStoreGoodsHotelCategory>> getGoodsCategoryList(@RequestParam(value = "storeId", required = true) Long storeId,
                                                                                @RequestParam(value = "PageNum", required = false, defaultValue = "1") Integer PageNum,
                                                                                @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                                                @RequestParam(value = "gcName", required = false) String gcName,
                                                                                @RequestParam(value = "bedType", required = false) Integer bedType,
                                                                                @RequestParam(value = "goodsType", required = false) Integer goodsType) {
        return goodsService.getGoodsCategoryList(storeId, PageNum, pageSize, gcName, bedType, goodsType);
    }


    /**
     * @param id 客房主键ID
     * @@describe: 获取客房详情
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/26 16:50
     */
    @ApiOperation(value = "获取客房详情")
    @ApiImplicitParam(name = "id", value = "  客房主键ID", required = true, dataType = "Long", paramType = "query")
    @RequestMapping(value = "/detailsGoodsCategory", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.GET})
    public ResultCode<CsStoreGoodsHotelCategory> detailsGoodsCategory(@RequestParam(value = "id", required = true) Long id) {
        return goodsService.detailsGoodsCategory(id);
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
    @ApiOperation(value = "房间添加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "storeId", value = "店铺 id example:1524262555 ", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "ghcId", value = " 房间类别id (客房的主键)", required = false, dataType = "Long"),
            @ApiImplicitParam(name = "gName", value = " 房间名称(1002)", required = false, dataType = "String"),
            @ApiImplicitParam(name = "buId", value = " 二级行业id (星级酒店)", required = false, dataType = "Long"),
            @ApiImplicitParam(name = "roomDesc", value = " 房间描述", required = false, dataType = "String"),
            @ApiImplicitParam(name = "numberPeople", value = " 入住人数", required = false, dataType = "int"),
            @ApiImplicitParam(name = "temId", value = "商品规格模id", required = false, dataType = "Long"),
            @ApiImplicitParam(name = "itemAttr", value = "配置项/规格属性", required = false, dataType = "String"),
            @ApiImplicitParam(name = "area", value = "面积", required = false, dataType = "String"),
    })
    @RequestMapping(value = "/insertRoom", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
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
        return goodsService.insertRoom(storeId, ghcId, gName, buId, roomDesc, numberPeople, temId, itemAttr, area, bedTypeKey, bedTypeName);
    }

    /**
     * @param id
     * @@describe: 房间刪除
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/27 14:25
     */
    @ApiOperation(value = "房间刪除")
    @ApiImplicitParam(name = "id", value = "  房间主键ID", required = true, dataType = "Long", paramType = "query")
    @RequestMapping(value = "/deleteRoom", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.POST})
    public ResultCode deleteRoom(@RequestParam(value = "id", required = true) Long id) {
        return goodsService.deleteRoom(id);
    }

    /**
     * @param id
     * @@describe: 房间详情
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/27 14:25
     */
    @ApiOperation(value = "房间详情")
    @ApiImplicitParam(name = "id", value = "  房间主键ID", required = true, dataType = "Long", paramType = "query")
    @RequestMapping(value = "/roomDetails", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.GET})
    public ResultCode<CsGoodsHotel> roomDetails(@RequestParam(value = "id", required = true) Long id) {
        return goodsService.roomDetails(id);
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
    @ApiOperation(value = "房间修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "  房间主键ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "gName", value = " 房间名称(1002)", required = false, dataType = "String"),
            @ApiImplicitParam(name = "roomDesc", value = " 房间描述", required = false, dataType = "String"),
            @ApiImplicitParam(name = "numberPeople", value = " 入住人数", required = false, dataType = "int"),
            @ApiImplicitParam(name = "itemAttr", value = "配置项/规格属性", required = false, dataType = "String"),
            @ApiImplicitParam(name = "area", value = "面积", required = false, dataType = "String"),
    })
    @RequestMapping(value = "/updateRoom", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.POST})
    public ResultCode updateRoom(@RequestParam(value = "id", required = true) Long id,
                                 @RequestParam(value = "gName", required = false) String gName,
                                 @RequestParam(value = "roomDesc", required = false) String roomDesc,
                                 @RequestParam(value = "numberPeople", required = false) Integer numberPeople,
                                 @RequestParam(value = "itemAttr", required = false) String itemAttr,
                                 @RequestParam(value = "area", required = false) String area) {
        return goodsService.updateRoom(id, gName, roomDesc, numberPeople, itemAttr, area);
    }

    /**
     * @param id
     * @param saleStatus 上架下架 	0:下架 1：上架
     * @@describe: 房间下架上架
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/27 14:25
     */
    @ApiOperation(value = "房间下架上架")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "  房间主键ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "saleStatus", value = "0:下架 1：上架", required = true, dataType = "int"),
    })
    @RequestMapping(value = "/shelvesRoom", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.POST})
    public ResultCode shelvesRoom(@RequestParam(value = "id", required = true) Long id,
                                  @RequestParam(value = "saleStatus", required = true) Integer saleStatus) {
        return goodsService.shelvesRoom(id, saleStatus);
    }

    /**
     * @param storeId 店铺ID
     * @param ghcId   房间类别id (客房的主键)
     * @@describe: 房间列表查询
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/27 14:25
     */
    @ApiOperation(value = "房间列表查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "PageNum", value = "页数", required = false, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页展示多少条", required = false, dataType = "int"),
            @ApiImplicitParam(name = "storeId", value = "店铺 id example:1524262555 ", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "ghcId", value = " 房间类别id (客房的主键)", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "gName", value = " 房间名称(1002)", required = false, dataType = "String"),
            @ApiImplicitParam(name = "roomStatus", value = " 房间状态 1:空闲 2：入住 3：维修", required = false, dataType = "int")})
    @RequestMapping(value = "/getRoomList", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.GET})
    public ResultCode<PageInfo<CsGoodsHotel>> getRoomList(@RequestParam(value = "PageNum", required = false, defaultValue = "1") Integer PageNum,
                                                          @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                          @RequestParam(value = "storeId", required = true) Long storeId,
                                                          @RequestParam(value = "ghcId", required = true) Long ghcId,
                                                          @RequestParam(value = "gName", required = false) String gName,
                                                          @RequestParam(value = "roomStatus", required = false) String roomStatus) {
        return goodsService.getRoomList(PageNum, pageSize, storeId, ghcId, gName, roomStatus);
    }


    /**
     * @param roomId   房间ID
     * @param memberId 创建人ID
     * @param request  图片集合
     * @@describe: 房间添加图片
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/28 13:57
     */
    @ApiOperation(value = "房间添加图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roomId", value = "房间ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "memberId", value = "创建人ID", required = true, dataType = "String")})
    @RequestMapping(value = "/insertRommImage", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.POST})
    public ResultCode insertRommImage(@RequestParam(value = "roomId", required = true) Long roomId,
                                      @RequestParam(value = "memberId", required = true) String memberId,
                                      MultipartHttpServletRequest request) {
        return goodsService.insertRommImage(roomId, memberId, request);
    }

    /**
     * @param id 图片主键ID
     * @@describe:
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/28 13:57
     */
    @ApiOperation(value = "删除图片")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "Long")
    @RequestMapping(value = "/deletetRommImage", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.POST})
    public ResultCode deletetRommImage(@RequestParam(value = "id", required = true) Long id) {
        return goodsService.deleteRoom(id);
    }

    /**
     * @param roomId 房间主键ID
     * @@describe:
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/28 13:57
     */
    @ApiOperation(value = "查询图片列表")
    @ApiImplicitParam(name = "roomId", value = "房间ID", required = true, dataType = "Long")
    @RequestMapping(value = "/selectRommImage", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.POST})
    public ResultCode<List<CsGoodsHotelImage>> selectRommImage(@RequestParam(value = "roomId", required = true) Long roomId) {
        return goodsService.selectRommImage(roomId);
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
    @ApiOperation(value = "添加客房周期价格管理设置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "hotelCategoryId", value = "客房的主键id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "money", value = "房间价格", required = true, dataType = "Double"),
            @ApiImplicitParam(name = "vb", value = "vb的比例", required = true, dataType = "Double"),
            @ApiImplicitParam(name = "beginTime", value = "开始日期", required = true, dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束日期", required = true, dataType = "String")})
    @RequestMapping(value = "/insertPriceSet", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.POST})
    public ResultCode insertPriceSet(@RequestParam(value = "hotelCategoryId", required = true) Long hotelCategoryId,
                                     @RequestParam(value = "money", required = true) Double money,
                                     @RequestParam(value = "vb", required = true) Double vb,
                                     @RequestParam(value = "beginTime", required = true) String beginTime,
                                     @RequestParam(value = "endTime", required = true) String endTime) throws ParseException {
        return goodsService.insertPriceSet(hotelCategoryId, money, vb, beginTime, endTime);
    }

    /**
     * @param id 主键id数组
     * @@describe: 删除客房周期价格管理设置
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/3/1 10:15
     */
    @ApiOperation(value = "删除客房周期价格管理设置")
    @ApiImplicitParam(name = "id", value = "主键id数组", required = true, allowMultiple = true, dataType = "Long")
    @RequestMapping(value = "/deletePriceSet", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.POST})
    public ResultCode deletePriceSet(@RequestParam(value = "id", required = true) Long[] id) {
        return goodsService.deletePriceSet(id);
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
    @ApiOperation(value = "修改客房周期价格管理设置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "money", value = "房间价格", required = true, dataType = "Double"),
            @ApiImplicitParam(name = "vb", value = "vb的比例", required = true, dataType = "Double"),
            @ApiImplicitParam(name = "beginTime", value = "开始日期", required = true, dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束日期", required = true, dataType = "String")})
    @RequestMapping(value = "/updatePriceSet", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.POST})
    public ResultCode updatePriceSet(@RequestParam(value = "id", required = false) Long id,
                                     @RequestParam(value = "money", required = false) Double money,
                                     @RequestParam(value = "vb", required = false) Double vb,
                                     @RequestParam(value = "beginTime", required = false) String beginTime,
                                     @RequestParam(value = "endTime", required = false) String endTime) throws ParseException {
        return goodsService.updatePriceSet(id, money, vb, beginTime, endTime);
    }
    /**
     * @param hotelCategoryId 客房的主键id
     * @@describe: 查看客房周期价格管理列表
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/3/1 10:15
     */
    @ApiOperation(value = "客房周期价格管理列表查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "PageNum", value = "页数", required = false, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页展示多少条", required = false, dataType = "int"),
            @ApiImplicitParam(name = "hotelCategoryId", value = "客房的主键id", required = true, dataType = "Long", paramType = "query")})
    @RequestMapping(value = "/getPriceSetList", produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.GET})
    public ResultCode<PageInfo<CsStoreHotelCategoryPriceSet>> getPriceSetList(@RequestParam(value = "PageNum", required = false, defaultValue = "1") Integer PageNum,
                                                                              @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                                              @RequestParam(value = "hotelCategoryId", required = true) Long hotelCategoryId) {
        return goodsService.getPriceSetList(PageNum,pageSize,hotelCategoryId);
    }
}
