package com.fuhaidev.app.shop.controller;

import com.fuhaidev.app.api.common.DataResponse;
import com.fuhaidev.app.api.common.DataResult;
import com.fuhaidev.app.api.dto.item.BuyRequest;
import com.fuhaidev.app.api.service.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by xumingxun on 2018/5/12.
 */
@RestController
@RequestMapping("/item")
@ResponseBody
public class ItemController {

    @Autowired
    ItemService itemService;

    @RequestMapping("buy")
    public DataResult<DataResponse> buy(@RequestBody BuyRequest request){
        return itemService.buy(request);
    }
}
