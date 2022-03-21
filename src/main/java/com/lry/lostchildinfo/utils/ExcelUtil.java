package com.lry.lostchildinfo.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Description : Excel工具类
 * @ClassName : ExcelUtil
 * @Author : jdl
 * @Create : 2022-01-28 18:08
 */
public class ExcelUtil {

    /**
     * 数据库中的数据导出
     * @param response
     * @param pojoClass
     * @param list
     */
    public static void exportExcel(HttpServletResponse response , Class<?> pojoClass , List<?> list ,String title)  {
        response.setCharacterEncoding("utf-8");
        // 设置响应输出的头类型(设置响应类型)
        response.setHeader("Content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称(设置下载文件的默认名称)
        //导出操作
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(title, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.addHeader("Cache-Control", "no-cache");
        // ExportParams 1，第一个参数为 标题，2第二个为sheet表名
        try {
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(title, title), pojoClass, list);
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
