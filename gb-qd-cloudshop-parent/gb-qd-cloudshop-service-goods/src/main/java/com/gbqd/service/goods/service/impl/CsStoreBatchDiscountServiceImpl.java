package com.gbqd.service.goods.service.impl;

import com.gbqd.common.utils.RandomCodeUtil;
import com.gbqd.common.utils.ResultCode;
import com.gbqd.common.utils.enums.ResultCodeStatus;
import com.gbqd.mapper.CsStoreBatchDiscountMapper;
import com.gbqd.pojo.goods.CsStoreBatchDiscount;
import com.gbqd.service.goods.CsStoreBatchDiscountService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
@RestController
public class CsStoreBatchDiscountServiceImpl implements CsStoreBatchDiscountService {
    @Autowired
    CsStoreBatchDiscountMapper csStoreBatchDiscountMapper;
    /**
     * 根据店铺和时间段查找
     *
     * @param storeId   店铺id
     * @param beginTime 打折开始时间
     * @param endTime   打折结束时间
     * @return
     */
    @Override
    public ResultCode<List<CsStoreBatchDiscount>>   selectDiscountListByStoreId(Long storeId, Date beginTime, Date endTime) {
        ResultCode<List<CsStoreBatchDiscount>> rr = ResultCode.getNewInstance();
        if(storeId==null){
            rr.setStatus(ResultCodeStatus.FAIL);
            rr.setMsg("商铺id不能为空");
        }
        List<CsStoreBatchDiscount> list = csStoreBatchDiscountMapper.selectDiscountListByStoreId( storeId, beginTime, endTime);
        for(CsStoreBatchDiscount cc : list){
            cc.setRateStr((int)(cc.getRate()*100)+"");
        }
        rr.setContent(list);
        rr.setStatus(ResultCodeStatus.SUCCESS);
        return rr;
    }

    /**
     * 店铺批量折扣增加
     *
     * @param storeId   店铺id
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @param discount  折扣数
     * @return
     */
    @Override
    public ResultCode<Integer> batchDiscountAdd(String memberId,Long storeId, Date beginTime, Date endTime, String discount) {
        ResultCode<Integer> rr = ResultCode.getNewInstance();
        if(StringUtils.isBlank(discount)){
            rr.setMsg("折扣不能空");
            rr.setStatus(ResultCodeStatus.FAIL);
            return rr;
        }else if(Double.parseDouble(discount)>=1){
            rr.setMsg("折扣不能大于1");
            rr.setStatus(ResultCodeStatus.FAIL);
            return rr;
        }
        CsStoreBatchDiscount cbd = new CsStoreBatchDiscount();
        Long newid=RandomCodeUtil.getId();
        cbd.setId(newid);
        cbd.setStoreId(storeId);
        cbd.setBeginTime(beginTime);
        cbd.setEndTime(endTime);
        cbd.setMemberId(memberId);
        cbd.setRate(Double.parseDouble(discount));
        csStoreBatchDiscountMapper.insertSelective(cbd);
        rr.setStatus(ResultCodeStatus.SUCCESS);
        return rr;
    }

    /**
     * 折扣删除
     *
     * @param ids
     * @return
     */
    @Override
    public ResultCode<Integer> batchDiscountdel(Long[] ids) {
        int num= csStoreBatchDiscountMapper.batchDiscountdel(ids);
            ResultCode<Integer> rr = ResultCode.getNewInstance();
            rr.setStatus(ResultCodeStatus.SUCCESS);
            rr.setContent(num);
        return rr;
    }

    /**
     * 更新
     * @param id
     * @param memberId
     * @param beginTime
     * @param endTime
     * @param discount
     * @return
     */
    @Override
    public ResultCode<Integer> batchDiscountUpdate(Long id,String memberId, Date beginTime, Date endTime, String discount) {
        ResultCode<Integer> rr = ResultCode.getNewInstance();
        if(StringUtils.isNotBlank(discount)){
            if(Double.parseDouble(discount)>=1){
                rr.setMsg("折扣不能大于1");
                rr.setStatus(ResultCodeStatus.FAIL);
                return rr;
            }
        }
        CsStoreBatchDiscount cbd = new CsStoreBatchDiscount();
        cbd.setId(id);
        cbd.setBeginTime(beginTime);
        cbd.setEndTime(endTime);
        cbd.setMemberId(memberId);
        cbd.setRate(Double.parseDouble(discount));
        int num =csStoreBatchDiscountMapper.updateByPrimaryKeySelective(cbd);
        rr.setStatus(ResultCodeStatus.SUCCESS);
        rr.setContent(num);
        return rr;
    }
}
