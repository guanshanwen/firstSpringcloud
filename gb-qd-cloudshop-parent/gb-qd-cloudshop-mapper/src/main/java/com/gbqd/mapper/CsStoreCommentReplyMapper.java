package com.gbqd.mapper;

import com.gbqd.pojo.order.CsStoreCommentReply;

import java.util.List;

public interface CsStoreCommentReplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CsStoreCommentReply record);

    int insertSelective(CsStoreCommentReply record);

    CsStoreCommentReply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CsStoreCommentReply record);

    int updateByPrimaryKey(CsStoreCommentReply record);

    int countNumber(Long commentId);//根据评论ID 获取评论回复的总数

    List<CsStoreCommentReply>getReplyList(Long commentId);//根据评论ID 获取评论回复的集合
}