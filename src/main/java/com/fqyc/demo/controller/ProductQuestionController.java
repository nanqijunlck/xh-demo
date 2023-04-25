package com.fqyc.demo.controller;

import com.fqyc.demo.controller.base.BaseController;
import com.fqyc.demo.dto.ProductQuestionReqDTO;
import com.fqyc.demo.dto.RepairProductQuestionReqDTO;
import com.fqyc.demo.dto.base.PageDTO;
import com.fqyc.demo.dto.base.ResponseBase;
import com.fqyc.demo.entity.ProductQuestion;
import com.fqyc.demo.entity.ProductQuestionRepair;
import com.fqyc.demo.service.ProductQuestionRepairService;
import com.fqyc.demo.service.ProductQuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    @Resource
    private ProductQuestionRepairService productQuestionRepairService;

    @ApiOperation("add/update质检故障")
    @PostMapping("/addOrUpdate")
    public ResponseBase<Boolean> addOrUpdate(@RequestBody ProductQuestionReqDTO requestDTO) {
        log.debug("add/update质检故障，requestDTO={}", requestDTO);
        Boolean aBoolean = productQuestionService.addOrUpdate(requestDTO);
        return super.generateSuccess(aBoolean);
    }

    @ApiOperation("add/update维修")
    @PostMapping("/addOrUpdateRepair")
    public ResponseBase<Boolean> addOrUpdateRepair(@RequestBody RepairProductQuestionReqDTO requestDTO) {
        log.debug("add/update维修，requestDTO={}", requestDTO);
        Boolean aBoolean = productQuestionRepairService.addOrUpdateRepair(requestDTO);
        return super.generateSuccess(aBoolean);
    }

    @ApiOperation("删除")
    @GetMapping("/delete")
    public ResponseBase<Boolean> deleteQuestion(@RequestParam Integer id) {
        log.debug("删除质检单，requestDTO={}", id);
        Boolean aBoolean = productQuestionService.deleteQuestion(id);
        return super.generateSuccess(aBoolean);
    }
    @ApiOperation("删除")
    @GetMapping("/deleteRepair")
    public ResponseBase<Boolean> deleteRepair(@RequestParam Integer id) {
        log.debug("删除维修记录，requestDTO={}", id);
        Boolean aBoolean = productQuestionRepairService.deleteQuestion(id);
        return super.generateSuccess(aBoolean);
    }

    @ApiOperation("质检故障查询")
    @PostMapping("/pageQuery")
    public ResponseBase<PageDTO<ProductQuestion>> pageQuery(@RequestBody ProductQuestionReqDTO requestDTO) {
        log.debug("质检故障查询，requestDTO={}", requestDTO);
        PageDTO<ProductQuestion> pageQuery = productQuestionService.pageQuery(requestDTO);
        return super.generateSuccess(pageQuery);
    }

    @ApiOperation("维修查询")
    @PostMapping("/repairPageQuery")
    public ResponseBase<PageDTO<ProductQuestionRepair>> repairPageQuery(@RequestBody RepairProductQuestionReqDTO requestDTO) {
        log.debug("维修查询，requestDTO={}", requestDTO);
        PageDTO<ProductQuestionRepair> pageQuery = productQuestionRepairService.repairPageQuery(requestDTO);
        return super.generateSuccess(pageQuery);
    }

    @ApiOperation("根据角色查询故障编码")
    @GetMapping("/queryQuestionByRoleCode")
    public ResponseBase<List<ProductQuestion>> queryQuestionByRoleCode(@RequestParam("roleCode") String roleCode) {
        log.debug("根据角色查询故障编码，roleCode={}", roleCode);
        List<ProductQuestion> list = productQuestionService.queryQuestionByRoleCode(roleCode);
        return super.generateSuccess(list);
    }
}
