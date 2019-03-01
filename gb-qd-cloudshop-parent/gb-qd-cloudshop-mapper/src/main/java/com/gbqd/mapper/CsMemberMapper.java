package com.gbqd.mapper;

import com.gbqd.pojo.member.CsMember;

public interface CsMemberMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CsMember record);

    int insertSelective(CsMember record);

    CsMember selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CsMember record);

    int updateByPrimaryKey(CsMember record);

    CsMember selectByPrimaryMemberId(String memberId);//根据用户ID 查询用户详情

    CsMember selectByPhone(String phone);//根据用户phone查询用户详情
}