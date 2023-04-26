package com.fqyc.demo.service.impl;

import com.fqyc.demo.dto.QualityQuestionReqDTO;
import com.fqyc.demo.dto.QualityQuestionRspDTO;
import com.fqyc.demo.dto.QuestionStatisticsRspDTO;
import com.fqyc.demo.dto.base.PageDTO;
import com.fqyc.demo.entity.QualityOrder;
import com.fqyc.demo.enums.QualityStatusEnum;
import com.fqyc.demo.enums.RoleCodeEnum;
import com.fqyc.demo.service.QualityOrderService;
import com.fqyc.demo.service.QualityStatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author lck
 * @date 2020-03-16 16:13
 * @since 1.0
 */
@Service
@Slf4j
public class QualityStatisticsServiceImpl implements QualityStatisticsService {

    @Resource
    private QualityOrderService qualityOrderService;

    @Override
    public PageDTO<QualityQuestionRspDTO> roleQuestionStatics(QualityQuestionReqDTO reqDTO) {
        PageDTO<QualityQuestionRspDTO> pageDTO = new PageDTO();
        List<QualityOrder> qualityOrderList = qualityOrderService.queryList(reqDTO, false);
        if (CollectionUtils.isEmpty(qualityOrderList)) {
            return pageDTO;
        }
        Map<String, List<QualityOrder>> listMap = qualityOrderList.stream().collect(Collectors.groupingBy(QualityOrder::getOrderCode));

        List<QualityQuestionRspDTO> result = new ArrayList<>(listMap.size());
        for (Map.Entry<String, List<QualityOrder>> stringListEntry : listMap.entrySet()) {
            List<QualityOrder> mapKeyList = stringListEntry.getValue();
            QualityOrder qualityOrder1 = mapKeyList.get(0);
            if (CollectionUtils.isNotEmpty(stringListEntry.getValue())) {
                QualityQuestionRspDTO rspDTO = QualityQuestionRspDTO.builder()
                        .orderCode(qualityOrder1.getOrderCode())
                        .merchantCode(qualityOrder1.getMerchantCode())
                        .benChangCode(qualityOrder1.getBenChangCode())
                        .quantity(qualityOrder1.getQuantity())
                        .merchantCode(qualityOrder1.getMerchantCode())
                        .build();
                //一次性合格率 没有错误记录的
                List<QualityOrder> firstSucList = mapKeyList.stream().filter(qualityOrder -> StringUtils.isEmpty(qualityOrder.getRepairContent())
                        && QualityStatusEnum.SUCCESS.getCode().equals(qualityOrder.getQualityStatus())).collect(toList());
                Map<String, List<QualityOrder>> collect = firstSucList.stream().collect(Collectors.groupingBy(QualityOrder::getQrCode));
                int count = 0;
                for (Map.Entry<String, List<QualityOrder>> map : collect.entrySet()) {
                    if (map.getValue().size() == 4) {
                        count++;
                    }
                }
                BigDecimal noQuestionPercent = new BigDecimal(count).divide(new BigDecimal(qualityOrder1.getQuantity()), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
                rspDTO.setNoQuestionPercent(noQuestionPercent.toPlainString());
                //百分百合格率
                Map<String, List<QualityOrder>> stringListMap = mapKeyList.stream().filter(qualityOrder -> QualityStatusEnum.SUCCESS.getCode().equals(qualityOrder.getQualityStatus())
                        && RoleCodeEnum.FORE_SCAN_MACHINE.getCode().equals(qualityOrder.getRoleCode())).collect(Collectors.groupingBy(QualityOrder::getQrCode));
                BigDecimal lastSucPercent = new BigDecimal(stringListMap.size()).divide(new BigDecimal(qualityOrder1.getQuantity()), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
                rspDTO.setLastSuccessPercent(lastSucPercent.toPlainString());
                result.add(rspDTO);
            }
        }
        pageDTO.setTotalCount(result.size());
        pageDTO.setData(result);
        return pageDTO;
    }

    @Override
    public PageDTO<QuestionStatisticsRspDTO> questionCodeStatics(QualityQuestionReqDTO reqDTO) {
        PageDTO pageDTO = new PageDTO();
        Integer totalCount = 0;
        //  查询所有不通过的故障
        Boolean notNull = true;
        List<QualityOrder> qualityOrderList = qualityOrderService.queryList(reqDTO, notNull);
        List<QuestionStatisticsRspDTO> rspDTOList = new ArrayList<>(16);
        if (CollectionUtils.isNotEmpty(qualityOrderList)) {
            Map<String, List<QualityOrder>> questionMap = qualityOrderList.stream().collect(Collectors.groupingBy(QualityOrder::getQuestionCode));
            for (Map.Entry<String, List<QualityOrder>> map : questionMap.entrySet()) {
                String questionCode = map.getKey();
                String questionContent = StringUtils.join(map.getValue().stream().map(QualityOrder::getQuestionContent).distinct().collect(toList()), "/");
                Integer questionCount = map.getValue().size();
                totalCount += questionCount;
                rspDTOList.add(QuestionStatisticsRspDTO.builder().questionCode(questionCode).questionContent(questionContent).questionCount(questionCount).build());
            }
            for (QuestionStatisticsRspDTO questionStatisticsRspDTO : rspDTOList) {
                questionStatisticsRspDTO.setQuestionPercent(new BigDecimal(questionStatisticsRspDTO.getQuestionCount()).divide(new BigDecimal(String.valueOf(totalCount)), 4, BigDecimal.ROUND_HALF_UP)
                        .multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
            }
        }
        pageDTO.setTotalCount(rspDTOList.size());
        pageDTO.setData(rspDTOList);
        return pageDTO;
    }
}
