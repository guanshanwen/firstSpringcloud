package com.gbqd.service.order.service.impl;

import com.gbqd.common.utils.ResultCode;
import com.gbqd.common.utils.enums.ResultCodeStatus;
import com.gbqd.mapper.CsOrderMapper;
import com.gbqd.pojo.order.CsOrder;
import com.gbqd.service.order.OrderService;
import com.gbqd.service.order.feign.MemberServiceFeign;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author MrWen
 * @create 2019-01-15-13:41
 */
@RestController
public class OrderServiceImpl implements OrderService {
    @Autowired
    MemberServiceFeign memberServiceFeign;
    @Autowired
    CsOrderMapper csOrderMapper;

    /**
     * @param storeId  店铺ID
     * @param memberId 用户ID
     * @@describe: 查询用户的消费记录
     * @author: MrWen
     * @return: java.util.List<com.gbqd.pojo.order.CsOrder>
     * @date: 2019/1/31 14:06
     */
    public ResultCode<PageInfo<CsOrder>> getOrderList(@RequestParam(value = "storeId", required = true) Long storeId,
                                                      @RequestParam(value = "memberId", required = true) String memberId,
                                                      @RequestParam(value = "PageNum", required = false, defaultValue = "1") Integer PageNum,
                                                      @RequestParam(value = "pageSize", required = false,defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(PageNum, pageSize);
        List<CsOrder> list = csOrderMapper.getOrderList(storeId, memberId);
        ResultCode<PageInfo<CsOrder>> rc = new ResultCode<PageInfo<CsOrder>>();
        PageInfo<CsOrder> pageInfoList = new PageInfo<CsOrder>(list);
        rc.setContent(pageInfoList);
        rc.setStatus(ResultCodeStatus.SUCCESS);
        rc.setMsg(" 查询成功");
        return rc;
    }


}
