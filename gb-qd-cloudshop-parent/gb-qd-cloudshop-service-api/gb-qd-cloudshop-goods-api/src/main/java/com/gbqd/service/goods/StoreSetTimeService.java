package com.gbqd.service.goods;

import com.gbqd.common.utils.ResultCode;
import com.gbqd.pojo.goods.CsStoreBusinessHours;
import com.gbqd.pojo.goods.CsStoreHolidaySettings;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Time;
import java.text.ParseException;
import java.util.Date;
import java.util.List;


/**
 * 店铺营业时间设置接口类
 *
 * @author MrWen
 * @create 2019-02-19-14:39
 */
public interface StoreSetTimeService {
    /**
     * @param storeId   店铺ID
     * @param orRepeat  是否重复(1.是 0.否)
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @param dateOf    日期(1,2,3,4,5,6,7)代表周一至周日
     * @@describe: 店铺营业时间添加
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/19 15:17
     */
    public ResultCode insertBusinessHours(@RequestParam(value = "storeId", required = true) Long storeId,
                                          @RequestParam(value = "beginTime", required = true) String beginTime,
                                          @RequestParam(value = "endTime", required = true) String endTime,
                                          @RequestParam(value = "dateOf", required = true) String dateOf) throws ParseException;

    /**
     * @param id 主键ID
     * @@describe: 删除店铺营业时间设置
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/19 15:17
     */
    public ResultCode deleteBusinessHours(@RequestParam(value = "id", required = true) Long id);


    /**
     * @param id 主键ID
     * @@describe: 修改店铺营业时间设置
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/19 15:17
     */
    public ResultCode updateBusinessHours(@RequestParam(value = "id", required = true) Long id,
                                          @RequestParam(value = "beginTime", required = false) String beginTime,
                                          @RequestParam(value = "endTime", required = false) String endTime);

    /**
     * @param storeId 店铺ID
     * @@describe: 查询店铺营业时间设置列表
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/19 15:17
     */
    public ResultCode<List<CsStoreBusinessHours>> getCsStoreBusinessHoursList(@RequestParam(value = "storeId", required = true) Long storeId);

    /**
     * @param storeId         店铺ID
     * @param managementState 云店经营状态 1:营业中 2:打烊 3:歇业 4:封停/冻结
     * @@describe: 修改店铺营业状态
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/19 15:17
     */
    public ResultCode updateStoreStatus(@RequestParam(value = "storeId", required = true) Long storeId,
                                        @RequestParam(value = "managementState", required = true) Integer managementState);

    /**
     * @param storeId   店铺ID
     * @param beginDate 假期开始时间
     * @param endDate   假期结束时间
     * @@describe: 添加假期区间设置
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/20 9:53
     */
    public ResultCode insertStoreHolidaySettings(@RequestParam(value = "storeId", required = true) Long storeId,
                                                 @RequestParam(value = "beginDate", required = true) String beginDate,
                                                 @RequestParam(value = "endDate", required = true) String endDate) throws ParseException;

    /**
     * @param id 假期设置主键ID
     * @@describe: 删除假期区间设置
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/20 9:53
     */
    public ResultCode deleteStoreHolidaySettings(@RequestParam(value = "id", required = true) Long id);

    /**
     * @param id        假期设置主键ID
     * @param beginDate 假期开始时间
     * @param endDate   假期结束时间
     * @@describe: 修改假期区间设置
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/20 9:53
     */
    public ResultCode updateStoreHolidaySettings(@RequestParam(value = "id", required = true) Long id,
                                                 @RequestParam(value = "beginDate", required = false) Date beginDate,
                                                 @RequestParam(value = "endDate", required = false) Date endDate);

    /**
     * @param storeId 店铺ID
     * @@describe: 查询店铺假期时间设置列表
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/19 15:17
     */
    public ResultCode<List<CsStoreHolidaySettings>> getCsStoreHolidaySettingsList(@RequestParam(value = "storeId", required = true) Long storeId);

    /**
     * @param id 主键ID
     * @param onOff 开关(1.开  0.关)
     * @@describe: 开启或者关闭某条假期设置
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/20 13:47
     */
    public ResultCode holidaySettingsonOff(@RequestParam(value = "id", required = true) Long id,
                                           @RequestParam(value = "onOff", required = true) Integer onOff);

    /**
     * @param id 主键ID
     * @param onOff 开关(1.开  0.关)
     * @@describe: 开启或者关闭某营业时间设置
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/20 13:47
     */
    public ResultCode businessHourssonOff(@RequestParam(value = "id", required = true) Long id,
                                           @RequestParam(value = "onOff", required = true) Integer onOff);

}
