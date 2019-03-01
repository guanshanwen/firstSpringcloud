package com.gbqd.mapper;

import com.gbqd.pojo.CsBusinessHierarchy;

public interface CsBusinessHierarchyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CsBusinessHierarchy record);

    int insertSelective(CsBusinessHierarchy record);

    CsBusinessHierarchy selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CsBusinessHierarchy record);

    int updateByPrimaryKey(CsBusinessHierarchy record);
}