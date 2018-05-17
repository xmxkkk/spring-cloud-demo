package com.fuhaidev.app.biz_common.aspect.impl;

import com.fuhaidev.app.api.common.*;
import com.fuhaidev.app.biz_common.aspect.ServiceFilter;
import com.fuhaidev.app.biz_common.util.sign.SignUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by xumingxun on 2018/5/14.
 */
@Component
public class SignServiceFilter extends AbstractServiceFilter {

    @Autowired
    @Qualifier("paramServiceFilter")
    ServiceFilter serviceFilter;

    @Autowired
    SignUtil signUtil;

    @Override
    public Object proceed(ProceedingJoinPoint pjp) throws Throwable {
        long start=System.currentTimeMillis();
        Request request=null;
        Object args[]=pjp.getArgs();
        for(Object arg:args){
            if(arg instanceof Request){
                request=(Request)arg;
                break;
            }
        }

        if(request!=null){
            if(!signUtil.checkSign(request)){
                DataResult<DataResponse> dt = new DataResult<DataResponse>(new DataResponse());
                dt.getData().setErr(new ErrorResult("-3","签名无效",null));
                dt.getData().setData("sign not valid");
                dt.setElapsedMilliseconds(System.currentTimeMillis() - start);
                return dt;
            }
        }

        return serviceFilter.proceed(pjp);
    }
}
