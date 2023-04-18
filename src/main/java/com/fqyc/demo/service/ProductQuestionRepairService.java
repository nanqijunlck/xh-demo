package com.fqyc.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fqyc.demo.dto.ProductQuestionReqDTO;
import com.fqyc.demo.dto.RepairProductQuestionReqDTO;
import com.fqyc.demo.dto.base.PageDTO;
import com.fqyc.demo.entity.ProductQuestion;
import com.fqyc.demo.entity.ProductQuestionRepair;

import java.util.List;

/**
 * 首页访问Service
 *
 * @author lck
 * @date 2020-03-16 16:02
 * @since 1.0
 */
public interface ProductQuestionRepairService extends IService<ProductQuestionRepair> {

    Boolean deleteQuestion(Integer id);

    Boolean addOrUpdateRepair(RepairProductQuestionReqDTO requestDTO);

    PageDTO<ProductQuestionRepair> repairPageQuery(RepairProductQuestionReqDTO requestDTO);

    List<ProductQuestion> queryListByQuestionCode(String roleCode);
}
