package com.wp.exam.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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


    public static void  WebFormat(List<Map<String,Object>> dataList){
        dataList.forEach(map -> {
            String string = map.get("flag").toString();
            if (string.equals("0"))
                map.put("flag", "单选题");
            else if (string.equals("1"))
                map.put("flag", "多选题");
            else if (string.equals("2")) {
                map.put("flag", "判断题");
                if (map.get("answer") == null) return;
                if (map.get("answer").toString().equals("0"))
                    map.put("answer", "错");
                else if (map.get("answer").toString().equals("1"))
                    map.put("answer", "对");

                if (map.get("wrong_answer") == null) return;
                if (map.get("wrong_answer").toString().equals("0"))
                    map.put("wrong_answer", "错");
                else if (map.get("wrong_answer").toString().equals("1"))
                    map.put("wrong_answer", "对");
            }
        });

    }

}
