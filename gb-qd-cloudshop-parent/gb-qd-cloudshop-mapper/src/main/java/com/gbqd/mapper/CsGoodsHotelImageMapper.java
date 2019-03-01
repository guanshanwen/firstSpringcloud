package com.gbqd.mapper;

import com.gbqd.pojo.goods.CsGoodsHotelImage;

import java.util.List;

public interface CsGoodsHotelImageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CsGoodsHotelImage record);

    int insertSelective(CsGoodsHotelImage record);

    CsGoodsHotelImage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CsGoodsHotelImage record);

    int updateByPrimaryKey(CsGoodsHotelImage record);

    List<CsGoodsHotelImage>selectRoomList(Long roomId);
}