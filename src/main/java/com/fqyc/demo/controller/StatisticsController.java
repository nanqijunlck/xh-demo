package com.fqyc.demo.controller;

import com.fqyc.demo.controller.base.BaseController;
import com.fqyc.demo.dto.QualityQuestionReqDTO;
import com.fqyc.demo.dto.QualityQuestionRspDTO;
import com.fqyc.demo.dto.QuestionStatisticsRspDTO;
import com.fqyc.demo.dto.base.PageDTO;
import com.fqyc.demo.dto.base.ResponseBase;
import com.fqyc.demo.service.QualityStatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 用户登录的处理controller，包括从企业微信管理后台和手机端小程序两个访问入口
 *
 * @author lck
 * @date 2020-03-16 15:36
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/${api.version}/statistics")
@Api(value = "数据统计控制器", produces = "application/json", tags = {"统计报表"})
public class StatisticsController extends BaseController {

    @Resource
    private QualityStatisticsService qualityStatisticsService;

    @ApiOperation("统计合格率百分比")
    @PostMapping("/roleQuestionStatics")
    public ResponseBase<PageDTO<QualityQuestionRspDTO>> roleQuestionStatics(@Valid @RequestBody QualityQuestionReqDTO reqDTO) {
        log.debug("统计合格率百分比，reqDTO={}", reqDTO);
        PageDTO<QualityQuestionRspDTO> pageDTO = qualityStatisticsService.roleQuestionStatics(reqDTO);
        return super.generateSuccess(pageDTO);
    }

    @ApiOperation("故障数据统计")
    @PostMapping("/questionCodeStatics")
    public ResponseBase<PageDTO<QuestionStatisticsRspDTO>> questionCodeStatics(@Valid @RequestBody QualityQuestionReqDTO reqDTO) {
        log.debug("故障数据统计，reqDTO={}", reqDTO);
        PageDTO<QuestionStatisticsRspDTO> pageDTO = qualityStatisticsService.questionCodeStatics(reqDTO);
        return super.generateSuccess(pageDTO);
    }
}