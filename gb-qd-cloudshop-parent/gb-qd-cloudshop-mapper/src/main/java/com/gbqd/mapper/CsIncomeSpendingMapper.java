package com.gbqd.mapper;

import com.gbqd.pojo.goods.CsIncomeSpending;
import io.lettuce.core.dynamic.annotation.Param;

public interface CsIncomeSpendingMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CsIncomeSpending record);

    int insertSelective(CsIncomeSpending record);

    CsIncomeSpending selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CsIncomeSpending record);

    int updateByPrimaryKey(CsIncomeSpending record);

    CsIncomeSpending selectTotal(@Param("storeId")Long storeId);//统计总销售额 总收益

    CsIncomeSpending selectTotalMonth(@Param("storeId") Long storeId, @Param("dateMonth")String dateMonth);//统计月销售额 月收益 月发放VB
}