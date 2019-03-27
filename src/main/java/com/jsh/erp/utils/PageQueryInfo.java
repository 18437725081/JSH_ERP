package com.jsh.erp.utils;

import lombok.Data;

import java.util.List;

/**
 * 分页查询结果
 *
 * @author 暗香
 */
@Data
public class PageQueryInfo {

    /**
     * 总条数
     */
    private Long total;

    /**
     * 记录总数
     */
    private List<?> rows;
}
