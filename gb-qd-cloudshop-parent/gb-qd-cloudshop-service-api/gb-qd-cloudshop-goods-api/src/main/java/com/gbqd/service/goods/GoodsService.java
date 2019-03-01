package com.gbqd.service.goods;


import com.gbqd.common.utils.ResultCode;
import com.gbqd.pojo.goods.*;
import com.github.pagehelper.PageInfo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface GoodsService {
    /**
     * 根据商铺获取商铺的商品层级
     *
     * @param storeId
     * @return
     */
    public List<CsStoreGoodsHotelCategory> getCsStoreGoodsHotelCategoryByStoreId(Long storeId);

    /**
     * 根据商铺id获取添加"自定义商品添加模板"
     *
     * @param buId
     * @return
     */
    public String getCsStoreGoodsHotelCategoryAddTemp(Long buId, Integer temType);

    /**
     * 获取商品类型
     *
     * @return
     */
    public ResultCode getJsonRoomType(Long buId);

    /**
     * 获取商品维护-房屋维护
     *
     * @param storeId
     * @return
     */
    public ResultCode<StoreMaintainModel> getCsStoreGoodsMaintain(Long storeId, String Optpye);


    /**
     * 修改房屋价格
     *
     * @param gcRoomId 房屋id
     * @param gcPrice  人民币价格
     * @param vbPrice  VB价格
     * @return
     */
    public ResultCode updateGcRoomPrice(Long gcRoomId, Double gcPrice, Double vbPrice);

    /**
     * 更新  房间状态 1:空闲 2：入住 3：维修
     *
     * @param roomId
     * @param roomStatus
     * @return
     */
    public ResultCode updateRoomStatus(Long roomId, Integer roomStatus);


    /**
     * 根据层级房屋id 获取层架下房屋的列表
     *
     * @param gcRoomId 层级房屋id
     * @return
     */
    public ResultCode<List<CsGoodsHotel>> getRoomFromGcId(Long gcRoomId);

    /**
     * @param storeId         店铺ID
     * @param gcName          商品类型名称
     * @param bedType         床型  {0:大床房,1:单人床,2:双人床}
     * @param itemCodeBedType 配置项编码,bed_type的code用来去字典表里找
     * @param orBreakfast     是否含早餐(1.是  0.否)
     * @param breakfastMoney  早餐价格
     * @param goodsType       商品类别 1:日租房 2:分时房
     * @param gcPrice         分类价格 商品层级定义的价格
     * @param vbPrice         VB比例
     * @@describe: 客房管理添加
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/26 16:50
     */
    public ResultCode insertGoodsCategory(@RequestParam(value = "storeId", required = true) Long storeId,
                                          @RequestParam(value = "gcName", required = true) String gcName,
                                          @RequestParam(value = "bedType", required = true) Integer bedType,
                                          @RequestParam(value = "itemCodeBedType", required = true) String itemCodeBedType,
                                          @RequestParam(value = "orBreakfast", required = true) Integer orBreakfast,
                                          @RequestParam(value = "breakfastMoney", required = false) Double breakfastMoney,
                                          @RequestParam(value = "goodsType", required = true) Integer goodsType, @RequestParam(value = "gcPrice", required = true) Double gcPrice,
                                          @RequestParam(value = "vbPrice", required = true) Double vbPrice,
                                          @RequestParam(value = "bedCharacter", required = true) String bedCharacter);

    /**
     * @param id 客房主键ID
     * @@describe: 客房删除功能(逻辑删除)
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/26 16:50
     */
    public ResultCode deleteGoodsCategory(@RequestParam(value = "id", required = true) Long id);

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
                                          @RequestParam(value = "vbPrice", required = false) Double vbPrice);


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
                                                                                @RequestParam(value = "goodsType", required = false) Integer goodsType);

    /**
     * @param id 客房主键ID
     * @@describe: 获取客房详情
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/26 16:50
     */
    public ResultCode<CsStoreGoodsHotelCategory> detailsGoodsCategory(@RequestParam(value = "id", required = true) Long id);


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
                                 @RequestParam(value = "bedTypeName", required = false) String bedTypeName);

    /**
     * @param id
     * @@describe: 房间刪除
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/27 14:25
     */
    public ResultCode deleteRoom(@RequestParam(value = "id", required = true) Long id);

    /**
     * @param id
     * @@describe: 房间详情
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/27 14:25
     */
    public ResultCode<CsGoodsHotel> roomDetails(@RequestParam(value = "id", required = true) Long id);

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
                                 @RequestParam(value = "area", required = false) String area);

    /**
     * @param id
     * @@describe: 房间下架上架
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/27 14:25
     */
    public ResultCode shelvesRoom(@RequestParam(value = "id", required = true) Long id, @RequestParam(value = "saleStatus", required = true) Integer saleStatus);

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
                                                          @RequestParam(value = "roomStatus", required = false) String roomStatus);

    /**
     * @param roomId   房间ID
     * @param memberId 创建人ID
     * @param request  图片集合
     * @@describe:
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/28 13:57
     */
    public ResultCode insertRommImage(@RequestParam(value = "roomId", required = true) Long roomId,
                                      @RequestParam(value = "memberId", required = true) String memberId,
                                      MultipartHttpServletRequest request);

    /**
     * @param id 图片主键ID
     * @@describe:
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/28 13:57
     */
    public ResultCode deletetRommImage(@RequestParam(value = "id", required = true) Long id);

    /**
     * @param roomId 房间主键ID
     * @@describe: 查询房间图片集合
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/28 13:57
     */
    public ResultCode<List<CsGoodsHotelImage>> selectRommImage(@RequestParam(value = "roomId", required = true) Long roomId);

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
                                     @RequestParam(value = "endTime", required = true) String endTime) throws ParseException;

    /**
     * @param id 主键id数组
     * @@describe: 删除客房周期价格管理设置
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/3/1 10:15
     */
    public ResultCode deletePriceSet(@RequestParam(value = "id[]", required = true) Long[] id);

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
                                     @RequestParam(value = "endTime", required = false) String endTime) throws ParseException;
    /**
     * @param hotelCategoryId 客房的主键id
     * @@describe: 查看客房周期价格管理列表
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/3/1 10:15
     */
    public ResultCode<PageInfo<CsStoreHotelCategoryPriceSet>>getPriceSetList(@RequestParam(value = "PageNum", required = false, defaultValue = "1") Integer PageNum,
                                                                             @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                                             @RequestParam(value = "hotelCategoryId", required = true) Long hotelCategoryId);

}
