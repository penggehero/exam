package com.wp.exam.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Service工具类
 */
public class ServiceUtil {

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final String UNKNOWNERROR = "发送未知错误!";

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
            result.put("timestamp", format.format(new Date()));
        } else {
            result.put("msg", errmsg);
            result.put("status", 0);
            result.put("resultData", null);
            result.put("timestamp", format.format(new Date()));
        }
        return result;
    }

    /**
     * 添加分页的start和end
     *
     * @param param
     */
    public static void addStartAndEnd(Map<String, Object> param) {
        int rows = Integer.valueOf(param.get("rows").toString());
        param.put("start", rows * (Integer.valueOf(param.get("page").toString()) - 1));
        param.put("end", rows);
    }

    /**
     * 封装dataList和total
     *
     * @param dataList
     * @param total
     * @return
     */
    public static Map<String, Object> addTotalAndDatalist(Object dataList, int total) {
        Map<String, Object> result = new HashMap<>();
        if (total < 0) {
            result.put("datalist", dataList);
        } else {
            result.put("total", total);
            result.put("datalist", dataList);
        }
        return result;
    }

}
