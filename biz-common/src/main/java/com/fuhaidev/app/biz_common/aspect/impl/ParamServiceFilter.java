package com.fuhaidev.app.biz_common.aspect.impl;

import com.fuhaidev.app.biz_common.aspect.ServiceFilter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by xumingxun on 2018/5/14.
 */
@Component
public class ParamServiceFilter extends AbstractServiceFilter{

    @Autowired
    @Qualifier("masterSlaveServiceFilter")
    ServiceFilter serviceFilter;

    @Override
    public Object proceed(ProceedingJoinPoint pjp) throws Throwable {
        

        return serviceFilter.proceed(pjp);
    }
}
