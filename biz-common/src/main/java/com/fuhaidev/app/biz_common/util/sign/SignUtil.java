package com.fuhaidev.app.biz_common.util.sign;

import com.fuhaidev.app.api.common.Request;
import com.fuhaidev.app.biz_common.config.AppKeyConfig;
import com.fuhaidev.app.biz_common.util.security.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by xumingxun on 2018/5/14.
 */
@Component
public class SignUtil {
    @Autowired
    AppKeyConfig appKeyConfig;

    public boolean checkSign(Request request){
        Map<String,String> map=new HashMap<String,String>();
        map.put("appId",request.getAppId());
        map.put("language",request.getLanguage());
        map.put("traceId",request.getTraceId());
        map.put("timeZone",request.getTimeZone());
        map.put("timestamp",String.valueOf(request.getTimestamp()));

        List<String> keysList = new ArrayList<String>();
        keysList.addAll(map.keySet());
        Collections.sort(keysList);
        StringBuilder sb = new StringBuilder();
        for (String key : keysList) {
            sb.append(key).append("=").append(map.get(key)).append("&");
        }
        if(!appKeyConfig.getAppKeys().containsKey(request.getAppId())){
            return false;
        }
        sb.append("appKey=").append(appKeyConfig.getAppKeys().get(request.getAppId()));
        String md5= EncryptUtil.encryptMD5(sb.toString());
        return md5.equals(request.getSign());
    }
}
