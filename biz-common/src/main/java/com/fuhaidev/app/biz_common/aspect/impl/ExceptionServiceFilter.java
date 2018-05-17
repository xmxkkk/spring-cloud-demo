package com.fuhaidev.app.biz_common.aspect.impl;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

/**
 * Created by xumingxun on 2018/5/14.
 */
@Component
public class ExceptionServiceFilter extends AbstractServiceFilter {

    @Override
    public Object proceed(ProceedingJoinPoint pjp) throws Throwable {

        return null;
    }
}
