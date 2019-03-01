package com.gbqd.service.goods.service.impl;

import com.gbqd.common.utils.RandomCodeUtil;
import com.gbqd.common.utils.ResultCode;
import com.gbqd.common.utils.enums.ResultCodeStatus;
import com.gbqd.mapper.CsStoreBusinessHoursMapper;
import com.gbqd.mapper.CsStoreHolidaySettingsMapper;
import com.gbqd.mapper.CsStoreMapper;
import com.gbqd.pojo.goods.CsStore;
import com.gbqd.pojo.goods.CsStoreBusinessHours;
import com.gbqd.pojo.goods.CsStoreHolidaySettings;
import com.gbqd.service.goods.StoreSetTimeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author MrWen
 * @create 2019-02-19-14:40
 */
@RestController
public class StoreSetTimeServiceImpl implements StoreSetTimeService {
    @Autowired
    CsStoreBusinessHoursMapper CsStoreBusinessHoursMapper;
    @Autowired
    CsStoreMapper csStoreMapper;
    @Autowired
    CsStoreHolidaySettingsMapper csStoreHolidaySettingsMapper;
 /*   @Autowired
    private QuartzManager quartzScheduler;*/

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
    public ResultCode insertBusinessHours(@RequestParam(value = "storeId", required = true) Long storeId,
                                          @RequestParam(value = "beginTime", required = true) String beginTime,
                                          @RequestParam(value = "endTime", required = true) String endTime,
                                          @RequestParam(value = "dateOf", required = true) String dateOf) {
        ResultCode rc = new ResultCode();
        CsStoreBusinessHours csStoreBusinessHours = new CsStoreBusinessHours();
        csStoreBusinessHours.setId(RandomCodeUtil.getId());
        csStoreBusinessHours.setStoreId(storeId);
        csStoreBusinessHours.setBeginTime(beginTime);
        csStoreBusinessHours.setEndTime(endTime);
        csStoreBusinessHours.setDateOf(dateOf);
        csStoreBusinessHours.setOnOff(0);
        CsStoreBusinessHoursMapper.insertSelective(csStoreBusinessHours);
        rc.setStatus(ResultCodeStatus.SUCCESS);
        rc.setMsg("店铺营业时间设置成功");
        return rc;
    }

    /**
     * @param id 主键ID
     * @@describe: 删除店铺营业时间设置
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/19 15:17
     */
    public ResultCode deleteBusinessHours(@RequestParam(value = "id", required = true) Long id) {
        ResultCode rc = new ResultCode();
        CsStoreBusinessHoursMapper.deleteByPrimaryKey(id);
        rc.setStatus(ResultCodeStatus.SUCCESS);
        rc.setMsg("删除成功");
        return rc;
    }


    /**
     * @param id 主键ID
     * @@describe: 修改店铺营业时间设置
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/19 15:17
     */
    public ResultCode updateBusinessHours(@RequestParam(value = "id", required = true) Long id,
                                          @RequestParam(value = "beginTime", required = false) String beginTime,
                                          @RequestParam(value = "endTime", required = false) String endTime) {
        ResultCode rc = new ResultCode();
        CsStoreBusinessHours csStoreBusinessHours = CsStoreBusinessHoursMapper.selectByPrimaryKey(id);
        if (csStoreBusinessHours != null) {
            csStoreBusinessHours.setBeginTime(beginTime);
            csStoreBusinessHours.setEndTime(endTime);
            CsStoreBusinessHoursMapper.updateByPrimaryKeySelective(csStoreBusinessHours);
            rc.setStatus(ResultCodeStatus.SUCCESS);
            rc.setMsg("修改成功");
        } else {
            rc.setStatus(ResultCodeStatus.FAIL);
            rc.setMsg("修改失败,没有该设置");
        }
        return rc;
    }

    /**
     * @param storeId 店铺ID
     * @@describe: 查询店铺营业时间设置列表
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/19 15:17
     */
    public ResultCode<List<CsStoreBusinessHours>> getCsStoreBusinessHoursList(@RequestParam(value = "storeId", required = true) Long storeId) {
        ResultCode<List<CsStoreBusinessHours>> rc = new ResultCode<List<CsStoreBusinessHours>>();
        rc.setStatus(ResultCodeStatus.SUCCESS);
        rc.setMsg("查询成功");
        rc.setContent(CsStoreBusinessHoursMapper.getStoreList(storeId));
        return rc;
    }

    /**
     * @param storeId         店铺ID
     * @param managementState 云店经营状态 1:营业中 2:打烊 3:歇业 4:封停/冻结
     * @@describe: 修改店铺营业状态
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/19 15:17
     */
    public ResultCode updateStoreStatus(@RequestParam(value = "storeId", required = true) Long storeId,
                                        @RequestParam(value = "managementState", required = true) Integer managementState) {
        ResultCode rc = new ResultCode();
        CsStore csStore = csStoreMapper.selectByPrimaryKey(storeId);
        if (csStore == null) {
            rc.setStatus(ResultCodeStatus.FAIL);
            rc.setMsg("店铺ID错误,店铺不存在");
            return rc;
        }
        csStore.setManagementState(managementState);
        csStoreMapper.updateByPrimaryKeySelective(csStore);
        rc.setStatus(ResultCodeStatus.SUCCESS);
        rc.setMsg("修改成功");
        return rc;
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
    public ResultCode insertStoreHolidaySettings(@RequestParam(value = "storeId", required = true) Long storeId,
                                                 @RequestParam(value = "beginDate", required = true) String beginDate,
                                                 @RequestParam(value = "endDate", required = true) String endDate) throws ParseException {
        ResultCode rc = new ResultCode();
        CsStoreHolidaySettings csStoreHolidaySettings = new CsStoreHolidaySettings();
        csStoreHolidaySettings.setId(RandomCodeUtil.getId());
        csStoreHolidaySettings.setOnOff(0);
        SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
        csStoreHolidaySettings.setBeginDate(sfd.parse(beginDate));
        csStoreHolidaySettings.setEndDate(sfd.parse(endDate));
        csStoreHolidaySettings.setStoreId(storeId);
        csStoreHolidaySettingsMapper.insertSelective(csStoreHolidaySettings);
        rc.setStatus(ResultCodeStatus.SUCCESS);
        rc.setMsg("添加设置成功");
        return rc;
    }

    /**
     * @param id 假期设置主键ID
     * @@describe: 删除假期区间设置
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/20 9:53
     */
    public ResultCode deleteStoreHolidaySettings(@RequestParam(value = "id", required = true) Long id) {
        ResultCode rc = new ResultCode();
        csStoreHolidaySettingsMapper.deleteByPrimaryKey(id);
        rc.setStatus(ResultCodeStatus.SUCCESS);
        rc.setMsg("删除成功");
        return rc;
    }

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
                                                 @RequestParam(value = "endDate", required = false) Date endDate) {
        ResultCode rc = new ResultCode();
        CsStoreHolidaySettings csStoreHolidaySettings = csStoreHolidaySettingsMapper.selectByPrimaryKey(id);
        if (csStoreHolidaySettings == null) {
            rc.setStatus(ResultCodeStatus.FAIL);
            rc.setMsg("id有误该设置不存在");
            return rc;
        }
        csStoreHolidaySettings.setEndDate(endDate);
        csStoreHolidaySettings.setBeginDate(beginDate);
        csStoreHolidaySettingsMapper.updateByPrimaryKeySelective(csStoreHolidaySettings);
        rc.setStatus(ResultCodeStatus.SUCCESS);
        rc.setMsg("修改成功");
        return rc;
    }

    /**
     * @param storeId 店铺ID
     * @@describe: 查询店铺假期时间设置列表
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/19 15:17
     */
    public ResultCode<List<CsStoreHolidaySettings>> getCsStoreHolidaySettingsList(@RequestParam(value = "storeId", required = true) Long storeId) {
        ResultCode<List<CsStoreHolidaySettings>> rc = new ResultCode<List<CsStoreHolidaySettings>>();
        rc.setContent(csStoreHolidaySettingsMapper.getStoreHolidaySettingsList(storeId));
        rc.setStatus(ResultCodeStatus.SUCCESS);
        rc.setMsg("查询成功");
        return rc;
    }

    /**
     * @param id    主键ID
     * @param onOff 开关(1.开  0.关)
     * @@describe: 开启或者关闭某条假期设置
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/20 13:47
     */
    public ResultCode holidaySettingsonOff(@RequestParam(value = "id", required = true) Long id,
                                           @RequestParam(value = "onOff", required = true) Integer onOff) {
        ResultCode rc = new ResultCode();
        CsStoreHolidaySettings csStoreHolidaySettings = csStoreHolidaySettingsMapper.selectByPrimaryKey(id);
        if (csStoreHolidaySettings == null) {
            rc.setStatus(ResultCodeStatus.FAIL);
            rc.setMsg("id有误该设置不存在 ");
            return rc;
        }
        csStoreHolidaySettings.setOnOff(onOff);
        csStoreHolidaySettingsMapper.updateByPrimaryKeySelective(csStoreHolidaySettings);
        rc.setStatus(ResultCodeStatus.SUCCESS);
        rc.setMsg("设置成功");
        return rc;
    }

    /**
     * @param id    主键ID
     * @param onOff 开关(1.开  0.关)
     * @@describe: 开启或者关闭某营业时间设置
     * @author: MrWen
     * @return: com.gbqd.common.utils.ResultCode
     * @date: 2019/2/20 13:47
     */
    @Transactional
    public ResultCode businessHourssonOff(@RequestParam(value = "id", required = true) Long id,
                                          @RequestParam(value = "onOff", required = true) Integer onOff) {
        ResultCode rc = new ResultCode();
        CsStoreBusinessHours csStoreBusinessHours = CsStoreBusinessHoursMapper.selectByPrimaryKey(id);
        if (csStoreBusinessHours == null) {
            rc.setStatus(ResultCodeStatus.FAIL);
            rc.setMsg("id有误该设置不存在");
            return rc;
        }
        csStoreBusinessHours.setOnOff(onOff);
        CsStoreBusinessHoursMapper.updateByPrimaryKeySelective(csStoreBusinessHours);
        rc.setStatus(ResultCodeStatus.SUCCESS);
        rc.setMsg("设置成功");
      /*  if (csStoreBusinessHours.getStoreId() != null) {
            if (onOff == 1) {
                //判断当前时间是否大于初始时间 如果大于 就修改店铺状态改为 1 开业
                SimpleDateFormat sfd = new SimpleDateFormat("HH:mm");
                Date begin = sfd.parse(csStoreBusinessHours.getBeginTime());
                Date end = sfd.parse(csStoreBusinessHours.getEndTime());
                Date n = new Date();
                Date c = sfd.parse(sfd.format(n));
                if (c.getTime()>=begin.getTime()&&c.getTime()<=end.getTime() ) {
                   //修改店铺状态
                    CsStore csStore=csStoreMapper.selectByPrimaryKey(csStoreBusinessHours.getStoreId());
                    csStore.setManagementState(1);
                    csStoreMapper.updateByPrimaryKeySelective(csStore);
                }
                //推一个定时任务
                System.out.println("开始调用");
                quartzScheduler.startJob(csStoreBusinessHours.getBeginTime(), "jobBegin"+csStoreBusinessHours.getId()+"", "groupBegin"+csStoreBusinessHours.getId()+"", csStoreBusinessHours.getEndTime(), "jobEnd"+csStoreBusinessHours.getId()+"", "groupEnd"+csStoreBusinessHours.getId()+"", csStoreBusinessHours.getStoreId(),csStoreBusinessHours.getDateOf());
            } else if (onOff == 0) {
                //取消定时任务
                quartzScheduler.deleteJob("jobBegin"+csStoreBusinessHours.getId()+"", "groupBegin"+csStoreBusinessHours.getId()+"");
                quartzScheduler.deleteJob("jobEnd"+csStoreBusinessHours.getId()+"", "groupEnd"+csStoreBusinessHours.getId()+"");
            }
        }*/
        return rc;
    }

}
