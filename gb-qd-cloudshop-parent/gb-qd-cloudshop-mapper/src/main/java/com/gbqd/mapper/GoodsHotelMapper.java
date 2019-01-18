package com.gbqd.mapper;

import com.gbqd.pojo.GoodsHotel;

public interface GoodsHotelMapper  {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsHotel record);

    int insertSelective(GoodsHotel record);

    GoodsHotel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsHotel record);

    int updateByPrimaryKey(GoodsHotel record);
}