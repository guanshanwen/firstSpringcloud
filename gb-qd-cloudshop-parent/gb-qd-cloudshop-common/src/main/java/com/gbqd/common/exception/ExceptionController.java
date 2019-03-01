package com.gbqd.common.exception;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSException;
import com.gbqd.common.utils.ResultCode;
import com.gbqd.common.utils.enums.ResultCodeStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author: yjgithub
 * @create: 2018-05-29
 * @desc: 异常处理
 **/
@ControllerAdvice
@ResponseBody
public class ExceptionController {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus
    public ResultCode exceptionHandler(Exception e) {
        ResultCode rc = new ResultCode();
        rc.setMsg("运行错误数据异常");
        rc.setStatus(ResultCodeStatus.FAIL);
        e.printStackTrace();
        return rc;
    }


    @ExceptionHandler(OSSException.class)
    @ResponseStatus
    public ResultCode exceptionHandlerOSSException(OSSException oe) {
        ResultCode rc = new ResultCode();
        System.out.println("Caught an OSSException, which means your request made it to OSS, "
                + "but was rejected with an error response for some reason.");
        System.out.println("Error Message: " + oe.getMessage());
        System.out.println("Error Code:       " + oe.getErrorCode());
        rc.setMsg(oe.getMessage());
        rc.setStatus(ResultCodeStatus.FAIL);
        rc.setNetStatus(oe.getErrorCode());
        oe.printStackTrace();
        return rc;
    }

    @ExceptionHandler(ClientException.class)
    @ResponseStatus
    public ResultCode exceptionHandlerClientException(ClientException ce) {
        ResultCode rc = new ResultCode();
        System.out.println("Caught an ClientException, which means the client encountered "
                + "a serious internal problem while trying to communicate with OSS, "
                + "such as not being able to access the network.");
        System.out.println("Error Message: " + ce.getMessage());
        rc.setMsg(ce.getMessage());
        rc.setStatus(ResultCodeStatus.FAIL);
        rc.setNetStatus(ce.getErrorCode());
        ce.printStackTrace();
        return rc;
    }
}



