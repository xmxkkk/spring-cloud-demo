package com.fuhaidev.app.api.service.item;

import com.fuhaidev.app.api.common.CommonResponse;
import com.fuhaidev.app.api.common.DataResponse;
import com.fuhaidev.app.api.common.DataResult;
import com.fuhaidev.app.api.dto.item.BuyRequest;

import java.util.Map;

/**
 * Created by xumingxun on 2018/5/12.
 */
public interface ItemService {
    DataResult<DataResponse> buy(BuyRequest request);
}
