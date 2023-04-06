package com.fqyc.demo.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fqyc.demo.dto.QualityQuestionReqDTO;
import com.fqyc.demo.dto.QualityQuestionRspDTO;
import com.fqyc.demo.entity.QualityOrder;
import org.apache.ibatis.annotations.Param;

/**
 * @Author lck
 * @Date 2023/3/15 14:47
 * @Version 1.0
 * @Desc
 */
public interface QualityRepository extends BaseMapper<QualityOrder> {

    QualityQuestionRspDTO roleQuestionStatics(@Param("reqDTO") QualityQuestionReqDTO reqDTO);
}
