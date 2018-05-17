package com.fuhaidev.app.biz_common.aspect;

import com.fuhaidev.app.biz_common.annotation.Master;
import com.fuhaidev.app.biz_common.annotation.Slave;
import com.fuhaidev.app.biz_common.config.DbContextHolder;
import org.apache.logging.log4j.core.config.Order;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by xumingxun on 2018/5/15.
 */
/**/
//@Aspect
@Component
@Order(1)
public class DataSourceAspect {
    @Pointcut("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void userAccess() {}

    @Before("userAccess()")
    public void doBefore(JoinPoint joinPoint)
    {
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        if(method.isAnnotationPresent(Slave.class)){
            DbContextHolder.setDbType(DbContextHolder.DbType.SLAVE);
        }
    }

    /*
    @Pointcut("execution(com.fuhaidev.app.dao.mapper.test1.*.*(..))")
    public void pointcut() {}

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint)
    {
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        if(method.isAnnotationPresent(Slave.class)){
            DbContextHolder.setDbType(DbContextHolder.DbType.SLAVE);
        }
    }

    @After("pointcut()")
    public void after(JoinPoint point) {
        //清理掉当前设置的数据源，让默认的数据源不受影响
        DbContextHolder.clearDbType();
    }*/
}
