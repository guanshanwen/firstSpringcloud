package com.gbqd.service.order.service.impl;

import com.gbqd.pojo.CsMember;
import com.gbqd.service.order.OrderService;
import com.gbqd.service.order.feign.MemberServiceFeign;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MrWen
 * @create 2019-01-15-13:41
 */
@RestController
public class OrderServiceImpl implements OrderService {
    @Autowired
    MemberServiceFeign memberServiceFeign;
    /**
     * @param id 订单ID
     * @param code 订单号
     * @@describe: 返回订单信息实现类
     * @author: MrWen
     * @return: java.lang.String
     * @date: 2019/1/15 13:42
     */

    public String getOrder(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "code", required = false) String code) {
        String memmsg=memberServiceFeign.gerMember("张衍军","33");
        String msg="通过getOrder接口 获得id:"+id+"    和 code"+code+"  参数  和";
        return msg+"             "+memmsg;
    }

    @Override
    public PageInfo<CsMember> orderInser() {
        return null;
    }

    @Override
    public CsMember find(@RequestParam(value = "id", required = false) int id) {
        return  memberServiceFeign.find(id);

    }
    /**
     * 测试调用member用户inser接口
     */
  /*  @Override
    public PageInfo<CsMember> orderInser() {
        return memberServiceFeign.findList(1,3);

    }
*/


}
