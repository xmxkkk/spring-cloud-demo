package com.fuhaidev.app.shop.third.user;

import com.fuhaidev.app.api.common.DataResponse;
import com.fuhaidev.app.api.common.DataResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by xumingxun on 2018/5/14.
 */
@FeignClient(name="user")
@RequestMapping("/login")
public interface LoginFeign {
    @RequestMapping(value = "/loginByUser/{id}",method = RequestMethod.GET)
    DataResult<DataResponse> loginByUser(@PathVariable("id") Integer id);
}
