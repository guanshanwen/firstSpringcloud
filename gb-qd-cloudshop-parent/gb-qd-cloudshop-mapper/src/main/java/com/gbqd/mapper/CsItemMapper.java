package com.gbqd.mapper;

import com.gbqd.pojo.Item.CsItem;

public interface CsItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CsItem record);

    int insertSelective(CsItem record);

    CsItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CsItem record);

    int updateByPrimaryKey(CsItem record);

    CsItem selectByItemCode(String itemCode);
}