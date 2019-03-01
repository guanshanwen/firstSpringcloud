package com.gbqd.service.goods.service.impl;

import com.gbqd.common.utils.ResultCode;
import com.gbqd.common.utils.enums.ResultCodeStatus;
import com.gbqd.mapper.CsStoreMapper;
import com.gbqd.mapper.CsStoreMemberMapper;
import com.gbqd.pojo.goods.CsStore;
import com.gbqd.pojo.goods.CsStoreMember;
import com.gbqd.pojo.member.CsMember;
import com.gbqd.pojo.order.CsOrder;
import com.gbqd.service.goods.CsStoreMemberService;
import com.gbqd.service.goods.fegin.MemberServiceFegin;
import com.gbqd.service.goods.fegin.OrderServiceFegin;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author MrWen
 * @create 2019-01-31-11:08
 */
@RestController
public class CsStoreMemberServiceImpl implements CsStoreMemberService {
    @Autowired
    CsStoreMemberMapper  csStoreMemberMapper;
    @Autowired
    OrderServiceFegin orderServiceFegin;
    @Autowired
    CsStoreMapper  csStoreMapper;
    @Autowired
    MemberServiceFegin memberServiceFegin;
    /**
     * @param storeId 店铺ID
     * @@describe: 店铺我的会员集合接口
     * @author: MrWen
     * @return: com.github.pagehelper.PageInfo<com.gbqd.pojo.goods.CsStoreMember>
     * @date: 2019/1/29 16:04
     */
    public ResultCode getStoreMembersList(@RequestParam(value = "storeId", required = true) Long storeId,
                                                       @RequestParam(value = "PageNum", required = false,defaultValue = "1") int PageNum,
                                                       @RequestParam(value = "pageSize", required = false,defaultValue = "10") int pageSize,
                                                       @RequestParam(value = "role", required = true) int role){
        ResultCode rc=new  ResultCode();
        PageHelper.startPage(PageNum, pageSize);
        //查询店铺下面有多少会员或者员工
        List<CsStoreMember> list = csStoreMemberMapper.getStoreMemberList(storeId,role);
        //员工资料待定
        PageInfo<CsStoreMember> pageInfoList = new PageInfo<CsStoreMember>(list);
        rc.setStatus(ResultCodeStatus.SUCCESS);
        rc.setMsg("查询成功 ");
        rc.setContent(pageInfoList);
        return rc;

    }

    /**
     * @param memberId 用户ID
     * @@describe: 我的会员详情接口
     * @author: MrWen
     * @return: com.gbqd.pojo.goods.CsStoreMember
     * @date: 2019/1/29 16:13
     */
    public ResultCode<CsMember> storeMemberDetails(
                                            @RequestParam(value = "memberId", required = true) String memberId){
        //远程rpc调用用户详情接口
        CsMember member =memberServiceFegin.getMember(memberId);
        ResultCode<CsMember> rc=new ResultCode<CsMember>();
        rc.setStatus(ResultCodeStatus.SUCCESS);
        rc.setContent(member);
        rc.setMsg("查询成功");
        return rc;
    }

    @Override
    /**
     * @param discount 折扣比例
     * @param storeId  店铺ID
     * @@describe: 店铺会员折扣设置
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/1/29 16:58
     */
    @Transactional
    public ResultCode memberDiscount(@RequestParam(value = "discount", required = true) Double discount,
                                     @RequestParam(value = "storeId", required = true) Long storeId){
        ResultCode rc=new  ResultCode();
        CsStore csStore  = csStoreMapper.selectByPrimaryKey(storeId);
        if(csStore!=null){
            csStore.setDiscount(discount);
            csStoreMapper.updateByPrimaryKeySelective(csStore);
            rc.setStatus(ResultCodeStatus.SUCCESS);
            rc.setMsg("会员折扣设置成功");
        }else{
            rc.setStatus(ResultCodeStatus.FAIL);
            rc.setMsg("店铺信息错误,没有该店铺存在");
        }
        return  rc;
    }


}
