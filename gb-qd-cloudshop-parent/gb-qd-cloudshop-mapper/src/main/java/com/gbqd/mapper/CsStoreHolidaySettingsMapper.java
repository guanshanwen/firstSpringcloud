package com.gbqd.mapper;

import com.gbqd.pojo.goods.CsStoreHolidaySettings;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface CsStoreHolidaySettingsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CsStoreHolidaySettings record);

    int insertSelective(CsStoreHolidaySettings record);

    CsStoreHolidaySettings selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CsStoreHolidaySettings record);

    int updateByPrimaryKey(CsStoreHolidaySettings record);
    List<CsStoreHolidaySettings> getStoreHolidaySettingsList(Long storeId);//根据店铺ID 查询店铺下面设置的假期区间列表

    List<CsStoreHolidaySettings>getOnOffList(Integer onOff);//通过开关状态查询()

    List<CsStoreHolidaySettings>getOnOffStoreList(@Param("onOff") Integer onOff,@Param("storeId") Long storeId);//查询该店铺下面的开关打开的假期区间列表
}