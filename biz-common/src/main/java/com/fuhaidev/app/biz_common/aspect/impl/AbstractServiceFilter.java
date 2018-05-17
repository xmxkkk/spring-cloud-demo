package com.fuhaidev.app.biz_common.aspect.impl;

import com.fuhaidev.app.biz_common.aspect.ServiceFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xumingxun on 2018/5/14.
 */
public abstract class AbstractServiceFilter implements ServiceFilter{
    protected Logger logger= LoggerFactory.getLogger(getClass());
}
