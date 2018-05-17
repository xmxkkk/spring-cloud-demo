package com.fuhaidev.app.dao.user.impl;

import com.fuhaidev.app.api.common.DataResponse;
import com.fuhaidev.app.api.common.DataResult;
import com.fuhaidev.app.api.service.user.LoginService;
import com.fuhaidev.app.biz_common.annotation.Master;
import com.fuhaidev.app.biz_common.annotation.Slave;
import com.fuhaidev.app.biz_common.service.AbstractServiceImpl;
import com.fuhaidev.app.dao.entity.test1.UserEntity;
import com.fuhaidev.app.dao.entity.test2.ItemEntity;
import com.fuhaidev.app.dao.mapper.test1.UserEntityMapper;
import com.fuhaidev.app.dao.mapper.test2.ArticleEntityMapper;
import com.fuhaidev.app.dao.mapper.test2.ItemEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xumingxun on 2018/5/3.
 */
@Service
public class LoginServiceImpl extends AbstractServiceImpl implements LoginService {

    @Autowired
    UserEntityMapper userEntityMapper;

    @Autowired
    ArticleEntityMapper articleEntityMapper;

    @Autowired
    ItemEntityMapper itemEntityMapper;

    @Value("${shop_url}")
    String baseUrl;

    @Master
//    @Transactional(rollbackFor = Throwable.class,transactionManager = "test1MasterTransactionManager")
    public DataResult<DataResponse> loginByUser(Integer id) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DataResult<DataResponse> result=new DataResult<>(new DataResponse());
        UserEntity user=userEntityMapper.selectByPrimaryKey(id);

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("name",user.getName());
        map.put("age",user.getAge());

        UserEntity upd=new UserEntity();
        upd.setId(id);
        upd.setName("ojbk");

        userEntityMapper.updateByPrimaryKeySelective(upd);

        map.put("baseUrl",baseUrl);

        ItemEntity ins=new ItemEntity();
        ins.setItemId(1);
        ins.setCreateTime(new Date());
        ins.setUpdateTime(new Date());
        itemEntityMapper.insertUseGeneratedKeys(ins);

        map.put("itemId",ins.getId());

        logger.info(jsonUtil.toJson(result));

        result.getData().setData(map);
        return result;
    }
}
