//package com.gbqd.service.goods.scheduledTasks;
//
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//
//import org.quartz.CronScheduleBuilder;
//import org.quartz.CronTrigger;
//import org.quartz.JobBuilder;
//import org.quartz.JobDetail;
//import org.quartz.JobKey;
//import org.quartz.Scheduler;
//import org.quartz.SchedulerException;
//import org.quartz.TriggerBuilder;
//import org.quartz.TriggerKey;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//
///**
// * 任务调度处理
// *
// * @author yvan
// */
///*@Configuration*/
//public class QuartzManager {
//    // 任务调度
//    @Autowired
//    private Scheduler scheduler;
//
//    /**
//     * 开始执行所有任务
//     *
//     * @param jobNameBegin 任务名
//     * @param jobGroupNameBegin 组名
//     * @param  timeBegin 时间 转换成cron表达式
//
//     * @throws SchedulerException
//     */
//    public void startJob(String timeBegin, String jobNameBegin, String jobGroupNameBegin, String timeEnd, String jobNameEnd, String jobGroupNameEnd, Long storeId,String weeks) throws SchedulerException {
//        System.out.println(fmtDateToStr(new Date(), getCronTime(timeBegin,weeks)));
//        startJob1(scheduler, jobNameBegin, fmtDateToStr(new Date(), getCronTime(timeBegin,weeks)), jobGroupNameBegin, storeId);
//        startJob2(scheduler, jobNameEnd, fmtDateToStr(new Date(), getCronTime(timeEnd,weeks)), jobGroupNameEnd, storeId);
//        scheduler.start();
//    }
//
//    /**
//     * 获取Job信息
//     *
//     * @param name
//     * @param group
//     * @return
//     * @throws SchedulerException
//     */
//    public String getJobInfo(String name, String group) throws SchedulerException {
//        TriggerKey triggerKey = new TriggerKey(name, group);
//        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
//        return String.format("time:%s,state:%s", cronTrigger.getCronExpression(),
//                scheduler.getTriggerState(triggerKey).name());
//    }
//
//    /**
//     * 修改某个任务的执行时间
//     *
//     * @param name
//     * @param group
//     * @param time
//     * @return
//     * @throws SchedulerException
//     */
//    public boolean modifyJob(String name, String group, String time) throws SchedulerException {
//        Date date = null;
//        TriggerKey triggerKey = new TriggerKey(name, group);
//        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
//        String oldTime = cronTrigger.getCronExpression();
//        if (!oldTime.equalsIgnoreCase(time)) {
//            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(time);
//            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group).withSchedule(cronScheduleBuilder).build();
//            date = scheduler.rescheduleJob(triggerKey, trigger);
//        }
//        return date != null;
//    }
//
//    /**
//     * 暂停所有任务
//     *
//     * @throws SchedulerException
//     */
//    public void pauseAllJob() throws SchedulerException {
//        scheduler.pauseAll();
//    }
//
//    /**
//     * 暂停某个任务
//     *
//     * @param name
//     * @param group
//     * @throws SchedulerException
//     */
//    public void pauseJob(String name, String group) throws SchedulerException {
//        JobKey jobKey = new JobKey(name, group);
//        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
//        if (jobDetail == null) return;
//        scheduler.pauseJob(jobKey);
//    }
//
//    /**
//     * 恢复所有任务
//     *
//     * @throws SchedulerException
//     */
//    public void resumeAllJob() throws SchedulerException {
//        scheduler.resumeAll();
//    }
//
//    /**
//     * 恢复某个任务
//     *
//     * @param name
//     * @param group
//     * @throws SchedulerException
//     */
//    public void resumeJob(String name, String group) throws SchedulerException {
//        JobKey jobKey = new JobKey(name, group);
//        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
//        if (jobDetail == null) return;
//        scheduler.resumeJob(jobKey);
//    }
//
//    /**
//     * 删除某个任务
//     *
//     * @param name
//     * @param group
//     * @throws SchedulerException
//     */
//    public void deleteJob(String name, String group) throws SchedulerException {
//        JobKey jobKey = new JobKey(name, group);
//        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
//        if (jobDetail == null) return;
//        scheduler.deleteJob(jobKey);
//    }
//
//    /**
//     * @param scheduler    调度器
//     * @param jobName      任务名
//     * @param time         时间
//     * @param jobGroupName 组名
//     * @throws SchedulerException
//     */
//    private void startJob1(Scheduler scheduler, String jobName, String time, String jobGroupName, Long storeId) throws SchedulerException {
//        // 通过JobBuilder构建JobDetail实例，JobDetail规定只能是实现Job接口的实例
//        // JobDetail 是具体Job实例
//        JobDetail jobDetail = JobBuilder.newJob(SchedulerQuartzJob1.class).withIdentity(jobName, jobGroupName).build();
//        jobDetail.getJobDataMap().put("storeId", storeId);
//        // 基于表达式构建触发器
//        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(time);
//        // CronTrigger表达式触发器 继承于Trigger
//        // TriggerBuilder 用于构建触发器实例
//        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName).withSchedule(cronScheduleBuilder).build();
//        scheduler.scheduleJob(jobDetail, cronTrigger);
//    }
//
//    /**
//     * @param scheduler    调度器
//     * @param jobName      任务名
//     * @param time         时间
//     * @param jobGroupName 组名
//     * @throws SchedulerException
//     */
//    private void startJob2(Scheduler scheduler, String jobName, String time, String jobGroupName, Long storeId) throws SchedulerException {
//        // 通过JobBuilder构建JobDetail实例，JobDetail规定只能是实现Job接口的实例
//        // JobDetail 是具体Job实例
//        JobDetail jobDetail = JobBuilder.newJob(SchedulerQuartzJob2.class).withIdentity(jobName, jobGroupName).build();
//        jobDetail.getJobDataMap().put("storeId", storeId);
//        // 基于表达式构建触发器
//        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(time);
//        // CronTrigger表达式触发器 继承于Trigger
//        // TriggerBuilder 用于构建触发器实例
//        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName).withSchedule(cronScheduleBuilder).build();
//        scheduler.scheduleJob(jobDetail, cronTrigger);
//    }
//
//
//    /**
//     * Description:格式化日期,String字符串转化为Date
//     *
//     * @param date
//     * @param dtFormat 例如:yyyy-MM-dd HH:mm:ss yyyyMMdd
//     * @return
//     */
//    public static String fmtDateToStr(Date date, String dtFormat) {
//        if (date == null) return "";
//        try {
//            SimpleDateFormat dateFormat = new SimpleDateFormat(dtFormat);
//            return dateFormat.format(date);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "";
//        }
//    }
//
//    /**
//     * 时间转换成cron公式
//     *
//     * @param time     時間
//     * @return
//     */
//    public String getCronTime(String time,String weeks) {
//        String[] a = time.split(":");
//        String minute = a[1];
//        String hours = a[0];
//        String dateFormat = "";
//
//            //如果重复只算 时分
//            dateFormat = "0 " + minute + " " + hours + " ? * "+weeks+"";
//         /*else if (orRepeat == 0) {
//            //如果不重复 添加当天日期进入cron公式
//            Calendar date = Calendar.getInstance();
//             String year = String.valueOf(date.get(Calendar.YEAR));
//            String month = String.valueOf(date.get(Calendar.MONTH) + 1);
//            String dates = String.valueOf(date.get(Calendar.DATE));
//            dateFormat = "0 " + minute + " " + hours + " ? " + month + " "+weeks+" "+year+"";
//        }*/
//        return dateFormat;
//    }
//}
//
