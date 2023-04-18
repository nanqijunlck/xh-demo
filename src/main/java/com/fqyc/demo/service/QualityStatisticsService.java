package com.fqyc.demo.service;

import com.fqyc.demo.dto.QualityQuestionReqDTO;
import com.fqyc.demo.dto.QualityQuestionRspDTO;
import com.fqyc.demo.dto.QuestionStatisticsRspDTO;
import com.fqyc.demo.dto.base.PageDTO;

/**
 * 首页访问Service
 *
 * @author lck
 * @date 2020-03-16 16:02
 * @since 1.0
 */
public interface QualityStatisticsService {
    PageDTO<QualityQuestionRspDTO> roleQuestionStatics(QualityQuestionReqDTO reqDTO);

    PageDTO<QuestionStatisticsRspDTO> questionCodeStatics(QualityQuestionReqDTO reqDTO);
}
