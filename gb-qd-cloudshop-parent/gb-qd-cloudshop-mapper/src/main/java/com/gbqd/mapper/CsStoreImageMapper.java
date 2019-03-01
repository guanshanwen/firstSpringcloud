package com.gbqd.mapper;



import com.gbqd.pojo.goods.CsStoreImage;

import java.util.List;

public interface CsStoreImageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CsStoreImage record);

    int insertSelective(CsStoreImage record);

    CsStoreImage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CsStoreImage record);

    int updateByPrimaryKey(CsStoreImage record);

    List<CsStoreImage> getCsStoreImageList(Long groupId);//根据图片分组主键Id 查询list


}