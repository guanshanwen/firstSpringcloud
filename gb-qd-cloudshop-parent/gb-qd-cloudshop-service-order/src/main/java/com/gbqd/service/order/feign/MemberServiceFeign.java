package com.gbqd.service.order.feign;

import com.gbqd.service.member.MemberService;
import com.gbqd.service.order.hystrix.MemberServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author MrWen
 * @create 2019-01-15-13:59
 */
@FeignClient(value = "service-member" ,fallback = MemberServiceHystrix.class)
public interface MemberServiceFeign extends MemberService {
}
