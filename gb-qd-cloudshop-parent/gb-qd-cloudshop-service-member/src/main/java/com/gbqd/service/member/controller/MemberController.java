package com.gbqd.service.member.controller;

import com.gbqd.common.utils.RedisUtil;
import com.gbqd.pojo.CsMember;
import com.gbqd.service.member.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
    @ApiOperation(value = "查询用户信息", notes = "需要传递一个id参数来查询用户的详细信息")
    @ApiImplicitParam(name = "id", value = "每个 id 对应一次用户登录", required = true, dataType = "int",paramType="query")
    @RequestMapping(value = "/getfind", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    public CsMember getMember(@RequestParam(value = "id") int id) {
        return memberService.find(id);
    }

    @ApiIgnore
    @RequestMapping(value = "/insert")
    public void getinsert(CsMember member) {
        memberService.insert(member);
    }


  /*  *//**
//     * @param PageNum 页数
//     * @param pageSize 每页几条
     * @@describe: 分页例子
     * @author: MrWen
     * @return: com.github.pagehelper.PageInfo<com.gbqd.pojo.CsMember>
     * @date: 2019/1/17 13:53
     */
   /* @ApiOperation(value = "查询所有用户信息", notes = "传PageNum页数查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "PageNum", value = "页数", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页多少数据", required = true, dataType = "int")
    })
    @RequestMapping(value = "/findList", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    public PageInfo<CsMember> findList(int PageNum, int pageSize){
        return  memberService.findList(PageNum,pageSize);
    }*/
    @ApiIgnore
    @RequestMapping(value = "/getMember")
    public  String getMember(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "age", required = false) String age){
       return  memberService.gerMember(name,age);
    }
    @ApiIgnore
    @RequestMapping(value = "/getRedis")
    public  Object getMembesr(){
       Object ob=  redisUtil.get("name");
       return ob;
    }
    @ApiIgnore
    @RequestMapping(value = "/deleteRedis")
    public void  deleteRedis(){
         redisUtil.remove("name");

    }
}
