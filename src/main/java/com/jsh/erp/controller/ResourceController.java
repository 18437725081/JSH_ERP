package com.jsh.erp.controller;

import com.alibaba.fastjson.JSONObject;
import com.jsh.erp.constants.BusinessConstants;
import com.jsh.erp.service.CommonQueryManager;
import com.jsh.erp.utils.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.jsh.erp.utils.ResponseJsonUtil.returnJson;

/**
 * @author 暗香
 */
@RestController
public class ResourceController {
    private Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @Resource
    private CommonQueryManager configResourceManager;

    @GetMapping(value = "/test/heart")
    public JSONObject exitHeart(HttpServletRequest request) {
        return JsonUtils.ok();
    }

    @GetMapping(value = "/{apiName}/list")
    public String getList(@PathVariable("apiName") String apiName,
                        @RequestParam(value = Constants.PAGE_SIZE, required = false) Integer pageSize,
                        @RequestParam(value = Constants.CURRENT_PAGE, required = false) Integer currentPage,
                        @RequestParam(value = Constants.SEARCH, required = false) String search,
                        HttpServletRequest request) {
        Map<String, String> parameterMap = ParamUtils.requestToMap(request);
        parameterMap.put(Constants.SEARCH, search);
        PageQueryInfo queryInfo = new PageQueryInfo();
        Map<String, Object> objectMap = new HashMap<String, Object>();
        if (pageSize != null && pageSize <= 0) {
            pageSize = 10;
        }
        String offset = ParamUtils.getPageOffset(currentPage, pageSize);
        if (StringUtil.isNotEmpty(offset)) {
            parameterMap.put(Constants.OFFSET, offset);
        }
        List<?> list = configResourceManager.select(apiName, parameterMap);
        objectMap.put("page", queryInfo);
        if (list == null) {
            queryInfo.setRows(new ArrayList<Object>());
            queryInfo.setTotal(BusinessConstants.DEFAULT_LIST_NULL_NUMBER);
            return returnJson(objectMap, "查找不到数据", ErpInfo.OK.code);
        }
        queryInfo.setRows(list);
        queryInfo.setTotal(configResourceManager.counts(apiName, parameterMap));
        return returnJson(objectMap, ErpInfo.OK.name, ErpInfo.OK.code);
    }

    @PostMapping(value = "/{apiName}/add", produces = {"application/javascript", "application/json"})
    public String addResource(@PathVariable("apiName") String apiName,
                              @RequestParam("info") String beanJson, HttpServletRequest request) {
        Map<String, Object> objectMap = new HashMap<String, Object>();
        int insert = configResourceManager.insert(apiName, beanJson, request);
        if(insert > 0) {
            return returnJson(objectMap, ErpInfo.OK.name, ErpInfo.OK.code);
        } else {
            return returnJson(objectMap, ErpInfo.ERROR.name, ErpInfo.ERROR.code);
        }
    }

    @PostMapping(value = "/{apiName}/update", produces = {"application/javascript", "application/json"})
    public String updateResource(@PathVariable("apiName") String apiName,
                              @RequestParam("info") String beanJson,
                              @RequestParam("id") Long id, HttpServletRequest request) {
        Map<String, Object> objectMap = new HashMap<String, Object>();
        int update = configResourceManager.update(apiName, beanJson, id, request);
        if(update > 0) {
            return returnJson(objectMap, ErpInfo.OK.name, ErpInfo.OK.code);
        } else {
            return returnJson(objectMap, ErpInfo.ERROR.name, ErpInfo.ERROR.code);
        }
    }

    @PostMapping(value = "/{apiName}/{id}/delete", produces = {"application/javascript", "application/json"})
    public String deleteResource(@PathVariable("apiName") String apiName,
                                 @PathVariable Long id, HttpServletRequest request) {
        Map<String, Object> objectMap = new HashMap<String, Object>();
        int delete = configResourceManager.delete(apiName, id, request);
        if(delete > 0) {
            return returnJson(objectMap, ErpInfo.OK.name, ErpInfo.OK.code);
        } else {
            return returnJson(objectMap, ErpInfo.ERROR.name, ErpInfo.ERROR.code);
        }
    }

    @PostMapping(value = "/{apiName}/batchDelete", produces = {"application/javascript", "application/json"})
    public String batchDeleteResource(@PathVariable("apiName") String apiName,
                                      @RequestParam("ids") String ids, HttpServletRequest request) {
        Map<String, Object> objectMap = new HashMap<String, Object>();
        int delete = configResourceManager.batchDelete(apiName, ids, request);
        if(delete > 0) {
            return returnJson(objectMap, ErpInfo.OK.name, ErpInfo.OK.code);
        } else {
            return returnJson(objectMap, ErpInfo.ERROR.name, ErpInfo.ERROR.code);
        }
    }

    @GetMapping(value = "/{apiName}/checkIsNameExist")
    public String checkIsNameExist(@PathVariable("apiName") String apiName,
                                   @RequestParam Long id, @RequestParam(value ="name", required = false) String name,
                                   HttpServletRequest request) {
        Map<String, Object> objectMap = new HashMap<String, Object>();
        int exist = configResourceManager.checkIsNameExist(apiName, id, name);
        if(exist > 0) {
            objectMap.put("status", true);
        } else {
            objectMap.put("status", false);
        }
        return returnJson(objectMap, ErpInfo.OK.name, ErpInfo.OK.code);
    }


}
