package com.jsh.erp.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jsh.erp.datasource.entities.App;
import com.jsh.erp.datasource.entities.UserBusiness;
import com.jsh.erp.service.app.AppService;
import com.jsh.erp.service.userBusiness.UserBusinessService;
import com.jsh.erp.utils.BaseResponseInfo;
import com.jsh.erp.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;
import java.util.Properties;

/**
 * @author 暗香
 */
@RestController
@RequestMapping(value = "/app")
public class AppController {
    private Logger logger = LoggerFactory.getLogger(AppController.class);

    @Resource
    private AppService appService;

    @Resource
    private UserBusinessService userBusinessService;

    /**
     * 根据用户查询有权限的app
     *
     * @param userId
     * @param request
     * @return
     */
    @GetMapping(value = "/findAppByUserId")
    public JSONObject findAppByUserId(@RequestParam("userId") String userId, HttpServletRequest request) {
        List<UserBusiness> roleList = userBusinessService.findRoleByUserId(userId);
        String roles = null;
        if (roleList != null && roleList.size() > 0 && roleList.get(0) != null) {
            roles = roleList.get(0).getValue();
        }
        if (roles != null) {
            roles = roles.replaceAll("\\]\\[", ",").replaceAll("\\]", "").replaceAll("\\[", "");
        }
        List<UserBusiness> appList = userBusinessService.findAppByRoles(roles);
        String apps = null;
        if (appList != null && appList.size() > 0 && appList.get(0) != null) {
            apps = appList.get(0).getValue();
        }
        if (apps != null) {
            //转为逗号隔开的
            apps = apps.replaceAll("\\]\\[", ",").replaceAll("\\]", "").replaceAll("\\[", "");
        }
        JSONObject obj = new JSONObject();
        List<App> dockList = appService.findAppInIds(apps, "dock");
        JSONArray dockArray = new JSONArray();
        if (null != dockList) {
            for (App app : dockList) {
                JSONObject item = new JSONObject();
                item.put("title", app.getName());
                item.put("id", app.getId());
                item.put("type", app.getType());
                item.put("icon", "../../upload/images/deskIcon/" + app.getIcon());
                item.put("url", app.getUrl());
                item.put("width", app.getWidth());
                item.put("height", app.getHeight());
                item.put("isresize", app.getResize());
                item.put("isopenmax", app.getOpenmax());
                item.put("isflash", app.getFlash());
                dockArray.add(item);
            }
        }
        obj.put("dock", dockArray);

        List<App> deskList = appService.findAppInIds(apps, "desk");
        JSONArray deskArray = new JSONArray();
        if (null != deskList) {
            for (App app : deskList) {
                JSONObject item = new JSONObject();
                item.put("id", app.getId());
                item.put("type", app.getType());
                item.put("title", app.getName());
                item.put("icon", "../../upload/images/deskIcon/" + app.getIcon());
                item.put("url", "../../pages/common/menu.html?appID=" + app.getNumber() + "&id=" + app.getId());
                item.put("width", app.getWidth());
                item.put("height", app.getHeight());
                item.put("isresize", app.getResize());
                item.put("isopenmax", app.getOpenmax());
                item.put("isflash", app.getFlash());
                deskArray.add(item);
            }
        }
        obj.put("desk", deskArray);
        return obj;
    }

    @GetMapping(value = "/findDesk")
    public JSONObject findDesk() {
        JSONObject obj = new JSONObject();
        List<App> dockList = appService.findDock();
        JSONArray dockArray = new JSONArray();
        if (null != dockList) {
            for (App app : dockList) {
                JSONObject item = new JSONObject();
                item.put("id", app.getId());
                item.put("title", app.getName());
                item.put("type", app.getType());
                item.put("url", app.getUrl());
                item.put("icon", "../../upload/images/deskIcon/" + app.getIcon());
                item.put("width", app.getWidth());
                item.put("height", app.getHeight());
                item.put("isresize", app.getResize());
                item.put("isopenmax", app.getOpenmax());
                item.put("isflash", app.getFlash());
                dockArray.add(item);
            }
        }
        obj.put("dock", dockArray);
        List<App> deskList = appService.findDesk();
        JSONArray deskArray = new JSONArray();
        if (null != deskList) {
            for (App app : deskList) {
                JSONObject item = new JSONObject();
                item.put("id", app.getId());
                item.put("title", app.getName());
                item.put("type", app.getType());
                item.put("icon", "../../upload/images/deskIcon/" + app.getIcon());
                item.put("url", "../../pages/common/menu.html?appID=" + app.getNumber() + "&id=" + app.getId());
                item.put("width", app.getWidth());
                item.put("height", app.getHeight());
                item.put("isresize", app.getResize());
                item.put("isopenmax", app.getOpenmax());
                item.put("isflash", app.getFlash());
                deskArray.add(item);
            }
        }
        obj.put("desk", deskArray);
        return obj;
    }

    /**
     * 角色对应应用显示
     *
     * @return
     */
    @PostMapping(value = "/findRoleAPP")
    public JSONArray findRoleAPP(@RequestParam("UBType") String type, @RequestParam("UBKeyId") String keyId) {
        JSONArray arr = new JSONArray();
        try {
            List<App> dataList = appService.findRoleAPP();
            //开始拼接json数据
            JSONObject outer = new JSONObject();
            outer.put("id", 1);
            outer.put("text", "应用列表");
            outer.put("state", "open");
            //存放数据json数组
            JSONArray dataArray = new JSONArray();
            if (null != dataList) {
                for (App app : dataList) {
                    JSONObject item = new JSONObject();
                    item.put("id", app.getId());
                    item.put("text", app.getName());
                    //勾选判断1
                    Boolean flag = false;
                    try {
                        flag = userBusinessService.checkIsUserBusinessExist(type, keyId, "[" + app.getId().toString() + "]");
                    } catch (Exception e) {
                        logger.error(">>>>>>>>>>>>>>>>>设置角色对应的应用：类型" + type + " KeyId为： " + keyId + " 存在异常！");
                    }
                    if (flag) {
                        item.put("checked", true);
                    }
                    dataArray.add(item);
                }
            }
            outer.put("children", dataArray);
            arr.add(outer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    /**
     * 上传图片
     *
     * @param fileInfo
     * @param request
     */
    @PostMapping(value = "/uploadImg")
    public BaseResponseInfo uploadImg(MultipartFile fileInfo, @RequestParam("fileInfoName") String fileName,
                                      HttpServletRequest request) {
        BaseResponseInfo res = new BaseResponseInfo();
        try {
            if (fileInfo != null) {
                //默认windows文件路径，linux环境下生成的目录与项目同级，而不是下级
                String basePath = request.getSession().getServletContext().getRealPath("/");
                //windows环境下的路径
                String path = basePath + "upload/images/deskIcon/";
                Properties pro = System.getProperties();
                //获得当前操作系统的名称
                String osName = pro.getProperty("os.name");
                if ("Linux".equals(osName) || "linux".equals(osName) || "LINUX".equals(osName)) {
                    //linux环境下的路径
                    path = basePath + "/upload/images/deskIcon/";
                }
                FileUtils.SaveFileFromInputStream(fileInfo.getInputStream(), path, fileName);
                res.code = 200;
                res.data = "上传图片成功";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            res.code = 500;
            res.data = "获取图片失败";
        } catch (IOException e) {
            e.printStackTrace();
            res.code = 500;
            res.data = "上传图片失败";
        }
        return res;
    }
}
