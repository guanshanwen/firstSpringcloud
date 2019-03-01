package com.gbqd.mapper;

import com.gbqd.pojo.goods.CsStoreHotelCategoryPriceSet;

import java.util.List;

public interface CsStoreHotelCategoryPriceSetMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CsStoreHotelCategoryPriceSet record);

    int insertSelective(CsStoreHotelCategoryPriceSet record);

    CsStoreHotelCategoryPriceSet selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CsStoreHotelCategoryPriceSet record);

    int updateByPrimaryKey(CsStoreHotelCategoryPriceSet record);

    List<CsStoreHotelCategoryPriceSet>getPriceSetList(Long hotelCategoryId);//通过客房ID 查询该客房下面的价格管理设置列表
}