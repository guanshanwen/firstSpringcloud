package com.gbqd.common.utils;

import com.alibaba.fastjson.JSON;
import com.gbqd.common.utils.enums.ResultCodeStatus;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 接口调用返回规范
 * @author MrWen
 * @create 2019-01-28-15:31
 */
public  class  ResultCode<T>{
    /**
     * 获取实例
     * @return
     */
     static public  ResultCode getNewInstance(){
         return new ResultCode ();
     }
    /**
     * 业务请求状态("1","成功") ("0","失败") 只有成功时才能获取content内容
     */
    @ApiModelProperty(value = "业务请求状态(\"1\",\"成功\") (\"0\",\"失败\")")
    private  String status;
    @ApiModelProperty(value = "网络状态,一般用于失败时查看网络状态")
    private  String netStatus;//网络状态,一般用于失败时查看网络状态
    @ApiModelProperty(value = "说明")
    private  String msg;//说明,可用于弹框

    private T content; //自定义业务数据

    public String getStatus() {
        return status;
    }

    public void setStatus(ResultCodeStatus resultCodeStatus) {

        this.status = resultCodeStatus.getCode()+"";
    }

    public String getNetStatus() {
        return netStatus;
    }

    public void setNetStatus(String netStatus) {
        this.netStatus = netStatus;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
    public static void main(String[] args) {
        /**
         * 例子
         */
        Map<String,String> map = new HashMap<String,String>();
        ResultCode<String> rr =new ResultCode<String>();
        ResultCode<Integer> ghh = ResultCode.getNewInstance();
        rr.setStatus(ResultCodeStatus.SUCCESS);

        System.out.println(JSON.toJSON(rr));


        String str ="1548729321657011191";
        System.out.println(new Date().getTime());
       /* 9223372036854775807
        9223372036854775807
        15487293216570011191*/
        Calendar cal= Calendar.getInstance();
        cal.setTimeInMillis(9223372036854L);
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println(sdf.format(cal.getTime()));
    }
}
