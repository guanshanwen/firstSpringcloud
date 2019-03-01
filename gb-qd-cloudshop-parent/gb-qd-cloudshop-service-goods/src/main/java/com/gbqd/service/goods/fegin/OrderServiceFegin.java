package com.gbqd.service.goods.fegin;

import com.gbqd.service.goods.hystrix.OrderServiceHystrix;
import com.gbqd.service.order.OrderService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author MrWen
 * @create 2019-01-31-14:26
 */
@FeignClient(value = "service-order" ,fallback = OrderServiceHystrix.class)
public interface OrderServiceFegin extends OrderService {
}
