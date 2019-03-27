package com.jsh.erp.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

import static com.jsh.erp.utils.Constants.CURRENT_PAGE;
import static com.jsh.erp.utils.Constants.PAGE_SIZE;

/**
 * @author 暗香
 */
public class QueryUtils {

    public static List<String> search(Map<String, String> map) {
        List<String> search = null;

        String str = map.get(Constants.SEARCH);
        if (!StringUtil.isEmpty(str)) {
            search = StringUtil.searchCondition(str);
        }
        return search;
    }

    public static Integer rows(Map<String, String> map) {
        return Integer.parseInt(map.get(PAGE_SIZE));
    }

    public static Integer offset(Map<String, String> map) {
        return (currentPage(map) - 1) * pageSize(map);
    }

    public static Integer pageSize(Map<String, String> map) {
        return Integer.parseInt(map.get(PAGE_SIZE));
    }

    public static Integer currentPage(Map<String, String> map) {
        int val = Integer.parseInt(map.get(CURRENT_PAGE));
        if (val < 1)
            throw new RuntimeException("当前页数目:" + val + " 必须大于0");
        return val;
    }

    public static String order(Map<String, String> map) {
        String orderString = OrderUtils.getOrderString(map.get(Constants.ORDER));
        return orderString.trim().isEmpty() ? null : orderString;
    }

    public static Integer level(Map<String, String> map) {
        String levelString = map.get(Constants.LEVEL);
        return StringUtil.isEmpty(levelString) ? null : Integer.parseInt(levelString);
    }

    public static int type(Map<String, String> map) {
        return Integer.parseInt(map.get(Constants.TYPE));
    }

    public static String filter(Map<String, String> map) {
        if (map.containsKey(Constants.FILTER)) {
            JSONArray array = JSON.parseArray(map.get(Constants.FILTER));
            if (array.isEmpty()) {
                return null;
            } else {
                boolean first = true;
                StringBuilder builder = new StringBuilder();
                for (int idx = 0; idx < array.size(); ++idx) {
                    JSONObject object = array.getJSONObject(idx);
                    if (object.get("value") instanceof JSONArray) {
                        JSONArray value = object.getJSONArray("value");
                        if (!value.isEmpty()) {
                            if (!first) {
                                builder.append(" AND ");
                            } else {
                                first = false;
                            }
                            String key = object.getString("name");
                            builder.append("(");
                            builder.append("`").append(key).append("`");
                            builder.append(" IN ");
                            builder.append("(");
                            for (int vidx = 0; vidx < value.size(); ++vidx) {
                                if (vidx != 0) {
                                    builder.append(",");
                                }
                                builder.append(value.getString(vidx));
                            }
                            builder.append(")");
                            builder.append(")");
                        }
                    }
                }
                return builder.toString();
            }
        } else {
            return null;
        }
    }
}
