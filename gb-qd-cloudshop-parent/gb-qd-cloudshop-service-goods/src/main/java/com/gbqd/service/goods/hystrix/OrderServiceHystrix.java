package com.gbqd.service.goods.hystrix;

import com.gbqd.common.utils.ResultCode;
import com.gbqd.pojo.order.CsOrder;
import com.gbqd.service.goods.fegin.OrderServiceFegin;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

/**
 * @author MrWen
 * @create 2019-01-31-14:28
 */
@Component
public class OrderServiceHystrix implements OrderServiceFegin {

    @Override
    public ResultCode<PageInfo<CsOrder>> getOrderList(Long storeId, String memberId, Integer PageNum, Integer pageSize) {
        return null;
    }
}
