package com.gbqd.service.order.hystrix;

import com.gbqd.pojo.CsMember;
import com.gbqd.service.order.feign.MemberServiceFeign;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author MrWen
 * @create 2019-01-15-14:39
 */
@Component
public class MemberServiceHystrix implements MemberServiceFeign {

    public void insert(@RequestParam(value = "member", required = false) CsMember member) {

    }

    ;


    public void update(@RequestParam(value = "member", required = false) CsMember member) {

    }

    ;


    public CsMember find(@RequestParam(value = "id", required = false) int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    ;


    public void delete(@RequestParam(value = "id", required = false) String id) {

    }


    public PageInfo<CsMember> findList(@RequestParam("PageNum") int PageNum,@RequestParam("pageSize") int pageSize) {
        return null;
    }

    ;

    /**
     * @param name
     * @param age
     * @@describe: 服务降级
     * @author: MrWen
     * @return: java.lang.String
     * @date: 2019/1/15 14:45
     */
    @Override
    public String gerMember(String name, String age) {

        return "member接口熔断";
    }
}
