package com.jsh.erp.service.inOutItem;

import com.jsh.erp.service.ResourceInfo;

import java.lang.annotation.*;

/**
 */
@ResourceInfo(value = "inOutItem", type = 35)
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface InOutItemResource {
}
