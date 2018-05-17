package com.fuhaidev.app.dao.user.controller;

import com.fuhaidev.app.api.common.DataResponse;
import com.fuhaidev.app.api.common.DataResult;
import com.fuhaidev.app.api.service.user.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by xumingxun on 2018/5/3.
 */
@RestController
@RequestMapping("/login")
@ResponseBody
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/loginByUser/{id}",method = RequestMethod.GET)
    public DataResult<DataResponse> loginByUser(@PathVariable Integer id){
        return loginService.loginByUser(id);
    }
}
