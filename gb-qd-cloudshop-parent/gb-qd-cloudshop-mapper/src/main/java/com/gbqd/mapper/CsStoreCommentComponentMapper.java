package com.gbqd.mapper;

import com.gbqd.pojo.order.CsStoreCommentComponent;

import java.util.List;

public interface CsStoreCommentComponentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CsStoreCommentComponent record);

    int insertSelective(CsStoreCommentComponent record);

    CsStoreCommentComponent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CsStoreCommentComponent record);

    int updateByPrimaryKey(CsStoreCommentComponent record);

    List<CsStoreCommentComponent>getList(Long commentId);//根据评论ID 查询该评论下的图片集合
}