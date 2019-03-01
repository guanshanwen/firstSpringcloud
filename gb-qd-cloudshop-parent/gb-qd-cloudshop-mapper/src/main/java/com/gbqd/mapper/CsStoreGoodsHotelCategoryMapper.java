package com.gbqd.mapper;


import com.gbqd.pojo.goods.CsStoreGoodsHotelCategory;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface CsStoreGoodsHotelCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CsStoreGoodsHotelCategory record);

    int insertSelective(CsStoreGoodsHotelCategory record);

    CsStoreGoodsHotelCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CsStoreGoodsHotelCategory record);

    int updateByPrimaryKey(CsStoreGoodsHotelCategory record);

    /**
     * 根据商铺获取商铺的商品层级
     *
     * @param storeId 店铺ID
     * @return
     */
    List<CsStoreGoodsHotelCategory> getCsStoreGoodsHotelCategoryByStoreId(Long storeId);

    /**
     * 根据商铺获取商铺的商品层级
     *
     * @param storeId 店铺ID
     * @param gcName 客房名称
     * @param bedType 房型
     * @param goodsType 商品类别 1:日租房 2:分时房
     * @return
     */
    List<CsStoreGoodsHotelCategory> getCsStoreGoodsHotelCategoryList(@Param("storeId") Long storeId, @Param("gcName")String gcName, @Param("bedType")Integer bedType, @Param("goodsType")Integer goodsType,@Param("shelves")Integer shelves);

    /**
     * 通过床铺类型或房型获取商品房屋类别
     *
     * @param storeId
     * @param roomType
     * @return
     */
    List<CsStoreGoodsHotelCategory> getCsStoreGoodsHotelCategoryByBedTypeOrRoomType(Long storeId, Integer bedType, Integer roomType);
}