package com.gbqd.service.member;

import com.gbqd.common.utils.ResultCode;
import com.gbqd.pojo.member.CsMember;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author MrWen
 * @create 2019-01-15-11:15
 */
public interface MemberService {
    /**
     * @param memberId 用户ID
     * @@describe: 根据用户ID 获取用户信息详情
     * @author: MrWen
     * @return: com.gbqd.pojo.member.CsMember
     * @date: 2019/2/12 13:15
     */
    @RequestMapping("/getMember")
    public CsMember getMember(@RequestParam(value = "memberId", required = true) String memberId);

    /**
     * 登陆接口
     * @param loginAccount 用户id
     * @param password 密码
     * @param authCode 邀请码
     * @return
     */
    @RequestMapping("/login")
    public ResultCode<CsMember> login(@RequestParam("loginAccount")String loginAccount,@RequestParam("password")String password,@RequestParam("authCode")  String authCode);
}
