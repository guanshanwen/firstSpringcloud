package com.gbqd.service.goods.controller;

import com.gbqd.common.utils.ResultCode;
import com.gbqd.mapper.CsStoreHolidaySettingsMapper;
import com.gbqd.pojo.goods.CsStoreBusinessHours;
import com.gbqd.pojo.goods.CsStoreHolidaySettings;
import com.gbqd.service.goods.StoreSetTimeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

/**
 * 店铺时间设置控制类
 *
 * @author MrWen
 * @create 2019-02-19-14:39
 */
@RestController
@RequestMapping("/storeSetTime")
@Api(description = "店铺营业时间设置")
public class StoreSetTimeController {
    @Autowired
    StoreSetTimeService storeSetTimeService;
    @Autowired
    CsStoreHolidaySettingsMapper cCsStoreHolidaySettingsMapper;

    /**
     * @param storeId   店铺ID
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @param dateOf    日期(1,2,3,4,5,6,7)代表周一至周日
     * @@describe: 店铺营业时间添加
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/19 15:17
     */
    @ApiOperation(value = "添加店铺营业时间")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "beginTime", value = "开始时间", required = true, dataType ="String"),
            @ApiImplicitParam(name = "dateOf", value = "日期(1,2,3,4,5,6,7)代表周日至周六 1代表周日", required = true, dataType = "String"),
            @ApiImplicitParam(name = "storeId", value = "店铺 id example:1524262555 ", required = true, dataType = "Long", paramType = "query"), @ApiImplicitParam(name = "endTime", value = "结束时间", required = true, dataType = "String")
    })

    @RequestMapping(value = "/insertBusinessHours", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResultCode insertBusinessHours(@RequestParam(value = "storeId", required = true) Long storeId,
                                          @RequestParam(value = "beginTime", required = true) String beginTime,
                                          @RequestParam(value = "endTime", required = true) String endTime,
                                          @RequestParam(value = "dateOf", required = true) String dateOf)throws ParseException {
        return storeSetTimeService.insertBusinessHours(storeId, beginTime, endTime, dateOf);
    }

    /**
     * @param id 主键ID
     * @@describe: 删除店铺营业时间设置
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/19 15:17
     */
    @ApiOperation(value = "删除店铺营业时间")
    @ApiImplicitParam(name = "id", value = "营业设置主键ID", required = true, dataType = "Long")
    @RequestMapping(value = "/deleteBusinessHours", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResultCode deleteBusinessHours(@RequestParam(value = "id", required = true) Long id) {
        return storeSetTimeService.deleteBusinessHours(id);
    }

    /**
     * @param id 主键ID
     * @@describe: 修改店铺营业时间设置
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/19 15:17
     */
    @ApiOperation(value = "修改店铺营业时间")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "营业设置主键ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "beginTime", value = "开始时间", required = true, dataType = "Date"), @ApiImplicitParam(name = "endTime", value = "结束时间", required = true, dataType = "Date")
    })

    @RequestMapping(value = "/updateBusinessHours", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResultCode updateBusinessHours(@RequestParam(value = "id", required = true) Long id,
                                          @RequestParam(value = "beginTime", required = false) String beginTime,
                                          @RequestParam(value = "endTime", required = false) String endTime) {
        return storeSetTimeService.updateBusinessHours(id, beginTime, endTime);
    }

    /**
     * @param storeId 店铺ID
     * @@describe: 查询店铺营业时间设置列表
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/19 15:17
     */
    @ApiOperation(value = "查询店铺营业时间设置列表")
    @ApiImplicitParam(name = "storeId", value = "店铺ID 1524262559", required = true, dataType = "Long")
    @RequestMapping(value = "/getCsStoreBusinessHoursList", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResultCode<List<CsStoreBusinessHours>> getCsStoreBusinessHoursList(@RequestParam(value = "storeId", required = true) Long storeId) {
        return storeSetTimeService.getCsStoreBusinessHoursList(storeId);
    }


    /**
     * @param storeId         店铺ID
     * @param managementState 云店经营状态 1:营业中 2:打烊 3:歇业 4:封停/冻结
     * @@describe: 修改店铺营业状态
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/19 15:17
     */
    @ApiOperation(value = "修改店铺营业状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "storeId", value = "店铺ID 1524262559", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "managementState", value = "云店经营状态 1:营业中 2:打烊 3:歇业 4:封停/冻结", required = true, dataType = "int")
    })

    @RequestMapping(value = "/updateStoreStatus", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResultCode updateStoreStatus(@RequestParam(value = "storeId", required = true) Long storeId,
                                        @RequestParam(value = "managementState", required = true) Integer managementState) {
        return storeSetTimeService.updateStoreStatus(storeId, managementState);
    }


    /**
     * @param storeId   店铺ID
     * @param beginDate 假期开始时间
     * @param endDate   假期结束时间
     * @@describe: 添加假期区间设置
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/20 9:53
     */
    @ApiOperation(value = "添加假期区间设置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "storeId", value = "店铺ID 1524262559", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "beginDate", value = "假期开始时间", required = true, dataType = "Date"),
            @ApiImplicitParam(name = "endDate", value = "假期结束时间", required = true, dataType = "Date")
    })

    @RequestMapping(value = "/insertStoreHolidaySettings", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResultCode insertStoreHolidaySettings(@RequestParam(value = "storeId", required = true) Long storeId,
                                                 @RequestParam(value = "beginDate", required = true) String beginDate,
                                                 @RequestParam(value = "endDate", required = true) String endDate)throws ParseException {
        return storeSetTimeService.insertStoreHolidaySettings(storeId,beginDate, endDate);
    }


    /**
     * @param id 假期设置主键ID
     * @@describe: 删除假期区间设置
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/20 9:53
     */
    @ApiOperation(value = "删除假期区间设置")
    @ApiImplicitParam(name = "id", value = "假期设置主键ID", required = true, dataType = "Long")
    @RequestMapping(value = "/deleteStoreHolidaySettings", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResultCode deleteStoreHolidaySettings(@RequestParam(value = "id", required = true) Long id){
        return storeSetTimeService.deleteStoreHolidaySettings(id);
    }


    /**
     * @param storeId 店铺ID
     * @@describe: 查询店铺假期时间设置列表
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/19 15:17
     */
    @ApiOperation(value = "查询店铺假期时间设置列表")
    @ApiImplicitParam(name = "storeId", value = "店铺ID 1524262559", required = true, dataType = "Long")
    @RequestMapping(value = "/getCsStoreHolidaySettingsList", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public  ResultCode<List<CsStoreHolidaySettings>> getCsStoreHolidaySettingsList(@RequestParam(value = "storeId", required = true) Long storeId){
        return storeSetTimeService.getCsStoreHolidaySettingsList(storeId);
    }
    /**
     * @param id 主键ID
     * @param onOff 开关(1.开  0.关)
     * @@describe: 开启或者关闭某条假期设置
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/20 13:47
     */
    @ApiOperation(value = "开启或者关闭某条假期设置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "onOff", value = "开关(1.开  0.关)", required = true, dataType = "int")
    })

    @RequestMapping(value = "/holidaySettingsonOff", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResultCode holidaySettingsonOff(@RequestParam(value = "id", required = true) Long id,
                                           @RequestParam(value = "onOff", required = true) Integer onOff){
        return storeSetTimeService.holidaySettingsonOff(id,onOff);
    }
    /**
     * @param id 主键ID
     * @param onOff 开关(1.开  0.关)
     * @@describe: 开启或者关闭某营业时间设置
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/20 13:47
     */
    @ApiOperation(value = "开启或者关闭某营业时间设置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "onOff", value = "开关(1.开  0.关)", required = true, dataType = "int")
    })

    @RequestMapping(value = "/businessHourssonOff", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResultCode businessHourssonOff(@RequestParam(value = "id", required = true) Long id,
                                          @RequestParam(value = "onOff", required = true) Integer onOff)  {
        return storeSetTimeService.businessHourssonOff(id,onOff);
    }


}
