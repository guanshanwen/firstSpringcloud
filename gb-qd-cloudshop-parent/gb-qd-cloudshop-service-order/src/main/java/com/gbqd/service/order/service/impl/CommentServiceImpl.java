package com.gbqd.service.order.service.impl;

import com.gbqd.common.utils.ResultCode;
import com.gbqd.common.utils.enums.ResultCodeStatus;
import com.gbqd.mapper.CsStoreCommentComponentMapper;
import com.gbqd.mapper.CsStoreCommentMapper;
import com.gbqd.mapper.CsStoreCommentReplyMapper;
import com.gbqd.pojo.order.CsStoreComment;
import com.gbqd.pojo.order.CsStoreCommentComponent;
import com.gbqd.pojo.order.CsStoreCommentReply;
import com.gbqd.service.order.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author MrWen
 * @create 2019-02-14-16:38
 */
@RestController
public class CommentServiceImpl implements CommentService {
    @Autowired
    CsStoreCommentMapper csStoreCommentMapper;
    @Autowired
    CsStoreCommentComponentMapper csStoreCommentComponentMapper;
    @Autowired
    CsStoreCommentReplyMapper  csStoreCommentReplyMapper;
    /**
     * @@describe: 获取店铺下面的所有评论
     * @author:     MrWen
     * @param storeId 店铺ID
     * @return:      com.gbqd.common.utils.ResultCode
     * @date:        2019/2/14 16:30
     */
    @Override
    public ResultCode<PageInfo<CsStoreComment>> getCommentList(@RequestParam(value = "storeId", required = true) Long storeId,

                                                               @RequestParam(value = "PageNum", required = false, defaultValue = "1") Integer PageNum,
                                                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,@RequestParam(value = "goodOrBad", required = false) Integer goodOrBad){
        ResultCode<PageInfo<CsStoreComment>> rc=new ResultCode<PageInfo<CsStoreComment>>();
        PageHelper.startPage(PageNum, pageSize);
        //根据店铺ID 查询该店铺下的所有评论
        List<CsStoreComment> list=csStoreCommentMapper.getCommentList(storeId,goodOrBad);
        for (int i = 0; i <list.size() ; i++) {
            List<CsStoreCommentComponent>getList =csStoreCommentComponentMapper.getList(list.get(i).getId());//根据评论的ID 查询评论下的图片
            list.get(i).setPictureList(getList);
            //查询该评论下面有多少回复总数
            list.get(i).setCommentNumber(csStoreCommentReplyMapper.countNumber(list.get(i).getId()));
        }


        PageInfo<CsStoreComment> pageInfoList = new PageInfo<CsStoreComment>(list);
        rc.setContent(pageInfoList);
        rc.setStatus(ResultCodeStatus.SUCCESS);
        rc.setMsg(" 查询成功 ");
        return rc;
    }


    /**
     * @@describe: 获取评论下面的回复集合
     * @author:     MrWen
     * @param commentId 评论ID
     * @return:      com.gbqd.common.utils.ResultCode
     * @date:        2019/2/14 16:30
     */
    public ResultCode< List<CsStoreCommentReply>>getCommentReplyList(@RequestParam(value = "commentId", required = true) Long commentId){
        ResultCode<List<CsStoreCommentReply>> rc=new ResultCode< List<CsStoreCommentReply>>();
        List<CsStoreCommentReply>list =csStoreCommentReplyMapper.getReplyList(commentId);
        rc.setContent(list);
        rc.setStatus(ResultCodeStatus.SUCCESS);
        rc.setMsg(" 查询成功 ");
        return rc;
    }
}
