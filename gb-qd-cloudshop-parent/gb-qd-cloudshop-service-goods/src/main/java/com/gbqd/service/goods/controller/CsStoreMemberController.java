package com.gbqd.service.goods.controller;

import com.gbqd.common.utils.ResultCode;
import com.gbqd.pojo.goods.CsStoreMember;
import com.gbqd.pojo.member.CsMember;
import com.gbqd.service.goods.CsStoreMemberService;
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
 * @create 2019-01-29-15:46
 */

@RestController
@RequestMapping("/storeMember")
@Api(description = "店铺会员管理")
public class CsStoreMemberController {

    @ApiOperation(value = "店铺主页B-----未完成")
    @ApiImplicitParam(name = "storeId", value = "店铺ID", required = true, dataType = "Long", paramType = "query")
    @RequestMapping(value = "/getStoreMain", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResultCode getStoreMain(@RequestParam(value = "storeId", required = true) Long storeId) {
        ResultCode rc = ResultCode.getNewInstance();
        return rc;
    }

    @Autowired
    CsStoreMemberService csStoreMemberService;


    /**
     * @param storeId 店铺ID
     * @@describe: 店铺我的会员集合接口
     * @author: MrWen
     * @return: com.github.pagehelper.PageInfo<com.gbqd.pojo.goods.CsStoreMember>
     * @date: 2019/1/29 16:04
     */
    @ApiOperation(value = "我的会员或我的店员列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "PageNum", value = "页数", required = false, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页展示多少条", required = false, dataType = "int"),
            @ApiImplicitParam(name = "storeId", value = "店铺 id example:1524262555 ", required = true, dataType = "Long", paramType = "query"), @ApiImplicitParam(name = "role", value = "用户角色(1.会员 2.员工)", required = true, dataType = "int")
    })

    @RequestMapping(value = "/getStoreMembersList", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResultCode<CsStoreMember> getStoreMembersList(@RequestParam(value = "storeId", required = true) Long storeId,
                                                         @RequestParam(value = "PageNum", required = false, defaultValue = "1") int PageNum,
                                                         @RequestParam(value = "pageSize", required = false,defaultValue = "10") int pageSize,
                                                         @RequestParam(value = "role", required = true) int role) {
        return csStoreMemberService.getStoreMembersList(storeId, PageNum, pageSize, role);
    }


    /**
     * @param memberId 用户ID
     * @@describe: 我的会员详情接口
     * @author: MrWen
     * @return: com.gbqd.pojo.goods.CsStoreMember
     * @date: 2019/1/29 16:13
     */
    @ApiOperation(value = "我的会员详情")

    @ApiImplicitParam(name = "memberId", value = "用户id", required = true, dataType = "String")

    @RequestMapping(value = "/storeMemberDetails", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResultCode<CsMember> storeMemberDetails(
            @RequestParam(value = "memberId", required = true) String memberId) {

        return   csStoreMemberService.storeMemberDetails(memberId);

    }

    /**
     * @param phone 手机号
     * @param code  区分是添加会员还是员工(1.会员 2.员工)
     * @@describe: 添加会员或员工接口
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/1/29 16:50
     */
    @ApiOperation(value = "添加会员或员工-----未完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "用户手机号", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "code", value = "区分是添加会员还是员工(1.会员 2.员工)", required = true, dataType = "int", paramType = "query")
    })

    @RequestMapping(value = "/inserStoreMember", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResultCode inserStoreMember(@RequestParam(value = "phone", required = true) String phone,
                                       @RequestParam(value = "code", required = true) Integer code) {
        //通过用户手机号 去远程拉取C端用户资料 存入本地
        //输入手机号 传送验证码 判断是员工还是会员  员工需要分配权限
        return null;

    }

    /**
     * @param discount 折扣钱数
     * @param storeId  店铺ID
     * @@describe: 店铺会员折扣设置
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/1/29 16:58
     */
    @ApiOperation(value = "店铺会员折扣设置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "discount", value = "折扣钱数", required = true, dataType = "Double", paramType = "query"),
            @ApiImplicitParam(name = "storeId", value = "店铺 id example:1524262555", required = true, dataType = "Long", paramType = "query")
    })
    @RequestMapping(value = "/memberDiscount", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResultCode memberDiscount(@RequestParam(value = "discount", required = true) Double discount,
                                     @RequestParam(value = "storeId", required = true) Long storeId) {


        return csStoreMemberService.memberDiscount(discount, storeId);
    }


}
