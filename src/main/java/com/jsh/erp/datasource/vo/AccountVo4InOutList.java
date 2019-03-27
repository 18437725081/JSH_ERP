package com.jsh.erp.datasource.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountVo4InOutList {

    private String number;

    private String type;

    private String supplierName;

    private BigDecimal changeAmount;

    private BigDecimal balance;

    private String operTime;

    private String aList;

    private String amList;

    private Long tenantId;
}