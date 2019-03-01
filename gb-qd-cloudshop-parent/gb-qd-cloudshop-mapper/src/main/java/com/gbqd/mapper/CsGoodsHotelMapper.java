package com.gbqd.mapper;

import com.gbqd.pojo.goods.CsGoodsHotel;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface CsGoodsHotelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CsGoodsHotel record);

    int insertSelective(CsGoodsHotel record);

    CsGoodsHotel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CsGoodsHotel record);

    int updateByPrimaryKey(CsGoodsHotel record);

    /**
     * 根据GCid获取房屋列表
     *
     * @param gcRoomId
     * @return
     */
    List<CsGoodsHotel> getRoomFromGcId(Long gcRoomId);

    /**
     * 查询 房间列表
     * @param storeId 店铺ID
     * @param ghcId 客房类ID
     * @param gName 房间号
     * @param roomStatus 房间状态 1:空闲 2：入住 3：维修
     * @return
     */
    List<CsGoodsHotel> getRoomList(@Param("storeId") Long storeId,
                                   @Param("ghcId") Long ghcId,
                                   @Param("gName") String gName,
                                   @Param("roomStatus") String roomStatus);
}