package com.gbqd.service.goods.scheduledTasks;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

///**
// * @author MrWen
// * @create 2019-02-21-10:46
// */
//@Configuration
//public class ApplicationStartQuartzJobListener implements ApplicationListener<ContextRefreshedEvent> {
//    @Autowired
//    private QuartzManager quartzScheduler;
//
//    /**
//     * 初始启动quartz
//     */
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//    /*    try {
//            quartzScheduler.startJob();
//            System.out.println("任务已经启动...");
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }*/
//    }
//
//    /**
//     * 初始注入scheduler
//     *
//     * @return
//     * @throws SchedulerException
//     */
//    @Bean
//    public Scheduler scheduler() throws SchedulerException {
//        SchedulerFactory schedulerFactoryBean = new StdSchedulerFactory();
//        return schedulerFactoryBean.getScheduler();
//    }
//
//}
