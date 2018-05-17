package com.fuhaidev.app.shop.impl;

import com.fuhaidev.app.api.common.DataResponse;
import com.fuhaidev.app.api.common.DataResult;
import com.fuhaidev.app.api.dto.item.BuyRequest;
import com.fuhaidev.app.api.service.item.ItemService;
import com.fuhaidev.app.biz_common.annotation.Slave;
import com.fuhaidev.app.biz_common.service.AbstractServiceImpl;
import com.fuhaidev.app.dao.entity.test1.UserEntity;
import com.fuhaidev.app.dao.entity.test2.ArticleEntity;
import com.fuhaidev.app.dao.mapper.test1.UserEntityMapper;
import com.fuhaidev.app.dao.mapper.test2.ArticleEntityMapper;
import com.fuhaidev.app.shop.third.user.LoginFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xumingxun on 2018/5/12.
 */
@Service
public class ItemServiceImpl extends AbstractServiceImpl implements ItemService{

    @Autowired
    UserEntityMapper userEntityMapper;

    @Autowired
    ArticleEntityMapper articleEntityMapper;

    @Autowired
    LoginFeign loginFeign;

    @Override
    @Slave
//    @Transactional(rollbackFor = Throwable.class,transactionManager = "test2MasterTransactionManager")
    public DataResult<DataResponse> buy(BuyRequest request) {
        DataResult<DataResponse> result=new DataResult<>(new DataResponse());

        List<ArticleEntity> articles=articleEntityMapper.selectAll();

        Map<String,Object> response=new HashMap<String,Object>();
        response.put("itemId",request.getItemId());
        response.put("articles",articles);

        DataResult<DataResponse>  user2Map=loginFeign.loginByUser(1);

        response.put("user2",user2Map);

        List<UserEntity> userMap=userEntityMapper.selectAll();


        result.getData().setData(userMap);

        return result;
    }
}
