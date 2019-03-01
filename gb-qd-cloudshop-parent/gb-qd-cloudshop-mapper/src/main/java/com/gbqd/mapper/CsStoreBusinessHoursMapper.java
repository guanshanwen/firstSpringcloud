package com.gbqd.mapper;

import com.gbqd.pojo.goods.CsStoreBusinessHours;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface CsStoreBusinessHoursMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CsStoreBusinessHours record);

    int insertSelective(CsStoreBusinessHours record);

    CsStoreBusinessHours selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CsStoreBusinessHours record);

    int updateByPrimaryKey(CsStoreBusinessHours record);

    List<CsStoreBusinessHours> getStoreList(Long storeId);//根据店铺ID 查询店铺下面的营业时间设置列表

    List<CsStoreBusinessHours> getOnOffStoreList(@Param("onOff") Integer onOff, @Param("storeId") Long storeId);//查询店铺下面开关开着的时间设置列表
}