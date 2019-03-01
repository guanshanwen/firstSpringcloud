package com.gbqd.service.member.service.impl;

import com.gbqd.common.utils.RedisUtil;
import com.gbqd.common.utils.ResultCode;
import com.gbqd.common.utils.enums.ResultCodeStatus;
import com.gbqd.mapper.CsMemberMapper;
import com.gbqd.mapper.CsStoreMapper;
import com.gbqd.pojo.goods.CsStore;
import com.gbqd.pojo.member.CsMember;
import com.gbqd.service.member.MemberService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author MrWen
 * @create 2019-01-15-13:35
 */
@RestController
public class MemberServiceImpl implements MemberService {
    @Autowired
    CsMemberMapper csMemberMapper;
    @Autowired
    CsStoreMapper csStoreMapper;
    @Autowired
    RedisUtil redisUtil;
    @Value("${server.port}")
    String port;  //在application.yml文件里赋的值
    private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

    /**
     * @param memberId 用户ID
     * @@describe: 根据用户ID 获取用户信息详情
     * @author: MrWen
     * @return: com.gbqd.pojo.member.CsMember
     * @date: 2019/2/12 13:15
     */
    public CsMember getMember(@RequestParam(value = "memberId", required = true) String memberId){
        return csMemberMapper.selectByPrimaryMemberId(memberId);
    }

    /**
     * 登陆接口
     * @param loginAccount 用户id或电话
     * @param password 密码
     * @param authCode 邀请码
     * @return
     */
    @Override
    public ResultCode<CsMember> login(String loginAccount ,String password, String authCode) {
        ResultCode<CsMember> rr =new  ResultCode<CsMember>();

        if(StringUtils.isBlank(loginAccount)){
            rr.setStatus(ResultCodeStatus.FAIL);
            rr.setMsg("请输入账户名或电话号码");
            return rr;
        }else if(StringUtils.isBlank(password)) {
            rr.setStatus(ResultCodeStatus.FAIL);
            rr.setMsg("请输入密码");
            return rr;
        }
        CsMember csMember ;
        if(loginAccount.startsWith("1") && loginAccount.length()==11){
            csMember =  csMemberMapper.selectByPhone(loginAccount);
        }else{
            csMember = csMemberMapper.selectByPrimaryMemberId(loginAccount);
        }
        if(csMember==null || csMember.getId()==null){
            rr.setStatus(ResultCodeStatus.FAIL);
            rr.setMsg("用户不存在");
            return rr;
        }
        if(!password.equals(csMember.getPwd())){
            rr.setStatus(ResultCodeStatus.FAIL);
            rr.setMsg("密码错误");
            return rr;
        }
        //获取店铺列表
        List<CsStore> listStore=csStoreMapper.selectByMemberId(csMember.getMemberId()+"");
        csMember.setMemberStroeList(listStore);
        rr.setContent(csMember);
        rr.setStatus(ResultCodeStatus.SUCCESS);
        rr.setMsg("登陆成功!");
        return rr;
    }



    /*  *//**
     * @param name 姓名
     * @param age  年龄
     * @@describe: 返回用户信息接口实现类
     * @author: MrWen
     * @return: java.lang.String
     * @date: 2019/1/15 13:35
     *//*
    @Override
    public String gerMember(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "age", required = false) String age) {
       redisUtil.set("name","郑国强");
        redisUtil.expire("name",20);
        String msg = "通过member接口 获得name:" + name + "    和 age" + age + "  参数        接口:" + port;
        return msg;

    }*/



}
