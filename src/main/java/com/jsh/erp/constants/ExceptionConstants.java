package com.jsh.erp.constants;

import com.alibaba.fastjson.JSONObject;

/**
 * @author 暗香
 */
public class ExceptionConstants {

    /**
     * code 格式 type+五位数字，例如3500000
     * ResourceInfo(value = "inOutItem", type = 35)
     */

    public static final String GLOBAL_RETURNS_CODE = "code";
    public static final String GLOBAL_RETURNS_MESSAGE = "msg";
    public static final String GLOBAL_RETURNS_DATA = "data";

    /**
     * 正常返回/操作成功
     **/
    public static final Integer SERVICE_SUCCESS_CODE = 200;
    public static final String SERVICE_SUCCESS_MSG = "操作成功";

    /**
     * 系统运行时未知错误
     **/
    public static final Integer SERVICE_SYSTEM_ERROR_CODE = 500;
    public static final String SERVICE_SYSTEM_ERROR_MSG = "未知异常";

    /**
     * 用户信息
     * 添加用户信息失败
     * type = 5
     */
    public static final Integer USER_ADD_FAILED_CODE = 500000;
    public static final String USER_ADD_FAILED_MSG = "添加用户信息失败";

    /**
     * 删除用户信息失败
     */
    public static final Integer USER_DELETE_FAILED_CODE = 500001;
    public static final String USER_DELETE_FAILED_MSG = "删除用户信息失败";

    /**
     * 修改用户信息失败
     */
    public static final Integer USER_EDIT_FAILED_CODE = 500002;
    public static final String USER_EDIT_FAILED_MSG = "修改用户信息失败";

    /**
     * 用户名已存在
     */
    public static final Integer USER_USER_NAME_ALREADY_EXISTS_CODE = 500003;
    public static final String USER_USER_NAME_ALREADY_EXISTS_MSG = "用户名已存在";

    /**
     * 登录名已存在
     */
    public static final Integer USER_LOGIN_NAME_ALREADY_EXISTS_CODE = 500003;
    public static final String USER_LOGIN_NAME_ALREADY_EXISTS_MSG = "登录名已存在";


    /**
     * 商品类别信息
     * type = 75
     */

    /**
     * 添加商品类别信息失败
     */
    public static final Integer MATERIAL_CATEGORY_ADD_FAILED_CODE = 7500000;
    public static final String MATERIAL_CATEGORY_ADD_FAILED_MSG = "添加商品类别信息失败";

    /**
     * 删除商品类别信息失败
     */
    public static final Integer MATERIAL_CATEGORY_DELETE_FAILED_CODE = 7500001;
    public static final String MATERIAL_CATEGORY_DELETE_FAILED_MSG = "删除商品类别信息失败";

    /**
     * 修改商品类别信息失败
     */
    public static final Integer MATERIAL_CATEGORY_EDIT_FAILED_CODE = 7500002;
    public static final String MATERIAL_CATEGORY_EDIT_FAILED_MSG = "添加商品类别信息失败";

    /**
     * 商品类别编号已存在
     */
    public static final Integer MATERIAL_CATEGORY_SERIAL_ALREADY_EXISTS_CODE = 7500003;
    public static final String MATERIAL_CATEGORY_SERIAL_ALREADY_EXISTS_MSG = "商品类别编号已存在";

    /**
     * 商品信息
     * type = 80
     */
    /**
     * 商品信息不存在
     */
    public static final Integer MATERIAL_NOT_EXISTS_CODE = 8000000;
    public static final String MATERIAL_NOT_EXISTS_MSG = "商品信息不存在";

    /**
     * 商品信息不唯一
     */
    public static final Integer MATERIAL_NOT_ONLY_CODE = 8000001;
    public static final String MATERIAL_NOT_ONLY_MSG = "商品信息不唯一";

    /**
     * 该商品未开启序列号
     */
    public static final Integer MATERIAL_NOT_ENABLE_SERIAL_NUMBER_CODE = 8000002;
    public static final String MATERIAL_NOT_ENABLE_SERIAL_NUMBER_MSG = "该商品未开启序列号功能";

    /**
     * 商品库存不足
     */
    public static final Integer MATERIAL_STOCK_NOT_ENOUGH_CODE = 8000004;
    public static final String MATERIAL_STOCK_NOT_ENOUGH_MSG = "商品:%s库存不足";

    /**
     * 序列号
     * type = 105
     * */

    /**
     * 序列号已存在
     */
    public static final Integer SERIAL_NUMBERE_ALREADY_EXISTS_CODE = 10500000;
    public static final String SERIAL_NUMBERE_ALREADY_EXISTS_MSG = "序列号已存在";

    /**
     * 序列号不能为为空
     */
    public static final Integer SERIAL_NUMBERE_NOT_BE_EMPTY_CODE = 10500001;
    public static final String SERIAL_NUMBERE_NOT_BE_EMPTY_MSG = "序列号不能为为空";

    /**
     * 商品%s下序列号不充足，请补充后重试
     */
    public static final Integer MATERIAL_SERIAL_NUMBERE_NOT_ENOUGH_CODE = 10500002;
    public static final String MATERIAL_SERIAL_NUMBERE_NOT_ENOUGH_MSG = "商品:%s下序列号不充足，请补充后重试";


    /**
     * 机构
     * 添加机构信息失败
     * type = 110
     */
    public static final Integer ORGANIZATION_ADD_FAILED_CODE = 11000000;
    public static final String ORGANIZATION_ADD_FAILED_MSG = "添加机构信息失败";

    /**
     * 删除机构信息失败
     */
    public static final Integer ORGANIZATION_DELETE_FAILED_CODE = 11000001;
    public static final String ORGANIZATION_DELETE_FAILED_MSG = "删除机构信息失败";

    /**
     * 修改机构信息失败
     */
    public static final Integer ORGANIZATION_EDIT_FAILED_CODE = 11000002;
    public static final String ORGANIZATION_EDIT_FAILED_MSG = "修改机构信息失败";

    /**
     * 机构编号已存在
     */
    public static final Integer ORGANIZATION_NO_ALREADY_EXISTS_CODE = 11000003;
    public static final String ORGANIZATION_NO_ALREADY_EXISTS_MSG = "机构编号已存在";

    /**
     * 机构用户关联关系
     * 添加机构用户关联关系失败
     * type = 115
     */
    public static final Integer ORGA_USER_REL_ADD_FAILED_CODE = 11500000;
    public static final String ORGA_USER_REL_ADD_FAILED_MSG = "添加机构用户关联关系失败";

    /**
     * 修改机构用户关联关系失败
     */
    public static final Integer ORGA_USER_REL_EDIT_FAILED_CODE = 11500002;
    public static final String ORGA_USER_REL_EDIT_FAILED_MSG = "修改机构用户关联关系失败";

    /**
     * 标准正常返回/操作成功返回
     *
     * @return
     */
    public static JSONObject standardSuccess() {
        JSONObject success = new JSONObject();
        success.put(GLOBAL_RETURNS_CODE, SERVICE_SUCCESS_CODE);
        success.put(GLOBAL_RETURNS_MESSAGE, SERVICE_SUCCESS_MSG);
        return success;
    }
}
