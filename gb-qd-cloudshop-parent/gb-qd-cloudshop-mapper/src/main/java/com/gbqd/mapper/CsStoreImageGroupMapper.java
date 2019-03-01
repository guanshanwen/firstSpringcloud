package com.gbqd.mapper;


import com.gbqd.pojo.goods.CsStoreImageGroup;
import com.gbqd.pojo.goods.Sort;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface CsStoreImageGroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CsStoreImageGroup record);

    int insertSelective(CsStoreImageGroup record);

    CsStoreImageGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CsStoreImageGroup record);

    int updateByPrimaryKey(CsStoreImageGroup record);


    List<CsStoreImageGroup> getCsStoreImageGroupList(Long storeId);//根据店铺Id 查询list
    List<CsStoreImageGroup> getCsStoreImageGroupNo(@Param("storeId") Long storeId, @Param("sort")Integer sort,@Param("nowStort") Integer nowStort);
    //往下排 查出区间
    List<CsStoreImageGroup> getCsStoreImageGroupDown(@Param("storeId") Long storeId, @Param("sort")Integer sort,@Param("nowStort") Integer nowStort);
    //往上排 查出区间 sort目标位置
    List<CsStoreImageGroup> getCsStoreImageGroupUp(@Param("storeId") Long storeId, @Param("sort")Integer sort,@Param("nowStort") Integer nowStort);

    List<CsStoreImageGroup> getCsStoreImageGroupDelete(@Param("storeId") Long storeId, @Param("sort")Integer sort);
    // int editSeq(Sort sortList);//批量更新排序
}