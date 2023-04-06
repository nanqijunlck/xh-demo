package com.fqyc.demo.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xieyingchao
 * @description: 多sheet表读取监听器
 * @date 2021/3/29 14:09
 */

@Slf4j
public class MultiSheetsListener<E> extends AnalysisEventListener<E> {

    private final Map<String, List<E>> map;

    private Map<Integer, String> headMap;

    public MultiSheetsListener() {
        this.map = new HashMap<>();
    }


    public Map<String, List<E>> getMap() {
        return map;
    }

    public Map<Integer, String> getHeadMap() {
        return headMap;
    }

    public void setHeadMap(Map<Integer, String> headMap) {
        this.headMap = headMap;
    }

    @Override
    public void invoke(E e, AnalysisContext analysisContext) {
        log.debug("本条记录{}", JSON.toJSONString(analysisContext));
        if (null == map.get(analysisContext.readSheetHolder().getSheetName())) {
            ArrayList<E> es = new ArrayList<>();
            es.add(e);
            map.put(analysisContext.readSheetHolder().getSheetName(), es);
        } else {
            map.get(analysisContext.readSheetHolder().getSheetName()).add(e);
        }
    }

    @SneakyThrows
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.debug("sheet-{}读取完毕", JSON.toJSONString(analysisContext.readSheetHolder().getSheetName()));
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        log.debug("解析到表头{}", JSON.toJSONString(headMap));
        this.setHeadMap(headMap);
    }
}
