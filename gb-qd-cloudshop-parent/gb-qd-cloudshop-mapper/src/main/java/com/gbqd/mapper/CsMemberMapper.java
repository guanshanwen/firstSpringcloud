package com.gbqd.mapper;

import com.gbqd.pojo.CsMember;


public interface CsMemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsMember record);

    int insertSelective(CsMember record);

    CsMember selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsMember record);

    int updateByPrimaryKey(CsMember record);
}