package com.gbqd.service.member.controller;

import com.aliyuncs.exceptions.ClientException;
import com.gbqd.common.utils.RedisUtil;
import com.gbqd.common.utils.ResultCode;
import com.gbqd.pojo.member.CsMember;
import com.gbqd.service.member.MemberService;
import com.gbqd.service.member.service.impl.ALiMsg;
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
 * @create 2019-01-16-9:59
 */
@RestController
@RequestMapping("/member")
@Api(description = "用户信息系统")
public class MemberController {
    @Autowired
    MemberService memberService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    ALiMsg aLiMsg;


    @ApiOperation(value = "注册-未开发")
    @RequestMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResultCode register(@RequestParam(value = "id") Long id) {
        return ResultCode.getNewInstance();
    }

    /**
     * 登陆接口
     * @param loginAccount 用户id或电话
     * @param password 密码
     * @param authCode 验证码
     * @return
     */
    @ApiOperation(value = "登陆接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginAccount", value = "电话或用户账户 ex:ligangUser0001 sevenDayUser0002",defaultValue = "ligangUser0001" ,required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码 ", defaultValue = "123456" ,required = true, dataType = "String"),
            @ApiImplicitParam(name = "authCode", value = "测试期间不需要传", required = false, dataType = "String")
    })
    @RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResultCode login(String loginAccount, String password, String authCode) {
        ResultCode rr= memberService.login(loginAccount, password, authCode);
        return rr;
    }

    /**
     * 获取校验码
     *
     * @param phone 电话号码
     * @return
     */
    @ApiOperation(value = "获取短信验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "电话",required = true, dataType = "String"),
            @ApiImplicitParam(name = "type", value = " type 1.注册  2.修改密码 3登录 4绑卡身份验证 ",required = true, dataType = "int")
    })
    @RequestMapping(value = "/getAuthCode", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResultCode<Integer> getAuthCode(@RequestParam(value = "phone") String  phone,
                                           @RequestParam(value = "type")Integer type) throws ClientException {
        return  aLiMsg.sendSmsCode(phone,type);
    }

    /**
     * 更新用户资料
     *
     * @param phone      电话号码
     * @param memberName 用户名
     * @param idType     证件类型
     * @param idCode     证件证号
     * @param mailAdress 邮箱地址
     * @return
     */
    @ApiOperation(value = "更新用户资料-未开发")
    @RequestMapping(value = "/updatePersonDocument", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResultCode updatePersonDocument(String phone, String memberName, String idType, String idCode, String mailAdress) {
        return ResultCode.getNewInstance();
    }

    /**
     * 重设手机号
     *
     * @param memberId 用户id
     * @param newPhone 新手机号
     * @param idType   证件类型
     * @param idCode   证件号
     * @param authCode 校验码
     * @return
     */
    @ApiOperation(value = "重设手机号-未开发")
    @RequestMapping(value = "/resetPhone", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResultCode resetPhone(String memberId, String newPhone, String idType, String idCode, String authCode) {
        return ResultCode.getNewInstance();
    }

    /**
     * 重设密码
     *
     * @param memberId   用户id
     * @param oldPwd     旧密码
     * @param newPwd     新密码
     * @param confirmpwd 确认密码
     * @param authCode   验证码
     * @return
     */
    @ApiOperation(value = "重设密码-未开发")
    @RequestMapping(value = "/resetPassWord", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResultCode resetPassWord(String memberId, String oldPwd, String newPwd, String confirmpwd, String authCode) {
        return ResultCode.getNewInstance();
    }

    /**
     * 提交工单
     *
     * @param memberId 用户id
     * @param storeId  商店id
     * @param content  内容
     * @return
     */
    @ApiOperation(value = "提交工单-未开发")
    @RequestMapping(value = "/putWorkBill", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResultCode putWorkBill(String memberId, Long storeId, String content) {
        return ResultCode.getNewInstance();
    }


    /**
     * 工单展示-查询工单
     *
     * @param wb_code 工单号
     * @param begTime 提交时间-开始
     * @param endTime 提交时间-结束
     * @param keyWord 关键字
     * @param StoreId 店铺id
     * @return
     */
    @ApiOperation(value = "展示工单-未开发")
    @RequestMapping(value = "/getWorkBill", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResultCode getWorkBill(String wb_code, String begTime, String endTime, String keyWord, Long StoreId) {
        return ResultCode.getNewInstance();
    }

    /**
     * 工单详情
     *
     * @param wb_code 工单号
     * @return
     */
    @ApiOperation(value = "展示工单-未开发")
    @RequestMapping(value = "/getWorkBillDetail", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResultCode getWorkBillDetail(String wb_code) {
        return ResultCode.getNewInstance();
    }




  /*  *//**
     * @param memberId 用户id
     * @@describe: 我邀请的会员列表
     * @author: MrWen
     * @return: java.util.List<com.gbqd.pojo.member.CsMember>
     * @date: 2019/1/30 17:35
     *//*
    @ApiOperation(value = "我邀请的会员列表-未开发")
    @RequestMapping(value = "/inviteMembers", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<CsMember> inviteMembers(@RequestParam(value = "memberId") Integer memberId) {

        return null;
    }
*/
}
