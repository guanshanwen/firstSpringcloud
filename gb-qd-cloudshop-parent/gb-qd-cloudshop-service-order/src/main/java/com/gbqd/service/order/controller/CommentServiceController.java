package com.gbqd.service.order.controller;

import com.gbqd.common.utils.ResultCode;
import com.gbqd.pojo.order.CsStoreComment;
import com.gbqd.pojo.order.CsStoreCommentReply;
import com.gbqd.service.order.CommentService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author MrWen
 * @create 2019-02-15-17:16
 */
@RestController
@RequestMapping("/order")
@Api(description = "评价系统")
public class CommentServiceController {
    @Autowired
    CommentService  commentService;
    @Autowired
    CommentService cmmentService;
    /**
     * @@describe: 获取评论下面的回复集合
     * @author:     MrWen
     * @param commentId 评论ID
     * @return:      com.gbqd.common.utils.ResultCode
     * @date:        2019/2/14 16:30
     */
    @ApiOperation(value = "获取评论下面的回复集合")
    @ApiImplicitParam(name = "commentId", value = "评论 id example:1212 ", required = true, dataType = "Long", paramType = "query")
    @RequestMapping(value = "/getCommentReplyList", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResultCode<List<CsStoreCommentReply>> getCommentReplyList(@RequestParam(value = "commentId", required = true) Long commentId){
        return commentService.getCommentReplyList(commentId);
    }

    /**
     * @param storeId 店铺ID
     * @@describe: 获取店铺下面的所有评论
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/14 16:30
     */
    @ApiOperation(value = "获取店铺下面的所有评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "PageNum", value = "页数", required = false, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页展示多少条", required = false, dataType = "int"),
            @ApiImplicitParam(name = "goodOrBad", value = "评价属性(1.好评 2 中评. 3差评  不传的话 默认查全部)", required = false, dataType = "goodOrBad"),
            @ApiImplicitParam(name = "storeId", value = "店铺 id example:1524262555 ", required = true, dataType = "Long", paramType = "query")
    })
    @RequestMapping(value = "/getCommentList", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResultCode<PageInfo<CsStoreComment>> getCommentList(@RequestParam(value = "storeId", required = true) Long storeId,

                                                               @RequestParam(value = "PageNum", required = false, defaultValue = "1") Integer PageNum,
                                                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize, @RequestParam(value = "goodOrBad", required = false) Integer goodOrBad) {
        return cmmentService.getCommentList(storeId, PageNum, pageSize,goodOrBad);
    }
}
