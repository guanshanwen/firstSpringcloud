package com.gbqd.service.goods.hystrix;

import com.gbqd.common.utils.ResultCode;
import com.gbqd.pojo.member.CsMember;
import com.gbqd.service.goods.fegin.MemberServiceFegin;
import org.springframework.stereotype.Component;

/**
 * @author MrWen
 * @create 2019-02-12-13:20
 */
@Component
public class MemberServiceHystrix implements MemberServiceFegin {
    @Override
    public CsMember getMember(String memberId) {
        return null;
    }

    /**
     * 登陆接口
     *
     * @param loginAccount 用户id
     * @param password     密码
     * @param authCode     邀请码
     * @return
     */
    @Override
    public ResultCode<CsMember> login(String loginAccount, String password, String authCode) {
        return null;
    }


}
