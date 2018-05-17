package com.fuhaidev.app.biz_common.util.json.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fuhaidev.app.biz_common.util.json.JsonUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by xumingxun on 2018/5/14.
 */
@Component
public class FastJsonUtil implements JsonUtil {
/*
    private static class Item{
        int item;

        public int getItem() {
            return item;
        }

        public void setItem(int item) {
            this.item = item;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "item=" + item +
                    '}';
        }
    }
    public static void main(String[] args) {
        FastJsonUtil fastJsonUtil=new FastJsonUtil();
        String json="{\"item\":1}";
        Object abc=fastJsonUtil.fromJson(json);
        System.out.println(abc);
        String arr="[{\"item\":1},{\"item\":2}]";
        abc=fastJsonUtil.fromJson(arr);
        System.out.println(abc);

        JSONObject ok=fastJsonUtil.fromJson(json,JSONObject.class);
        System.out.println(ok);

        Map<String,Object> ok2=fastJsonUtil.fromJson(json,Map.class);
        System.out.println(ok2);

        List<Map<String,Object>> ok3=fastJsonUtil.fromJson(arr,new TypeReference<List<Map<String,Object>>>(){});
        System.out.println(ok3);

        List<Item> items=fastJsonUtil.fromJson(arr,new TypeReference<List<Item>>(){});
        System.out.println(items);
    }*/
    @Override
    public String toJson(Object object) {
        if(object==null)return null;
        return JSONObject.toJSONString(object);
    }

    @Override
    public Object fromJson(String json) {
        return JSONObject.parse(json);
    }

    @Override
    public <T> T fromJson(String json, TypeReference<T> type) {
        return JSON.parseObject(json,type);
    }

    @Override
    public <T> T fromJson(String json, Class<T> cls) {
        return JSON.parseObject(json,cls);
    }

}
