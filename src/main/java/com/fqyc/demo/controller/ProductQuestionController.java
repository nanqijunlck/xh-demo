package com.fqyc.demo.controller;

import com.fqyc.demo.controller.base.BaseController;
import com.fqyc.demo.dto.ProductQuestionReqDTO;
import com.fqyc.demo.dto.QualityOrderAddReqDTO;
import com.fqyc.demo.dto.QualityOrderRequestDTO;
import com.fqyc.demo.dto.ScanQueryRspDTO;
import com.fqyc.demo.dto.base.PageDTO;
import com.fqyc.demo.dto.base.ResponseBase;
import com.fqyc.demo.entity.ProductQuestion;
import com.fqyc.demo.entity.QualityOrder;
import com.fqyc.demo.entity.UserInfo;
import com.fqyc.demo.service.ProductQuestionService;
import com.fqyc.demo.service.QualityOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author LY
 */
@RestController
@RequestMapping("/${api.version}/question")
@Slf4j
@Api(value = "质检故障控制器", produces = "application/json", tags = {"质检故障"})
public class ProductQuestionController extends BaseController {

    @Resource
    private ProductQuestionService productQuestionService;

    @ApiOperation("add/update质检故障")
    @PostMapping("/addOrUpdate")
    public ResponseBase<Boolean> addOrUpdate(@RequestBody ProductQuestionReqDTO requestDTO) {
        log.debug("新增质检单，requestDTO={}", requestDTO);
        Boolean aBoolean = productQuestionService.addOrUpdate(requestDTO);
        return super.generateSuccess(aBoolean);
    }

    @ApiOperation("add/update质检故障")
    @PostMapping("/addOrUpdateRepair")
    public ResponseBase<Boolean> addOrUpdateRepair(@RequestBody ProductQuestionReqDTO requestDTO) {
        log.debug("新增质检单，requestDTO={}", requestDTO);
        Boolean aBoolean = productQuestionService.addOrUpdate(requestDTO);
        return super.generateSuccess(aBoolean);
    }

    @ApiOperation("删除")
    @GetMapping("/delete")
    public ResponseBase<Boolean> deleteQuestion(@RequestParam Integer id) {
        log.debug("删除质检单，requestDTO={}", id);
        Boolean aBoolean = productQuestionService.deleteQuestion(id);
        return super.generateSuccess(aBoolean);
    }

    @ApiOperation("质检故障查询")
    @PostMapping("/pageQuery")
    public ResponseBase<PageDTO<ProductQuestion>> pageQuery(@RequestBody ProductQuestionReqDTO requestDTO) {
        log.debug("质检故障查询，requestDTO={}", requestDTO);
        PageDTO<ProductQuestion> pageQuery = productQuestionService.pageQuery(requestDTO);
        return super.generateSuccess(pageQuery);
    }
//    @ApiOperation("质检故障查询")
//    @PostMapping("/pageQuery")
//    public ResponseBase<PageDTO<ProductQuestion>> pageQuery(@RequestBody ProductQuestionReqDTO requestDTO) {
//        log.debug("质检故障查询，requestDTO={}", requestDTO);
//        PageDTO<ProductQuestion> pageQuery = productQuestionService.pageQuery(requestDTO);
//        return super.generateSuccess(pageQuery);
//    }
}
