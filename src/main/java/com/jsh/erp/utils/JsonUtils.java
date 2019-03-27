package com.jsh.erp.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * @author 暗香
 */
public class JsonUtils {
    public static JSONObject ok() {
        JSONObject obj = new JSONObject();
        JSONObject tmp = new JSONObject();
        tmp.put("message", "成功");
        obj.put("code", 200);
        obj.put("data", tmp);
        return obj;
    }

}
