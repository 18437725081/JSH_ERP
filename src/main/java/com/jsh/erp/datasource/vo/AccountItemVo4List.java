package com.jsh.erp.datasource.vo;

import com.jsh.erp.datasource.entities.AccountItem;
import lombok.Data;

/**
 * @author 暗香
 */
@Data
public class AccountItemVo4List extends AccountItem {

    /**
     * 账户名称
     */
    private String accountName;

    /** */
    private String inOutItemName;
}