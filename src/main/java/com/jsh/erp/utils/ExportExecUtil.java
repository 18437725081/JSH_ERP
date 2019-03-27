package com.jsh.erp.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

/**
 * 文件导出
 *
 * @author 文件导出
 */
public class ExportExecUtil {

    public static void showExec(File excelFile, String fileName, HttpServletResponse response) throws Exception {
        response.setContentType("application/octet-stream");
        fileName = new String(fileName.getBytes("gbk"), "ISO8859_1");
        response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + ".xls" + "\"");
        FileInputStream fis = new FileInputStream(excelFile);
        OutputStream out = response.getOutputStream();

        byte[] bytes = new byte[1024 * 1024];
        int length;
        while ((length = fis.read(bytes)) != -1) {
            out.write(bytes, 0, length);
        }
        out.flush();
        fis.close();
    }

}
