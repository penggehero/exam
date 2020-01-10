package com.wp.exam.util;

import java.util.HashMap;
import java.util.Map;

public class ServiceUtil {

    /**
     * 生产结果
     *
     * @param resultData
     * @param errmsg
     * @return
     */
    public static Map<String, Object> makeResult(Object resultData, String errmsg) {
        Map<String, Object> result = new HashMap<>();
        if (null == errmsg) {
            result.put("msg", "");
            result.put("status", 1);
            result.put("resultData", resultData);
        } else {
            result.put("msg", errmsg);
            result.put("status", 0);
            result.put("resultData", null);
        }
        return result;
    }
}
