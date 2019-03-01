package com.gbqd.service.goods.controller;

import com.gbqd.common.utils.ResultCode;
import com.gbqd.pojo.goods.CsStoreBatchDiscount;
import com.gbqd.service.goods.CsStoreBatchDiscountService;
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

import java.util.Date;

@RestController
@RequestMapping("/storeBatchDiscount")
@Api(description = "店铺批量折扣")
public class CsStoreBatchDiscountController {
    @Autowired
    CsStoreBatchDiscountService csStoreBatchDiscountService;

    @ApiOperation(value = "店铺批量折扣展示")
    @ApiImplicitParams({@ApiImplicitParam(name = "storeId", value = "店铺ID", defaultValue = "1524262555" ,required = true, dataType = "Long", paramType = "query"),
                    @ApiImplicitParam(name = "beginTime", value = "折扣开始时间", required = false, dataType = "String", paramType = "query"),
                    @ApiImplicitParam(name = "endTime", value = "折扣结束时间", required = false, dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/selectDiscountListByStoreId", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResultCode selectDiscountListByStoreId(@RequestParam(value = "storeId", required = true) Long storeId,
     @RequestParam(value = "beginTime", required = false) Date beginTime, @RequestParam(value = "endTime", required = false)  Date endTime) {
        ResultCode rc = csStoreBatchDiscountService.selectDiscountListByStoreId(storeId,beginTime,endTime);
        return rc;
    }

    @ApiOperation(value = "店铺批量折扣-增加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberId", value = "当前用户ID",required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "storeId", value = "店铺ID", defaultValue = "1524262555" ,required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "beginTime", value = "折扣开始时间", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "endTime", value = "折扣结束时间", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "discount", value = "折扣数", required = false, dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/batchDiscountAdd", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResultCode batchDiscountAdd(
                                       @RequestParam(value = "memberId", required = true) String memberId,
                                       @RequestParam(value = "storeId", required = true) Long storeId,
                                       @RequestParam(value = "beginTime", required = true) Date beginTime,
                                       @RequestParam(value = "endTime", required = true)  Date endTime,
                                       @RequestParam(value = "discount", required = true)  String discount) {
        ResultCode rc = csStoreBatchDiscountService.batchDiscountAdd(memberId,storeId,beginTime,endTime,discount);
        return rc;
    }


    @ApiOperation(value = "店铺批量折扣-删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "行索引id",required = true, allowMultiple=true, dataType = "Long", paramType = "query")
    })
    @RequestMapping(value = "/batchDiscountDel", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResultCode batchDiscountDel(@RequestParam(value = "id", required = true) Long[] ids) {
        ResultCode rc = csStoreBatchDiscountService.batchDiscountdel(ids);
        return rc;
    }



    @ApiOperation(value = "店铺批量折扣-更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "序列id",required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "memberId", value = "当前用户ID",required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "beginTime", value = "折扣开始时间", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "endTime", value = "折扣结束时间", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "discount", value = "折扣数", required = false, dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/batchDiscountUpdate", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResultCode batchDiscountUpdate(
                                         @RequestParam(value = "id", required = true) Long id,
                                         @RequestParam(value = "memberId", required = false) String memberId,
                                         @RequestParam(value = "beginTime", required = false) Date beginTime,
                                         @RequestParam(value = "endTime", required = false)  Date endTime,
                                         @RequestParam(value = "discount", required = false)  String discount) {
        ResultCode rc = csStoreBatchDiscountService.batchDiscountUpdate(id,memberId,beginTime,endTime,discount);
        return rc;
    }
}
