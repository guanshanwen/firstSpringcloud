package com.gbqd.service.order;

import com.gbqd.pojo.CsMember;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author MrWen
 * @create 2019-01-15-11:27
 */
public interface OrderService {
    /**
     * @param id 订单ID
     * @param code 订单号
     * @@describe:  返回订单信息
     * @author: MrWen
     * @return: java.lang.String
     * @date: 2019/1/15 11:28
     */
    @RequestMapping("/getOrder")
    public String getOrder(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "code", required = false) String code);
    @RequestMapping("/orderInser")
    public PageInfo<CsMember> orderInser( );

    public CsMember find(@RequestParam(value = "id", required = false) int id);
}
