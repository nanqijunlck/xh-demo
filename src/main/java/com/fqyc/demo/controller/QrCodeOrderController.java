package com.fqyc.demo.controller;

import com.fqyc.demo.controller.base.BaseController;
import com.fqyc.demo.dto.QrCodeOrderDownloadReqDTO;
import com.fqyc.demo.dto.QrCodeOrderReqDTO;
import com.fqyc.demo.dto.base.PageDTO;
import com.fqyc.demo.dto.base.ResponseBase;
import com.fqyc.demo.entity.QrCodeOrder;
import com.fqyc.demo.service.QrCodeOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author LY
 */
@RestController
@RequestMapping("/${api.version}/qrCodeOrder")
@Slf4j
@Api(value = "二维码控制器", produces = "application/json", tags = {"二维码管理"})
public class QrCodeOrderController extends BaseController {

    @Resource
    private QrCodeOrderService qrCodeOrderService;

    @ApiOperation("二维码分页查询")
    @PostMapping("/pageQuery")
    public ResponseBase<PageDTO<QrCodeOrder>> pageQuery(@Valid @RequestBody QrCodeOrderReqDTO reqDTO) {
        log.debug("二维码分页查询，requestDTO={}", reqDTO);
        PageDTO<QrCodeOrder> qrCodeOrderPageDTO = qrCodeOrderService.pageQuery(reqDTO);
        return super.generateSuccess(qrCodeOrderPageDTO);
    }

    @ApiOperation("二维码查询下载")
    @PostMapping(value = "/downloadPdf")
    public Void downloadPdf(@RequestBody QrCodeOrderDownloadReqDTO reqDTO, HttpServletResponse response) {
        log.debug("二维码查询下载，requestDTO={}", reqDTO);
        qrCodeOrderService.downloadPdf(reqDTO, response);
        return null;
    }
}
