//package com.gbqd.service.goods.scheduledTasks;
//
//import com.gbqd.mapper.CsStoreMapper;
//import com.gbqd.pojo.goods.CsStore;
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// * 实现Job接口
// * @author yvan
// *
// */
//public class SchedulerQuartzJob1 implements Job {
//    @Autowired
//    CsStoreMapper csStoreMapper;
//
//    @Override
//    public void execute(JobExecutionContext arg0) throws JobExecutionException {
//        Long id=arg0.getJobDetail().getJobDataMap().getLong("storeId");
//        //修改店铺营业状态
//        CsStore csStore =csStoreMapper.selectByPrimaryKey(id);
//        if(csStore!=null){
//            csStore.setManagementState(1);
//            csStoreMapper.updateByPrimaryKeySelective(csStore);
//            System.out.println("已经触发成功");
//        }
//    }
//
//
//}
