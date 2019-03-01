package com.gbqd.mapper;

import com.gbqd.pojo.order.CsStoreComment;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface CsStoreCommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CsStoreComment record);

    int insertSelective(CsStoreComment record);

    CsStoreComment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CsStoreComment record);

    int updateByPrimaryKey(CsStoreComment record);

    List<CsStoreComment>getCommentList(@Param("storeId") Long storeId,@Param("goodOrBad")Integer goodOrBad);//根据店铺ID 查询店铺下面的所有评论 评价属性goodOrBad(1.好评 2 中评. 3差评 )
}