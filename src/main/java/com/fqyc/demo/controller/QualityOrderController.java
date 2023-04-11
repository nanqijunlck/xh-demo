package com.fqyc.demo.controller;

import com.fqyc.demo.controller.base.BaseController;
import com.fqyc.demo.dto.QualityOrderAddReqDTO;
import com.fqyc.demo.dto.QualityOrderRequestDTO;
import com.fqyc.demo.dto.ScanQueryRspDTO;
import com.fqyc.demo.dto.base.AppResponseBase;
import com.fqyc.demo.dto.base.PageDTO;
import com.fqyc.demo.dto.base.ResponseBase;
import com.fqyc.demo.entity.QualityOrder;
import com.fqyc.demo.entity.UserInfo;
import com.fqyc.demo.service.QualityOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author LY
 */
@RestController
@RequestMapping("/${api.version}/qualityOrder")
@Slf4j
@Api(value = "质检单控制器", produces = "application/json", tags = {"质检单管理"})
public class QualityOrderController extends BaseController {

    @Resource
    private QualityOrderService qualityService;

    @ApiOperation("新增质检记录单")
    @PostMapping("/add")
    public AppResponseBase<Boolean> add(@RequestBody QualityOrderAddReqDTO requestDTO) {
        log.info("新增质检单，requestDTO={}", requestDTO);
        UserInfo loginUserInfo = this.getLoginUserInfo();
        Assert.notNull(loginUserInfo, "未登陆，请先登陆");
        Boolean aBoolean = qualityService.addQualityOrder(requestDTO, loginUserInfo);
        AppResponseBase<Boolean> base = AppResponseBase.success();
        base.setData(aBoolean);
        return base;
    }

    @ApiOperation("分页查询")
    @PostMapping("/pageQuery")
    public ResponseBase<PageDTO<QualityOrder>> pageQuery(@Valid @RequestBody QualityOrderRequestDTO requestDTO) {
        log.info("新增质检单，requestDTO={}", requestDTO);
        PageDTO<QualityOrder> pageDTO = qualityService.queryListByPage(requestDTO);
        return super.generateSuccess(pageDTO);
    }

    @ApiOperation("扫码查询")
    @GetMapping("/scanQueryList")
    public AppResponseBase<ScanQueryRspDTO> scanQueryList(@RequestParam("qrCode") String qrCode) {
        log.info("新增质检单，requestDTO={}", qrCode);
        UserInfo loginUserInfo = this.getLoginUserInfo();
        Assert.notNull(loginUserInfo, "请先登陆");
        ScanQueryRspDTO scanQueryRspDTO = qualityService.scanQueryList(qrCode, loginUserInfo);
        AppResponseBase<ScanQueryRspDTO> success = AppResponseBase.success();
        success.setData(scanQueryRspDTO);
        return success;
    }

    @ApiOperation("下载质检记录")
    @PostMapping("/download")
    public void download(@RequestBody QualityOrderRequestDTO requestDTO, HttpServletResponse response) {
        log.info("下载质检单，requestDTO={}", requestDTO);
        qualityService.downloadQuery(requestDTO, response);
        return;
    }
}
