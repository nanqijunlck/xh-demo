package com.fqyc.demo.util;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.data.DataFormatData;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.fqyc.demo.constants.GlobalConstants;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cn.afterturn.easypoi.util.PoiCellUtil.getCellValue;

/**
 * Excel相关操作方法封装
 *
 * @author panda
 */
@Slf4j
public class ExcelUtil {

    private ExcelUtil() {
    }

    public static <E> void write(HttpServletResponse resp, Class<?> clz, String fileName, Iterable<? extends E> elements) throws IOException {
        resp.setContentType("application/vnd.ms-excel");
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xlsx");
        EasyExcel.write(resp.getOutputStream(), clz).sheet("模板").doWrite(Lists.newArrayList(elements));
    }

    public static <E> void write(HttpServletResponse resp, Class<?> clz, String fileName, String sheetName, Iterable<? extends E> elements) throws IOException {
        resp.setContentType("application/vnd.ms-excel");
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xlsx");
        EasyExcel.write(resp.getOutputStream(), clz).registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).sheet(sheetName).doWrite(Lists.newArrayList(elements));
    }


    public static <E> byte[] write2ByteArray(Class<?> clz, Iterable<? extends E> elements, String sheetName) {
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        headWriteCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        EasyExcelFactory.write(out, clz)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .registerWriteHandler(horizontalCellStyleStrategy)
                .sheet(sheetName)
                .doWrite(Lists.newArrayList(elements));
        return out.toByteArray();
    }


    /**
     * 写入单个sheet中，写入xls
     *
     * @param outputStream
     * @param clz
     * @param sheetNo
     * @param fileName
     * @param elements
     * @param <E>
     * @throws IOException
     */
    public static <E> void writeOtherInputStream(FileOutputStream outputStream, Class<?> clz, Integer sheetNo, String fileName, Iterable<? extends E> elements) throws IOException {
        EasyExcel.write(outputStream, clz).sheet(sheetNo, fileName).doWrite(Lists.newArrayList(elements));
    }

    /**
     * 写入多sheet表格
     *
     * @param clz       模板类
     * @param elements  迭代元素 List<List<E>> sheet表数据集合的集合 为null则生成模板文件
     * @param sheetName sheet名集合
     * @param <E>       e
     * @return byte[]
     */
    public static <E> byte[] writeMultiSheet2ByteArray(Class<?> clz, Iterable<? extends List<E>> elements, List<String> sheetName) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(out, clz).build();
            for (int i = 0; i < sheetName.size(); i++) {
                WriteSheet writeSheet = EasyExcel.writerSheet(i, sheetName.get(i))
                        .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                        .build();
                if (null == elements) {
                    excelWriter.write(new ArrayList(), writeSheet);
                } else {
                    excelWriter.write(Lists.newArrayList(elements).get(i), writeSheet);
                }
            }
        } finally {
            if (null != excelWriter) {
                excelWriter.finish();
            }
        }
        return out.toByteArray();
    }

    /**
     * 写入多sheet表格到Response
     *
     * @param clz       模板类
     * @param elements  迭代元素 List<List<E>> sheet表数据集合的集合
     * @param sheetName sheet集合
     * @param <E>       e
     */
    public static <E> void writeMultiSheet2Rsp(HttpServletResponse resp, Class<?> clz, Iterable<? extends List<E>> elements, List<String> sheetName, String fileName) throws IOException {
        resp.setContentType("application/vnd.ms-excel");
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xlsx");
        ExcelWriter excelWriter = null;
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        DataFormatData dataFormatData = new DataFormatData();
        dataFormatData.setFormat("49");
        contentWriteCellStyle.setDataFormatData(dataFormatData);
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
        try {
            excelWriter = EasyExcel.write(resp.getOutputStream(), clz)
                    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                    .registerWriteHandler(horizontalCellStyleStrategy)
                    // 去掉说明列样式
//                    .registerWriteHandler(new ExcelCellStyleWriteHandler())
                    .build();
            for (int i = 0; i < sheetName.size(); i++) {
                WriteSheet writeSheet = EasyExcel.writerSheet(i, sheetName.get(i)).build();
                if (null == elements) {
                    excelWriter.write(new ArrayList(), writeSheet);
                } else {
                    excelWriter.write(Lists.newArrayList(elements).get(i), writeSheet);
                }
            }
        } finally {
            if (null != excelWriter) {
                excelWriter.finish();
            }
        }
    }

    /**
     * 读取多sheet表格
     *
     * @param file 上传文件
     * @param clz  模板类
     * @param <E>  e
     * @return map<sheetName, 导入记录>
     * @throws IOException IO异常
     */
    public static <E> Map<String, List<E>> readMultiSheet(MultipartFile file, Class<E> clz) throws IOException {
        MultiSheetsListener<E> multiSheetsListener = new MultiSheetsListener<>();
        EasyExcel.read(file.getInputStream(), clz, multiSheetsListener).doReadAll();
        return multiSheetsListener.getMap();
    }

    /**
     * @param resp     HttpServletResponse
     * @param clz      对应的Excel Object对象(需要打印的对象）
     * @param fileName 文件名
     * @param e        需要打印的对象
     * @param <E>      需要打印的对象
     * @throws IOException
     */
    public static <E> void write(HttpServletResponse resp, Class<?> clz, String fileName, E e) throws IOException {
        List<E> data = new ArrayList<>();
        data.add(e);
        write(resp, clz, fileName, data);
    }

    public static <E> List<E> read(MultipartFile file, Class<E> clz) throws IOException {
        return EasyExcelFactory.read(file.getInputStream()).sheet().head(clz).doReadSync();
    }

    public static <E> List<E> read(MultipartFile file, Class<E> clz, int headLineNum) throws IOException {
        return EasyExcelFactory.read(file.getInputStream()).headRowNumber(2).sheet().head(clz).doReadSync();
    }

    /***
     * 校验导入的列表Excel文件标题行是否为标准行
     */
    public static String verificationStudentExcelHeadLine(Workbook wb, String[] columnName) {
        String result = null;
        Sheet sheet = wb.getSheetAt(0);
        Row row = sheet.getRow(0);
        //上传文件列数
        if (row != null && row.getLastCellNum() > 4) {
            for (int idx = 0; idx < columnName.length; idx++) {
                String value = getCellValue(row.getCell(idx)).trim();
                if (StringUtils.isBlank(value) || !columnName[idx].equals(value)) {
                    result = "标题行第" + (idx + 1) + "列名称错误！";
                    return result;
                }
            }
        } else {
            result = "上传文件首行不能为空或与模板表格表头不一致！";
        }
        return result;
    }

    /**
     * 通用校验导入的列表Excel文件标题行是否为标准行
     *
     * @param wb         Workbook
     * @param columnName 表头名
     * @param num        列数限制，至少num列
     * @return String 出错提示文本
     */
    public static String verificationStudentExcelHeadLineNew(Workbook wb, String[] columnName, Integer num) {
        String result = null;
        Sheet sheet = wb.getSheetAt(0);
        Row row = sheet.getRow(0);
        //上传文件列数
        if (row != null && row.getLastCellNum() > num) {
            for (int idx = 0; idx < columnName.length; idx++) {
                String value = getCellValue(row.getCell(idx)).trim();
                if (StringUtils.isBlank(value) || !StrUtil.equals(columnName[idx], value)) {
                    result = "标题行第" + (idx + 1) + "列名称错误！";
                    return result;
                }
            }
        } else {
            result = "上传文件首行不能为空或与模板表格表头不一致！";
        }
        return result;
    }

    /**
     * 校验文件格式及表头
     *
     * @param excelFile        上传的文件MultipartFile
     * @param templateContents 表头列表
     * @param columnNum        最小列数
     * @return 校验结果 为null则文件正常
     * @throws IOException 异常
     */
    public static String checkFile(MultipartFile excelFile, List<String> templateContents, Integer columnNum) {
        String result = null;
        if (excelFile == null || excelFile.isEmpty()) {
            result = "文件为空，请选择文件上传";
            return result;
        }
        String fileName = excelFile.getOriginalFilename();
        if (null == fileName) {
            result = "解析出错，请尝试重新上传";
            return result;
        }

        InputStream fin = null;
        try {
            fin = excelFile.getInputStream();
        } catch (Exception e) {
            result = "解析出错，请尝试重新上传";
        }
        if (null == fin) {
            result = "解析出错，请尝试重新上传";
            return result;
        }

        Workbook wb = null;
        if (!fileName.endsWith(GlobalConstants.EXCEL_SUFFIX_XLS) && !fileName.endsWith(GlobalConstants.EXCEL_SUFFIX_XLSX)) {
            //文件不合法
            result = "文件格式错误，请尝试重新上传xls、xlsx文件";
            return result;
        }
        try {
            wb = new XSSFWorkbook(fin);
        } catch (Exception e) {
            try {
                wb = new HSSFWorkbook(fin);
            } catch (Exception ee) {
                result = "解析出错，请尝试重新上传";
                return result;
            }
        }

        //验证导入标题头是否合法
        String validResult = null;
        for (String item : templateContents) {
            String[] split = item.split(",|，|、");
            validResult = ExcelUtil.verificationStudentExcelHeadLineNew(wb, split, columnNum);
            if (StringUtils.isBlank(validResult)) {
                break;
            }
        }
        if (!StringUtils.isBlank(validResult)) {
            return validResult;
        }

        return result;
    }

    /**
     * 校验多Sheet文件格式及表头
     *
     * @param excelFile        上传的文件MultipartFile
     * @param templateContents 表头列表
     * @param columnNum        最小列数
     * @return 校验结果 为null则文件正常
     * @throws IOException 异常
     */
    public static String checkMultiSheetFile(MultipartFile excelFile, List<String> templateContents, Integer columnNum) {
        String result = null;
        if (excelFile == null || excelFile.isEmpty()) {
            result = "文件为空，请选择文件上传";
            return result;
        }
        String fileName = excelFile.getOriginalFilename();
        if (null == fileName) {
            result = "解析出错，请尝试重新上传";
            return result;
        }

        InputStream fin = null;
        try {
            fin = excelFile.getInputStream();
        } catch (Exception e) {
            result = "解析出错，请尝试重新上传";
        }
        if (null == fin) {
            result = "解析出错，请尝试重新上传";
            return result;
        }

        Workbook wb = null;
        if (!fileName.endsWith(GlobalConstants.EXCEL_SUFFIX_XLS) && !fileName.endsWith(GlobalConstants.EXCEL_SUFFIX_XLSX)) {
            //文件不合法
            result = "文件格式错误，请尝试重新上传xls、xlsx文件";
            return result;
        }
        try {
            wb = new XSSFWorkbook(fin);
        } catch (Exception e) {
            try {
                wb = new HSSFWorkbook(fin);
            } catch (Exception ee) {
                result = "解析出错，请尝试重新上传";
                return result;
            }
        }

        //验证导入标题头是否合法
        String validResult = null;
        for (String item : templateContents) {
            String[] split = item.split(",|，|、");
            validResult = ExcelUtil.verificationMultiSheetExcelHeadLine(wb, split, columnNum);
            if (StringUtils.isBlank(validResult)) {
                break;
            }
        }
        if (!StringUtils.isBlank(validResult)) {
            return validResult;
        }

        return result;
    }

    /**
     * 多sheet表头校验
     *
     * @param wb         workBook
     * @param columnName 表头模板
     * @param num        只校验表头模板中的前num个字段
     * @return
     */
    public static String verificationMultiSheetExcelHeadLine(Workbook wb, String[] columnName, Integer num) {
        String result = null;
        int numberOfSheets = wb.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {
            Sheet sheet = wb.getSheetAt(i);
            Row row = sheet.getRow(0);
            //上传文件列数
            if (row != null && row.getLastCellNum() > num) {
                for (int idx = 0; idx < num; idx++) {
                    String value = getCellValue(row.getCell(idx)).trim();
                    if (StringUtils.isBlank(value) || !columnName[idx].equals(value)) {
                        log.debug("表头值{}，模板值{}", value, columnName[idx]);
                        result = sheet.getSheetName() + "标题行第" + (idx + 1) + "列名称错误！";
                        return result;
                    }
                }
            } else {
                result = "上传文件首行不能为空或与模板表格表头不一致！";
            }
        }
        return result;
    }

    /**
     * 处理sheet名称，兼容导出api，规则如下
     * 长度不能超过31，开头和结尾不能为 '
     * 不能包含 * / : ? [ \\ ] 符号
     * @param sheetName
     * @return
     */
    public static String handleSheetName(String sheetName) {
        int maxSheetNameLength = 31;
        // 如果首字符为'
        if (sheetName.charAt(0) == '\'') {
            sheetName = sheetName.substring(1);
        }
        if (sheetName.charAt(sheetName.length() - 1) == '\'') {
            sheetName = sheetName.substring(0, sheetName.length() - 1);
        }
        String regEx= "[*/:?\\[\\]\\\\]";
        Pattern pattern  =  Pattern.compile(regEx);
        Matcher matcher  =  pattern.matcher(sheetName);
        sheetName = matcher.replaceAll("");
        if (sheetName.length() > maxSheetNameLength) {
            sheetName = sheetName.substring(0, 31);
        }
        return sheetName;
    }
}

