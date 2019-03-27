package com.jsh.erp.utils;

/**
 * @author 暗香
 */
public class OrderUtils {

    /**
     * 将指定字段排序
     *
     * @param orders 格式 属性名,排序方式 例如( name,asc或ip,desc)
     * @return 排序字符串 例如：（name asc 或 ip desc）
     */
    public static String getOrderString(String orders) {
        if (!StringUtil.isEmpty(orders)) {
            String[] splits = orders.split(Constants.SPLIT);
            if (splits.length == 2) {
                String column = ColumnPropertyUtil.propertyToColumn(splits[0]);
                if (column.equals("audit_status")) {
                    return "IF(`audit_status`=3,-1,`audit_status`) " + splits[1];
                } else if (column.equals("create_time") || column.equals("modify_time")) {
                    return column + " " + splits[1];
                } else {
                    return "convert(" + column + " using gbk) " + splits[1];
                }
            }
        }
        return "";
    }
}
