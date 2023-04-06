package com.fqyc.demo.util;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: lzc
 * @Date: 2020-08-01
 * @descption
 */
@Slf4j
public class UploadDataListener<T> extends AnalysisEventListener<T> {

    private List<T> list = new ArrayList<>();

    private final List<String> headList;

    public UploadDataListener(List<String> headList) {
        this.headList = headList;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param t
     * @param analysisContext
     */
    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        if (isLineNullValue(t)) {
            return;
        }
        list.add(t);
    }

    /**
     * 判断整行单元格数据是否均为空
     */
    private boolean isLineNullValue(T data) {
        if (data instanceof String) {
            return Objects.isNull(data);
        }
        try {
            List<Field> fields = Arrays.stream(data.getClass().getDeclaredFields())
                    .filter(f -> f.isAnnotationPresent(ExcelProperty.class))
                    .collect(Collectors.toList());
            List<Boolean> lineNullList = new ArrayList<>(fields.size());
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(data);
                if (Objects.isNull(value)) {
                    lineNullList.add(Boolean.TRUE);
                } else {
                    lineNullList.add(Boolean.FALSE);
                }
            }
            return lineNullList.stream().allMatch(Boolean.TRUE::equals);
        } catch (Exception e) {
            log.error("读取数据行[{}]解析失败: {}", data, e.getMessage());
        }
        return true;
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {

        if (headList.size() != headMap.size()) {

            throw new RuntimeException("表头校验失败");

        } else {
            for (int i = 0; i < headMap.size(); i++) {
                if (!(headMap.containsValue(headList.get(i)))) {
                    throw new RuntimeException("表头校验失败");
                }
            }
        }
    }

    public List<T> getDataList() {
        return list;
    }
}
