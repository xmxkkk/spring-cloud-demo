package com.fuhaidev.app.biz_common.aspect.impl;

import com.fuhaidev.app.biz_common.aspect.ServiceFilter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

/**
 * Created by xumingxun on 2018/5/14.
 */
@Component
public class LogServiceFilter extends AbstractServiceFilter{

    ServiceFilter serviceFilter;

    public Object proceed(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("LogServiceFilterImpl");
        Object[] args=pjp.getArgs();
        for(Object arg:args){

        }

        Object result=pjp.proceed(args);

        return result;
    }
}
