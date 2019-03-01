//package com.gbqd.service.goods.scheduledTasks;
//
//import com.gbqd.mapper.CsStoreHolidaySettingsMapper;
//import com.gbqd.mapper.CsStoreMapper;
//import com.gbqd.pojo.goods.CsStore;
//import com.gbqd.pojo.goods.CsStoreHolidaySettings;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.net.Inet4Address;
//import java.net.InetAddress;
//import java.net.NetworkInterface;
//import java.net.SocketException;
//import java.util.Date;
//import java.util.Enumeration;
//import java.util.List;
//
///**
// * @author MrWen
// * @create 2019-02-20-15:30
// */
//@Component
//public class ScheduledTasks {
//    @Autowired
//    CsStoreHolidaySettingsMapper csStoreHolidaySettingsMapper;
//    @Autowired
//    CsStoreMapper csStoreMapper;
//    @Value("${webIp}")
//
//    String webIp;
//
//    /**
//     * 每天凌晨定时更新假期设置
//     */
//  //   @Scheduled(cron = "0 25 14 * * ?")
//     @Scheduled(cron = "0 0 0 * * ?")
//     // @Scheduled(cron = "0 */1 * * * ?")
//    public void taskHolidaySettingsonOff() throws SocketException{
//        if(HostQuartz()){
//            //查询出所有开关是开着的设置并且是在区间段内的(当前日期大于起始小于终止)
//            List<CsStoreHolidaySettings> listonoff = csStoreHolidaySettingsMapper.getOnOffList(1);
//            Date date = new Date();
//            if (listonoff.size() > 0) {
//                //如果查询出的集合长度大于0 证明数据库里有需要更新的假期设置
//                for (int i = 0; i < listonoff.size(); i++) {
//                    CsStore csStore = csStoreMapper.selectByPrimaryKey(listonoff.get(i).getStoreId());//通过店铺ID 查询店铺对象
//                    if (csStore != null) {
//                        //更新状态
//                        csStore.setManagementState(3);
//                        csStoreMapper.updateByPrimaryKeySelective(csStore);
//                        System.out.println("更新了数据");
//                    }
//                }
//            }
//        }
//
//    }
//
//
//    public boolean HostQuartz() throws SocketException {
//        boolean bb = false;
//        String hostQuartzIp = webIp;
//        if (StringUtils.isEmpty(hostQuartzIp)) {
//            System.out.println("web_ip未配置");
//            return bb;
//        }
//        Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
//        InetAddress ip = null;
//        while (allNetInterfaces.hasMoreElements()) {
//            NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
//            Enumeration addresses = netInterface.getInetAddresses();
//            while (addresses.hasMoreElements()) {
//                ip = (InetAddress) addresses.nextElement();
//                if (ip != null && ip instanceof Inet4Address) {
//                    System.out.println(ip.getHostAddress());
//                    if (hostQuartzIp.equals(ip.getHostAddress())) {
//                        bb = true;
//                        break;
//                    }
//                }
//            }
//        }
//        return bb;
//    }
//
//}
