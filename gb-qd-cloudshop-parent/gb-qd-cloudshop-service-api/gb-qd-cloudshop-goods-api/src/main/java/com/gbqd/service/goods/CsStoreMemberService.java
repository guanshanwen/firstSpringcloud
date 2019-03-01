package com.gbqd.service.goods;

import com.gbqd.common.utils.ResultCode;
import com.gbqd.pojo.goods.CsStoreMember;
import com.gbqd.pojo.member.CsMember;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author MrWen
 * @create 2019-01-31-11:10
 */
public interface CsStoreMemberService {
    /**
     * @param storeId 店铺ID
     * @@describe: 店铺我的会员集合接口
     * @author: MrWen
     * @return: com.github.pagehelper.PageInfo<com.gbqd.pojo.goods.CsStoreMember>
     * @date: 2019/1/29 16:04
     */

    public ResultCode<CsStoreMember> getStoreMembersList(@RequestParam(value = "storeId", required = true) Long storeId,
                                                       @RequestParam(value = "PageNum", required = false,defaultValue = "1") int PageNum,
                                                       @RequestParam(value = "pageSize", required = false,defaultValue = "10") int pageSize,
                                                       @RequestParam(value = "role", required = true) int role);
    /**
     * @param memberId 用户ID

     * @@describe: 我的会员详情接口
     * @author: MrWen
     * @return: com.gbqd.pojo.goods.CsStoreMember
     * @date: 2019/1/29 16:13
     */

    public  ResultCode<CsMember> storeMemberDetails(
                                            @RequestParam(value = "memberId", required = true) String memberId);
    /**
     * @param discount 折扣比例
     * @param storeId  店铺ID
     * @@describe: 店铺会员折扣设置
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/1/29 16:58
     */
    public ResultCode memberDiscount(@RequestParam(value = "discount", required = true) Double discount,
                                     @RequestParam(value = "storeId", required = true) Long storeId);
}


