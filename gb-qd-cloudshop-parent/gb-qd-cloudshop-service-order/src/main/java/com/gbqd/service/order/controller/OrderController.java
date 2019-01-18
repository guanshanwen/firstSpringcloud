package com.gbqd.service.order.controller;

import com.gbqd.pojo.CsMember;
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
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author MrWen
 * @create 2019-01-15-15:59
 */
@RestController
@RequestMapping("/order")
@Api(value = "API - OrderController", description = "订单服务系统")
public class OrderController {
    @Autowired
    OrderService orderService;

    /**
     * @param id   订单ID
     * @param code 订单号
     * @@describe: 返回订单信息实现类
     * @author: MrWen
     * @return: java.lang.String
     * @date: 2019/1/15 13:42
     */
    @ApiOperation(value = "订单信息", notes = "需要传递一个id参数来查询订单的详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "每个 id 对应一个订单", required = true, dataType = "String"),
            @ApiImplicitParam(name = "code", value = "每个订单有一个code订单号", required = true, dataType = "String")
    })
    /*@ApiResponses({ @ApiResponse(code = 200, message = "Successful — 请求已完成"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求") })*/

    @RequestMapping(value = "/getOrder",method = RequestMethod.GET)
    public String getOrder(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "code", required = false) String code) {
        return orderService.getOrder(id, code);
    }


    /**
     * 测试调用member用户inser接口
     */
    @ApiIgnore
    @RequestMapping(value = "/orderInser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<CsMember> orderInser() {
        return orderService.orderInser();

    }

    @ApiIgnore
    @RequestMapping(value = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
    public CsMember find(@RequestParam(value = "id", required = false) int id) {
        return orderService.find(id);

    }
}
