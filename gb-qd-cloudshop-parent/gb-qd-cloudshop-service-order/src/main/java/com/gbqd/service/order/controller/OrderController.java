package com.gbqd.service.order.controller;

import com.gbqd.common.utils.ResultCode;
import com.gbqd.pojo.order.CsOrder;
import com.gbqd.pojo.order.CsStoreComment;
import com.gbqd.service.order.CommentService;
import com.gbqd.service.order.OrderService;
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

/**
 * @author MrWen
 * @create 2019-01-15-15:59
 */
@RestController
@RequestMapping("/order")
@Api(description = "订单服务系统")
public class OrderController {
    @Autowired
    OrderService orderService;


    /**
     * @param storeId  店铺ID
     * @param memberId 用户ID
     * @@describe: 查询用户的消费记录
     * @author: MrWen
     * @return: java.util.List<com.gbqd.pojo.order.CsOrder>
     * @date: 2019/1/31 14:06
     */
    @ApiOperation(value = "消费记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "PageNum", value = "页数", required = false, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页展示多少条", required = false, dataType = "int"),
            @ApiImplicitParam(name = "memberId", value = "用户ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "storeId", value = "店铺 id example:1524262555 ", required = true, dataType = "Long", paramType = "query")
    })
    @RequestMapping(value = "/getOrderList", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResultCode<PageInfo<CsOrder>> getOrderList(@RequestParam(value = "storeId", required = true) Long storeId,
                                                      @RequestParam(value = "memberId", required = true) String memberId,
                                                      @RequestParam(value = "PageNum", required = false, defaultValue = "1") Integer PageNum,
                                                      @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize
    ) {
        //用过用户ID店铺ID 获取该用户的订单信息列表
        return orderService.getOrderList(storeId, memberId, PageNum, pageSize);

    }


}
