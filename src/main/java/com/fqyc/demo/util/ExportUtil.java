package com.fqyc.demo.util;

import java.io.*;
import java.util.*;

/**
 * @author lck
 * @date 2020/6/29   17:29
 * @since 1.0
 */
public class ExportUtil {

    public static byte[] exportCSV(List<LinkedHashMap<String, Object>> exportData) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BufferedWriter buffCvsWriter = null;
        try {
            OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
            osw.write(new String(new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF}));
            buffCvsWriter = new BufferedWriter(osw);
            // 将body数据写入表格
            for (Iterator<LinkedHashMap<String, Object>> iterator = exportData.iterator(); iterator.hasNext(); ) {
                fillDataToCsv(buffCvsWriter, iterator.next());
                if (iterator.hasNext()) {
                    buffCvsWriter.newLine();
                }
            }
            // 刷新缓冲
            buffCvsWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            if (buffCvsWriter != null) {
                try {
                    buffCvsWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return out.toByteArray();
    }

    private static void fillDataToCsv(BufferedWriter buffCvsWriter, LinkedHashMap row) throws IOException {
        Map.Entry propertyEntry;
        for (Iterator<Map.Entry> propertyIterator = row.entrySet().iterator(); propertyIterator.hasNext(); ) {
            propertyEntry = propertyIterator.next();
            if(propertyEntry !=null && propertyEntry.getValue() != null){
                buffCvsWriter.write("\"" + propertyEntry.getValue().toString() + "\"");
            }
            if (propertyIterator.hasNext()) {
                buffCvsWriter.write(",");
            }
        }
    }

    /**
     *  * CSV文件导入
     * <p>
     *  * @param file csv文件（路径+文件）
     * <p>
     *  * @return
     * <p>
     *  
     */

    public static List<String> importCsv(File file) {
        List<String> dataList = new ArrayList<String>();//数据
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));//为文件流赋数据
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                dataList.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                    bufferedReader = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return dataList;
    }
}