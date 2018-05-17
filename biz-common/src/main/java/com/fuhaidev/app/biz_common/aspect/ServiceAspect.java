package com.fuhaidev.app.biz_common.aspect;

import com.fuhaidev.app.biz_common.config.DbContextHolder;
import org.apache.logging.log4j.core.config.Order;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by xumingxun on 2018/5/14.
 */
@Aspect
@Component
@Order(1)
public class ServiceAspect{

    @Autowired
    @Qualifier("paramValidateServiceFilter")
    ServiceFilter serviceFilter;

    @Around("target(com.fuhaidev.app.biz_common.service.AbstractService)")
    public Object proceed(ProceedingJoinPoint pjp) throws Throwable {
        Object result=serviceFilter.proceed(pjp);
        DbContextHolder.clearDbType();
        return result;
    }

}
