package com.fqyc.demo.controller.base;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.fqyc.demo.dto.base.ErrorType;
import com.fqyc.demo.dto.base.PageDTO;
import com.fqyc.demo.dto.base.ResponseBase;
import com.fqyc.demo.exception.WarnException;
import com.fqyc.demo.util.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author lck
 * @Date 2022/12/6 21:20
 * @Version 1.0
 * @Desc
 */

public abstract class AbstractController {
    @Value("${api.version:1.0}")
    private String API_VERSION;
    @Value("${api.base-info-version:1.0}")
    private String API_BASE_INFO_VERSION;

    public int hashCode()
    {
        int PRIME = 59;int result = 1;Object $API_VERSION = getAPI_VERSION();result = result * 59 + ($API_VERSION == null ? 43 : $API_VERSION.hashCode());Object $API_BASE_INFO_VERSION = getAPI_BASE_INFO_VERSION();result = result * 59 + ($API_BASE_INFO_VERSION == null ? 43 : $API_BASE_INFO_VERSION.hashCode());return result;
    }

    protected boolean canEqual(Object other)
    {
        return other instanceof AbstractController;
    }

    public boolean equals(Object o)
    {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AbstractController)) {
            return false;
        }
        AbstractController other = (AbstractController)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Object this$API_VERSION = getAPI_VERSION();Object other$API_VERSION = other.getAPI_VERSION();
        if (this$API_VERSION == null ? other$API_VERSION != null : !this$API_VERSION.equals(other$API_VERSION)) {
            return false;
        }
        Object this$API_BASE_INFO_VERSION = getAPI_BASE_INFO_VERSION();Object other$API_BASE_INFO_VERSION = other.getAPI_BASE_INFO_VERSION();return this$API_BASE_INFO_VERSION == null ? other$API_BASE_INFO_VERSION == null : this$API_BASE_INFO_VERSION.equals(other$API_BASE_INFO_VERSION);
    }

    public void setAPI_BASE_INFO_VERSION(String API_BASE_INFO_VERSION)
    {
        this.API_BASE_INFO_VERSION = API_BASE_INFO_VERSION;
    }

    public void setAPI_VERSION(String API_VERSION)
    {
        this.API_VERSION = API_VERSION;
    }

    public String toString()
    {
        return "AbstractController(API_VERSION=" + getAPI_VERSION() + ", API_BASE_INFO_VERSION=" + getAPI_BASE_INFO_VERSION() + ")";
    }

    public String getAPI_VERSION()
    {
        return this.API_VERSION;
    }

    public String getAPI_BASE_INFO_VERSION()
    {
        return this.API_BASE_INFO_VERSION;
    }

    protected void checkValid(BindingResult result)
    {
        if (result.hasErrors())
        {
            String message = result.getFieldError().getDefaultMessage();


            throw WarnException.builder().code(ErrorType.PARA_ERROR.getCode()).message(message).tipMessage(message).build();
        }
    }

    protected <T> ResponseBase<T> generateSuccess(T tObject)
    {
        ResponseBase<T> base = ResponseBase.success();
        base.setData(tObject);
        return base;
    }

    protected <T> ResponseBase<T> generateObjectSuccess(T tObject)
    {
        ResponseBase<T> base = ResponseBase.success();
        base.setData(tObject);
        return base;
    }

    protected ResponseBase generateBitSuccess(boolean res)
    {
        if (res) {
            return ResponseBase.success();
        }
        return ResponseBase.error(ErrorType.OPERATOR_ERROR);
    }

    protected ResponseBase generateLineSuccess(int line, int size)
    {
        if (size == 0)
        {
            if (line > size) {
                return ResponseBase.success();
            }
            return ResponseBase.error(ErrorType.OPERATOR_ERROR);
        }
        if (line == size) {
            return ResponseBase.success();
        }
        return ResponseBase.error(ErrorType.OPERATOR_ERROR);
    }

    protected <T> ResponseBase<PageDTO<T>> generatePageDtoSuccess(int total, List<T> list)
    {
        ResponseBase<PageDTO<T>> base = ResponseBase.success();
        PageDTO<T> pageDTO = new PageDTO();
        pageDTO.setTotalCount(total);
        pageDTO.setData(list);
        base.setData(pageDTO);
        return base;
    }

    protected <T> ResponseBase<T> generateError(ErrorType type)
    {
        return ResponseBase.error(type);
    }

    protected <T> HttpServletResponse exportSheets(String sheetName, String fileName, List<T> list, Class<T> tclass, HttpServletResponse response)
    {
        ExportParams params1 = new ExportParams();

        params1.setSheetName(sheetName);

        Map<String, Object> dataMap1 = new HashMap();

        dataMap1.put("title", params1);

        dataMap1.put("entity", tclass);

        dataMap1.put("data", list);

        List<Map<String, Object>> sheetsList = new ArrayList();
        sheetsList.add(dataMap1);

        Workbook workbook = ExcelExportUtil.exportExcel(sheetsList, ExcelType.HSSF);
        if (workbook == null) {
            throw WarnException.builder().code(ErrorType.EXCEL_ROW_NULL.getCode()).tipMessage(ErrorType.EXCEL_ROW_NULL.getMsg()).build();
        }
        response.reset();

        String dateStr = fileName + "_" + DateUtil.getTodayString();

        response.setHeader("Content-Disposition", "attachment;filename=" + dateStr + ".xls");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        try
        {
            OutputStream output = response.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            workbook.write(bufferedOutPut);
            bufferedOutPut.flush();
            bufferedOutPut.close();
            output.close();
        }
        catch (IOException e)
        {
            throw WarnException.builder().code(ErrorType.EXCEL_ERROR.getCode()).tipMessage(ErrorType.EXCEL_ERROR.getMsg()).build();
        }
        return response;
    }
}
