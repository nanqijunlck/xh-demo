package com.fqyc.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fqyc.demo.dto.ProductQuestionReqDTO;
import com.fqyc.demo.dto.RepairProductQuestionReqDTO;
import com.fqyc.demo.dto.base.PageDTO;
import com.fqyc.demo.entity.ProductQuestion;

import java.util.List;

/**
 * 首页访问Service
 *
 * @author lck
 * @date 2020-03-16 16:02
 * @since 1.0
 */
public interface ProductQuestionService extends IService<ProductQuestion> {

    List<ProductQuestion> queryListByRoleCode(String roleCode);

    Boolean addOrUpdate(ProductQuestionReqDTO requestDTO);

    Boolean deleteQuestion(Integer id);

    PageDTO<ProductQuestion> pageQuery(ProductQuestionReqDTO requestDTO);

    List<ProductQuestion> queryQuestionByRoleCode(String roleCode);
}
