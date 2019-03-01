package com.gbqd.service.member.controller;

import com.gbqd.common.utils.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author MrWen
 * @create 2019-02-12-9:27
 */
@RestController
@RequestMapping("/pay")
@Api(description = "支付模块")
public class PayController {

    /**
     * @param name       姓名
     * @param idCard     身份证号
     * @param cardNumber 卡号
     * @@describe: 添加银行卡
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/1/30 17:02
     */
    @RequestMapping(value = "/bindBankCard", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "添加银行卡-未开发")
    public ResultCode bindBankCard(@RequestParam(value = "name") String name,
                                   @RequestParam(value = "idCard") String idCard,
                                   @RequestParam(value = "cardNumber") String cardNumber) {

        return null;
    }

    /**
     * @param memberId 用户ID
     * @@describe: 银行卡列表
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/1/30 15:49
     */
    @RequestMapping(value = "/bindBankCardList", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ApiOperation(value = "银行卡列表-未开发")
    public List bindBankCardList(@RequestParam(value = "memberId") String memberId) {
        return null;
    }


    /**
     * @param bid 银行卡主键id
     * @@describe: 银行删除
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/1/30 15:49
     */
    @RequestMapping(value = "/deletebindBankCard", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ApiOperation(value = "银行删除-未开发")
    public ResultCode deletebindBankCard(@RequestParam(value = "bid") Long bid) {
        return null;
    }


    /**
     * @param bid      提现到的银行卡主键
     * @param money    提现金额
     * @param storeId  店铺ID
     * @param memberId 用户ID
     * @@describe: 申请提现
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/1/30 15:58
     */
    @ApiOperation(value = "申请提现-未开发")
    @RequestMapping(value = "/withdrawal", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResultCode withdrawal(@RequestParam(value = "bid") Long bid,
                                 @RequestParam(value = "money") Double money,
                                 @RequestParam(value = "storeId") Long storeId,
                                 @RequestParam(value = "memberId") String memberId) {

        return null;
    }

    /**
     * @param money    充值金额
     * @param storeId  店铺ID
     * @param memberId 用户ID
     * @@describe: 充值
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/1/30 15:58
     */
    @ApiOperation(value = "充值-未开发")
    @RequestMapping(value = "/topUp", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResultCode topUp(@RequestParam(value = "money") Double money,
                            @RequestParam(value = "storeId") Long storeId,
                            @RequestParam(value = "memberId") String memberId) {
        return null;
    }

}
