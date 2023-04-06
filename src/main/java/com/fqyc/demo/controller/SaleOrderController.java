package com.fqyc.demo.controller;

import com.fqyc.demo.constants.ExceptionCodeConstants;
import com.fqyc.demo.controller.base.BaseController;
import com.fqyc.demo.dto.SaleOrderPageQueryDTO;
import com.fqyc.demo.dto.SaleOrderPageRspDTO;
import com.fqyc.demo.dto.base.PageDTO;
import com.fqyc.demo.dto.base.ResponseBase;
import com.fqyc.demo.exception.BizException;
import com.fqyc.demo.exception.ExceptionCode;
import com.fqyc.demo.service.SaleOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.OLE2NotOfficeXmlFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 导出文件下载控制器
 *
 * @author liujianqiang
 * @create 2021-02-25 11:32
 **/
@RestController
@Api(value = "计划单控制器", produces = "application/json", tags = {"计划单管理"})
@RequestMapping("/${api.version}/saleOrder")
@Slf4j
public class SaleOrderController extends BaseController {

    @Autowired
    private SaleOrderService saleOrderService;
    @Autowired
    private ExceptionCode exceptionCode;

    @ApiOperation("导入计划单")
    @PostMapping("/importSaleOrder")
    public ResponseBase<Boolean> importSaleOrder(@RequestParam("excelFile") MultipartFile excelFile, HttpServletResponse response) {
        try {
            log.debug("计划单分页查询");
            Boolean importSaleOrder = saleOrderService.importSaleOrder(excelFile, response);
            return super.generateSuccess(importSaleOrder);
        } catch (OLE2NotOfficeXmlFileException e) {
            log.error("导入文件异常,文件格式错误", e);
            throw new BizException(ExceptionCodeConstants.EXCEL_TEMPLATE_ERROR, "不是合法的Excel文件，比如后缀被修改");
        } catch (DuplicateKeyException duplicateKeyException) {
            throw new BizException(ExceptionCodeConstants.BIZ_ERR_CODE, "有订单已经导入过，请勿重复导入");
        } catch (Exception e) {
            log.error("导入文件异常", e);
            throw new BizException(ExceptionCodeConstants.EXCEL_TEMPLATE_ERROR,
                    exceptionCode.getExceptionMsg(ExceptionCodeConstants.EXCEL_TEMPLATE_ERROR));
        }
    }

    @ApiOperation("计划单分页查询")
    @PostMapping("/pageQuery")
    public ResponseBase<PageDTO<SaleOrderPageRspDTO>> pageQuery(@Valid @RequestBody SaleOrderPageQueryDTO queryDTO) {
        log.debug("计划单分页查询");
        PageDTO<SaleOrderPageRspDTO> pageDTO = saleOrderService.pageQuery(queryDTO);
        return super.generateSuccess(pageDTO);
    }

}
