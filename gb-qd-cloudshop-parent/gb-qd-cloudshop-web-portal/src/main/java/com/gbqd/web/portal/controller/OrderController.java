package com.gbqd.web.portal.controller;

import com.gbqd.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MrWen
 * @create 2019-01-15-15:08
 */
@RestController
@RequestMapping("/web/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    /**
     * @param id 订单ID
     * @param code 订单号
     * @@describe: 返回订单信息实现类
     * @author: MrWen
     * @return: java.lang.String
     * @date: 2019/1/15 13:42
     */
    @RequestMapping("/getOrder")
    public String getOrder(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "code", required = false) String code){
       return  orderService.getOrder(id,code);
    }
}
