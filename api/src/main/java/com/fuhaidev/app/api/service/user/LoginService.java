package com.fuhaidev.app.api.service.user;

import com.fuhaidev.app.api.common.DataResponse;
import com.fuhaidev.app.api.common.DataResult;

import java.util.Map;

/**
 * Created by xumingxun on 2018/5/3.
 */
public interface LoginService {
    DataResult<DataResponse> loginByUser(Integer id);
}
