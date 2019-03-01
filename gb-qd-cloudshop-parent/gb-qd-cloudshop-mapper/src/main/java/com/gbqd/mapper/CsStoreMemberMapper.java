package com.gbqd.mapper;

import com.gbqd.pojo.goods.CsStoreMember;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface CsStoreMemberMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CsStoreMember record);

    int insertSelective(CsStoreMember record);

    CsStoreMember selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CsStoreMember record);

    int updateByPrimaryKey(CsStoreMember record);

  List<CsStoreMember> getStoreMemberList(@Param("storeId") Long storeId, @Param("role") Integer role);//根据店铺ID和用户角色 查询店铺下面的会员或员工列表
}