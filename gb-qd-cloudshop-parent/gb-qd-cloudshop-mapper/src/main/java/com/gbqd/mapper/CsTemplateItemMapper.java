package com.gbqd.mapper;


import com.gbqd.pojo.template.CsTemplateItem;

import java.util.List;

public interface CsTemplateItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CsTemplateItem record);

    int insertSelective(CsTemplateItem record);

    CsTemplateItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CsTemplateItem record);

    int updateByPrimaryKey(CsTemplateItem record);

    /**
     * 根据店铺和
     * @return
     */
    List<CsTemplateItem> getCsTemplateItemByStoreIdAndTemType(Long buId,Integer temType);
}