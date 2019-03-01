package com.gbqd.service.goods.fegin;

import com.gbqd.service.goods.hystrix.MemberServiceHystrix;
import com.gbqd.service.member.MemberService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author MrWen
 * @create 2019-02-12-13:19
 */
@FeignClient(value = "service-member" ,fallback = MemberServiceHystrix.class)
public interface MemberServiceFegin extends MemberService {
}
