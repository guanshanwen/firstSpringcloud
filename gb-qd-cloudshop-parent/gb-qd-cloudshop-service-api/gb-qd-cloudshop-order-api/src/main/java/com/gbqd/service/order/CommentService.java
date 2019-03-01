package com.gbqd.service.order;

import com.gbqd.common.utils.ResultCode;

import com.gbqd.pojo.order.CsStoreComment;
import com.gbqd.pojo.order.CsStoreCommentReply;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 评论接口
 * @author MrWen
 * @create 2019-02-14-16:15
 */
public interface CommentService {
    /**
     * @@describe: 获取店铺下面的所有评论
     * @author:     MrWen
     * @param storeId 店铺ID
     * @return:      com.gbqd.common.utils.ResultCode
     * @date:        2019/2/14 16:30
     */
    public ResultCode<PageInfo<CsStoreComment>> getCommentList(@RequestParam(value = "storeId", required = true) Long storeId,
                                                               @RequestParam(value = "PageNum", required = false, defaultValue = "1") Integer PageNum,
                                                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,@RequestParam(value = "goodOrBad", required = false) Integer goodOrBad);
    /**
     * @@describe: 获取评论下面的回复集合
     * @author:     MrWen
     * @param commentId 评论ID
     * @return:      com.gbqd.common.utils.ResultCode
     * @date:        2019/2/14 16:30
     */
    public ResultCode<List<CsStoreCommentReply>>getCommentReplyList(@RequestParam(value = "commentId", required = true) Long commentId);
}
