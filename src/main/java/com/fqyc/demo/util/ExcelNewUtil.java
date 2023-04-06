package com.fqyc.demo.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fqyc.demo.constants.ExceptionCodeConstants;
import com.fqyc.demo.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: lzc
 * @Date: 2022-08-01 15:41
 * @descption
 */
@Slf4j
public class ExcelNewUtil {

    /**
     * 根据ExcelFile文件查询
     *
     * @param excelFile
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> readExcel(MultipartFile excelFile,Class clazz) throws IOException {
        checkFile(excelFile);
        return readExcel(excelFile.getInputStream(),clazz,0);
    }

    /**
     * 根据流查询指定sheet;下标从 0 开始
     *
     * @param inputStream
     * @param clazz
     * @param sheetIndex
     * @param <T>
     * @return
     */
    public static <T> List<T> readExcel(InputStream inputStream,Class<T> clazz,int sheetIndex){
        List<String> headList = excelHeadData(clazz);
        UploadDataListener uploadDataListener = new UploadDataListener(headList);
        EasyExcel.read(inputStream, clazz, uploadDataListener).sheet(sheetIndex).doRead();

        return uploadDataListener.getDataList();
    }

    public static void checkFile(MultipartFile excelFile) throws IOException {
        if (excelFile == null || excelFile.isEmpty()) {
            throw new BizException(ExceptionCodeConstants.EXCEL_SIZE_ERROR, "文件为空，请选择文件上传");
        }
        String fileName = excelFile.getOriginalFilename();
        InputStream fin = excelFile.getInputStream();
        Workbook wb = null;
        if (!fileName.endsWith("xlsx") && !fileName.endsWith("xls")) {
            //文件不合法
            throw new BizException(ExceptionCodeConstants.EXCEL_FILE_ERROR);
        }
        try {
            wb = new XSSFWorkbook(fin);
        } catch (Exception e) {
            log.error("07 fail XSSFWorkbook error = ", e);
            try {
                wb = new HSSFWorkbook(fin);
            } catch (Exception ee) {
                log.error("HSSFWorkbook error = ", e);
            }
        }
    }

    public static void uploadHandle(HttpServletResponse httpServletResponse, String fileName, Class clazz) throws Exception {
        httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        httpServletResponse.setCharacterEncoding("utf-8");
        fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
        httpServletResponse.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(httpServletResponse.getOutputStream(), clazz)
                .sheet("模板")
                .doWrite(Collections.emptyList());
    }

    /**
     * 获取导入类中属性标注的ExcelProperty且未被ExcelIgore注解标注的值
     *
     *
     * @author 张杰 zhangjie
     */
    public static <T> List<String> excelHeadData(Class<T> clazz){
        Field[] fields = clazz.getDeclaredFields();
        List<String> excelHeadData = new ArrayList<>(fields.length);
        for (Field field : fields) {
            //筛选被@ExcelProperty修饰且未被@ExcelIgore修饰的属性
            if (field.isAnnotationPresent(ExcelProperty.class) && !field.isAnnotationPresent(ExcelIgnore.class)){
                ExcelProperty annotation = field.getAnnotation(ExcelProperty.class);
                excelHeadData.add(annotation.value()[0]);
            }
        }
        return excelHeadData;
    }
}
