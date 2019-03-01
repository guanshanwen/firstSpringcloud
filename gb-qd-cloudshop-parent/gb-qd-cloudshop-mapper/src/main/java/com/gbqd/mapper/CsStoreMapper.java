package com.gbqd.mapper;

import com.gbqd.pojo.goods.CsStore;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface CsStoreMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CsStore record);

    int insertSelective(CsStore record);

    CsStore selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CsStore record);

    int updateByPrimaryKey(CsStore record);

    CsStore selectByStoreKey(Long id);  //查询店铺设置详情
   List<CsStore>  selectByLicenseNo(@Param(value = "licenseNo") String licenseNo);//通过营业执照号 查询是否注册过
    List<CsStore>  selectByMemberId(@Param(value = "memberId") String memberId);

}