package com.gbqd.service.order;

import com.gbqd.common.utils.ResultCode;
import com.gbqd.pojo.order.CsOrder;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 订单接口
 * @author MrWen
 * @create 2019-01-15-11:27
 */
public interface OrderService {

    /**
     * @param storeId  店铺ID
     * @param memberId 用户ID
     * @@describe: 查询用户的消费记录
     * @author: MrWen
     * @return: java.util.List<com.gbqd.pojo.order.CsOrder>
     * @date: 2019/1/31 14:06
     */
    @RequestMapping("/getOrderList")
    public ResultCode<PageInfo<CsOrder>> getOrderList(@RequestParam(value = "storeId", required = true) Long storeId,
                                                      @RequestParam(value = "memberId", required = true) String memberId,
                                                      @RequestParam(value = "PageNum", required = false, defaultValue = "1") Integer PageNum,
                                                      @RequestParam(value = "pageSize", required = false,defaultValue = "10") Integer pageSize);
}
