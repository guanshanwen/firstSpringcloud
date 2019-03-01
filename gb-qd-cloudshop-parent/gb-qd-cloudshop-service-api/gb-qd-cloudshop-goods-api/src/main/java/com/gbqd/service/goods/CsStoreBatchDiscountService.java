package com.gbqd.service.goods;

import com.gbqd.common.utils.ResultCode;
import com.gbqd.pojo.goods.CsStoreBatchDiscount;

import java.util.Date;
import java.util.List;

public interface CsStoreBatchDiscountService {
    /**
     * 根据店铺和时间段查找
     * @param storeId 店铺id
     * @param beginTime 打折开始时间
     * @param endTime 打折结束时间
     * @return
     */
   ResultCode<List<CsStoreBatchDiscount>> selectDiscountListByStoreId(Long storeId, Date beginTime, Date endTime);


    /**
     * 店铺批量折扣增加
     * @param storeId 店铺id
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param discount 折扣数
     * @return
     */
    ResultCode<Integer> batchDiscountAdd(String memberId,Long storeId,Date beginTime,Date endTime,String discount);

    /**
     * 折扣删除
     * @param ids
     * @return
     */
    ResultCode<Integer> batchDiscountdel(Long[] ids);

    /**
     *
     * @param memberId
     * @param id
     * @param beginTime
     * @param endTime
     * @param discount
     * @return
     */
    ResultCode<Integer> batchDiscountUpdate(Long id,String memberId,Date beginTime,Date endTime,String discount);

}
