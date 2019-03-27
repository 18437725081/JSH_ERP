package com.jsh.erp.utils;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.*;

import java.io.File;
import java.util.List;

/**
 * @author 暗香
 */
public class ExcelUtils {

    /**
     * 导出excel，不需要第一行的title
     *
     * @param fileName 文件名称
     * @param names    列名
     * @param title    标题
     * @param objects  列值
     * @return 文件对象
     */
    public static File exportObjectsWithoutTitle(String fileName, String[] names, String title, List<String[]> objects) throws Exception {
        File excelFile = new File(fileName);
        WritableWorkbook wtwb = Workbook.createWorkbook(excelFile);
        WritableSheet sheet = wtwb.createSheet(title, 0);
        sheet.getSettings().setDefaultColumnWidth(20);
        WritableFont wfc = new WritableFont(WritableFont.ARIAL, 15,
                WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
                jxl.format.Colour.BLACK);
        WritableCellFormat wcfFC = new WritableCellFormat(wfc);
        wcfFC.setVerticalAlignment(VerticalAlignment.CENTRE);
        WritableFont wfont = new WritableFont(WritableFont.createFont("楷书"), 15);
        WritableCellFormat format = new WritableCellFormat(wfont);
        format.setAlignment(Alignment.LEFT);
        format.setVerticalAlignment(VerticalAlignment.TOP);
        // 第一行写入标题
        for (int i = 0; i < names.length; i++) {
            sheet.addCell(new Label(i, 0, names[i], wcfFC));
        }
        // 其余行依次写入数据
        int rowNum = 1;
        for (int j = 0; j < objects.size(); j++) {
            String[] obj = objects.get(j);
            for (int h = 0; h < obj.length; h++) {
                sheet.addCell(new Label(h, rowNum, obj[h], format));
            }
            rowNum = rowNum + 1;
        }
        wtwb.write();
        wtwb.close();
        return excelFile;
    }


    public static String getContent(Sheet src, int rowNum, int colNum) {
        return src.getRow(rowNum)[colNum].getContents().trim();
    }
}
