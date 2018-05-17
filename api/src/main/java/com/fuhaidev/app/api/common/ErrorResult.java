package com.fuhaidev.app.api.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ErrorResult {
    String errCode;
    String errMsg;
    String exception;
    public ErrorResult(){
        this("0","成功",null);
    }
    public ErrorResult(String errCode,String errMsg){
        this(errCode,errMsg,null);
    }
    public ErrorResult(String errCode,String errMsg,String exception){
        this.errCode=errCode;
        this.errMsg=errMsg;
        this.exception=exception;
    }
}
