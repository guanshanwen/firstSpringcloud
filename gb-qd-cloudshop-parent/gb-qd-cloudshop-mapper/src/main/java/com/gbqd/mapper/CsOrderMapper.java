package com.gbqd.mapper;

import com.gbqd.pojo.order.CsOrder;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface CsOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CsOrder record);

    int insertSelective(CsOrder record);

    CsOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CsOrder record);

    int updateByPrimaryKey(CsOrder record);

    List<CsOrder> getOrderList(@Param("storeId") Long storeId, @Param("memberId") String memberId);//根据店铺ID和用户ID 查询该用户在这个店铺的消费记录
}