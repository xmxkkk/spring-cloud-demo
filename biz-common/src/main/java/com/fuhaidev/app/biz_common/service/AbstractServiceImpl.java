package com.fuhaidev.app.biz_common.service;

import com.fuhaidev.app.biz_common.util.json.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by xumingxun on 2018/5/14.
 */
public class AbstractServiceImpl implements AbstractService{
    protected Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected JsonUtil jsonUtil;
}
