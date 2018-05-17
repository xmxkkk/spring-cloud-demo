package com.fuhaidev.app.biz_common.util.json;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

/**
 * Created by xumingxun on 2018/5/14.
 */
public interface JsonUtil {
    String toJson(Object object);
    Object fromJson(String json);
    <T> T fromJson(String json, TypeReference<T> type);
    <T> T fromJson(String json,Class<T> cls);
}
