package com.fuhaidev.app.biz_common.aspect.impl;

import com.fuhaidev.app.api.common.DataResponse;
import com.fuhaidev.app.api.common.DataResult;
import com.fuhaidev.app.api.common.ErrorResult;
import com.fuhaidev.app.biz_common.aspect.ServiceFilter;
import com.fuhaidev.app.biz_common.util.validate.ParamValidateUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xumingxun on 2018/5/14.
 */
@Component
public class ParamValidateServiceFilter extends AbstractServiceFilter {

    @Autowired
    @Qualifier("signServiceFilter")
    ServiceFilter serviceFilter;

    @Override
    public Object proceed(ProceedingJoinPoint pjp) throws Throwable {
        long start=System.currentTimeMillis();
        Object[] args=pjp.getArgs();

        List<String> checkErrors = new ArrayList<String>();
        for (Object arg : args) {
            if (arg != null) {
                Map<String, ArrayList<String>> validateResult = ParamValidateUtils.validator(arg);
                if (validateResult != null) {
                    for (Map.Entry<String, ArrayList<String>> entry : validateResult.entrySet()) {
                        checkErrors.add(String.format("参数:%s校验失败,原因:%s", entry.getKey(), entry.getValue()));
                    }
                }
            }
        }

        if(!CollectionUtils.isEmpty(checkErrors)){
            //返回参数错误信息
//            MethodSignature signature = (MethodSignature) pjp.getSignature();
//            Method method = signature.getMethod();
//            method.getReturnType();
            DataResult<DataResponse> dt = new DataResult<DataResponse>(new DataResponse());
            StringBuilder sb = new StringBuilder();
            for (String s : checkErrors) {
                sb.append(s + " ");
            }
            dt.getData().setErr(new ErrorResult("-2",sb.toString().trim(),null));
            dt.getData().setData("param not valid");
            dt.setElapsedMilliseconds(System.currentTimeMillis() - start);
            return dt;
        }

        return serviceFilter.proceed(pjp);
    }
}
