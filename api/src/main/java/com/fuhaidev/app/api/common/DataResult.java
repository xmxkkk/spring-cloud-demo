package com.fuhaidev.app.api.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class DataResult<T extends Response> implements Serializable{
    /**
     * 业务返回数据
     */
    T data;
    /**
     * 系统状态
     */
    boolean success;
    /**
     * 系统错误代码
     */
    String sysErrCode;
    /**
     * 系统错误描述
     */
    String sysErrDesc;
    /**
     * 系统异常信息
     */
    String sysException;

    long elapsedMilliseconds;

    String traceId;

    public DataResult(){

    }
    public DataResult(T data){
        this.data=data;
        this.success=true;
        this.sysErrCode="0";
        this.sysErrDesc="成功";
    }
    public DataResult(String sysErrCode,String sysErrDesc){
        this.sysErrCode=sysErrCode;
        this.sysErrDesc=sysErrDesc;
        this.success=false;
    }
    public DataResult(String sysErrCode,String sysErrDesc,String sysException){
        this.sysErrCode=sysErrCode;
        this.sysErrDesc=sysErrDesc;
        this.sysException=sysException;
        this.success=false;
    }
}
