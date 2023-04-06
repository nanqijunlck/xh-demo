package com.fqyc.demo.util;//package com.fqyc.quality.util;
//
//import cn.hutool.core.util.StrUtil;
////import com.alibaba.excel.metadata.CellData;
//import com.alibaba.excel.metadata.Head;
//import com.alibaba.excel.metadata.data.CellData;
//import com.alibaba.excel.write.handler.CellWriteHandler;
//import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
//import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
//import org.apache.poi.ss.usermodel.*;
//
//import java.util.List;
//
///**
// * @author lhs
// * @desc TODO
// * @date 2021/4/13 11:10
// * @since 1.0
// */
//public class ExcelCellStyleWriteHandler implements CellWriteHandler {
//
//    @Override
//    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Head head, Integer integer, Integer integer1, Boolean aBoolean) {
//
//    }
//
//    @Override
//    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell, Head head, Integer integer, Boolean aBoolean) {
//
//    }
//
//    @Override
//    public void afterCellDataConverted(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, CellData cellData, Cell cell, Head head, Integer integer, Boolean aBoolean) {
//
//    }
//
//    @Override
//    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<CellData> list, Cell cell, Head head, Integer integer, Boolean aBoolean) {
//        if (cell.getColumnIndex() == 3 && StrUtil.isBlank(cell.getStringCellValue())) {
//            Workbook workbook = writeSheetHolder.getSheet().getWorkbook();
//            CellStyle cellStyle = workbook.createCellStyle();
//
//            cellStyle.setFillBackgroundColor(FillPatternType.SOLID_FOREGROUND.getCode());
//
//            cell.setCellStyle(cellStyle);
//        }
//    }
//
//}
