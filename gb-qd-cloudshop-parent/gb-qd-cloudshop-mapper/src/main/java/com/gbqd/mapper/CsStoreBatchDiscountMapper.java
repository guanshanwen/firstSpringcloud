package com.gbqd.mapper;

import com.gbqd.pojo.goods.CsStoreBatchDiscount;

import java.util.Date;
import java.util.List;

public interface CsStoreBatchDiscountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CsStoreBatchDiscount record);

    int insertSelective(CsStoreBatchDiscount record);

    CsStoreBatchDiscount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CsStoreBatchDiscount record);

    int updateByPrimaryKey(CsStoreBatchDiscount record);

    /**
     * 根据店铺和时间段查找
     * @param storeId 店铺id
     * @param beginTime 打折开始时间
     * @param endTime 打折结束时间
     * @return
     */
    List<CsStoreBatchDiscount> selectDiscountListByStoreId(Long storeId, Date beginTime,Date endTime);

    /**
     * 批量删除折扣
     * @param ids
     * @return
     */
    Integer batchDiscountdel(Long[] ids);
}