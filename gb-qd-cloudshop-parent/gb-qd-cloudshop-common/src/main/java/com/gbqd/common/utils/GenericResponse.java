package com.gbqd.common.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author MrWen
 * @create 2019-02-01-9:53
 */
@ApiModel(value="返回类")
public class GenericResponse<T>  {

    /**
     * 程序定义状态码
     */
    @ApiModelProperty(value = "成功或者错误码")
    private int code;
    /**
     * 必要的提示信息
     */
    @ApiModelProperty(value = "描述")
    private String message;
    /**
     * 业务数据
     */
    @ApiModelProperty(value = "数据")
    private T datas;



    @Override
    public String toString() {
        return "GenericResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", datas=" + datas +
                '}';
    }



    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getDatas() {
        return datas;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDatas(T datas) {
        this.datas = datas;
    }
}
