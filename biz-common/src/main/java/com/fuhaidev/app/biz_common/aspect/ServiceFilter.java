package com.fuhaidev.app.biz_common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by xumingxun on 2018/5/14.
 */
public interface ServiceFilter {
    Object proceed(ProceedingJoinPoint pjp) throws Throwable;
}
