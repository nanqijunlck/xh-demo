package com.fqyc.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fqyc.demo.constants.ExceptionCodeConstants;
import com.fqyc.demo.dto.ProductQuestionReqDTO;
import com.fqyc.demo.dto.RepairProductQuestionReqDTO;
import com.fqyc.demo.dto.base.PageDTO;
import com.fqyc.demo.entity.ProductQuestion;
import com.fqyc.demo.entity.ProductQuestionRepair;
import com.fqyc.demo.exception.BizException;
import com.fqyc.demo.repository.ProductQuestionRepairRepository;
import com.fqyc.demo.repository.ProductQuestionRepository;
import com.fqyc.demo.service.ProductQuestionRepairService;
import com.fqyc.demo.util.ModelConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Author lck
 * @Date 2023/3/15 16:22
 * @Version 1.0
 * @Desc
 */
@Service
@Slf4j
public class ProductQuestionRepairServiceImpl extends ServiceImpl<ProductQuestionRepairRepository, ProductQuestionRepair> implements ProductQuestionRepairService {

    @Override
    public Boolean deleteQuestion(Integer id) {
        int deleteById = this.baseMapper.deleteById(id);
        return deleteById > 0;
    }

    @Override
    public Boolean addOrUpdateRepair(RepairProductQuestionReqDTO requestDTO) {
        // 判断编码是否已存在
        if (Objects.nonNull(requestDTO.getId())) {
            ProductQuestionRepair productQuestionRepair = this.baseMapper.selectOne(new QueryWrapper<ProductQuestionRepair>().lambda().eq(ProductQuestionRepair::getQuestionCode, requestDTO.getQuestionCode()));
            if (Objects.nonNull(productQuestionRepair)) {
                throw new BizException(ExceptionCodeConstants.BIZ_ERR_CODE, "该编码已存在，请使用新的编码");
            }
        }
        // 判断编码是否已存在
        ProductQuestionRepair productQuestionRepair = this.baseMapper.selectOne(new QueryWrapper<ProductQuestionRepair>().lambda().eq(ProductQuestionRepair::getRepairQuestionContent, requestDTO.getRepairQuestionContent()));
        if (Objects.nonNull(productQuestionRepair)) {
            throw new BizException(ExceptionCodeConstants.BIZ_ERR_CODE, "该编码已存在，请使用新的编码");
        }
        ProductQuestionRepair productQuestion = ModelConvertUtils.convert(requestDTO, ProductQuestionRepair.class);
        boolean saveOrUpdate = this.saveOrUpdate(productQuestion);
        return saveOrUpdate;
    }

    @Override
    public PageDTO<ProductQuestionRepair> repairPageQuery(RepairProductQuestionReqDTO requestDTO) {
        Page page = new Page(requestDTO.getCurrentPage(), requestDTO.getPageSize());
        LambdaQueryWrapper<ProductQuestionRepair> queryWrapper = new QueryWrapper().lambda();
        queryWrapper.orderByDesc(ProductQuestionRepair::getUpdateTime, ProductQuestionRepair::getQuestionCode);
        if (StringUtils.isNotEmpty(requestDTO.getQuestionCode())) {
            queryWrapper.eq(ProductQuestionRepair::getQuestionCode, requestDTO.getQuestionCode());
        }
        if (StringUtils.isNotEmpty(requestDTO.getQuestionContent())) {
            queryWrapper.like(ProductQuestionRepair::getQuestionContent, requestDTO.getQuestionContent());
        }
        if (StringUtils.isNotEmpty(requestDTO.getRepairQuestionCode())) {
            queryWrapper.eq(ProductQuestionRepair::getRepairQuestionCode, requestDTO.getRepairQuestionCode());
        }
        Page selectPage = this.baseMapper.selectPage(page, queryWrapper);
        return ModelConvertUtils.convertPageDTO(selectPage, ProductQuestionRepair.class);
    }

    @Override
    public List<ProductQuestion> queryListByQuestionCode(String questionCode) {
        LambdaQueryWrapper<ProductQuestionRepair> queryWrapper = new QueryWrapper().lambda();
        queryWrapper.eq(ProductQuestionRepair::getQuestionCode, questionCode);
        queryWrapper.orderByAsc(ProductQuestionRepair::getRepairQuestionCode);
        List<ProductQuestionRepair> productQuestionList = this.baseMapper.selectList(queryWrapper);
        List<ProductQuestion> productQuestions = ModelConvertUtils.convertList(productQuestionList, ProductQuestion.class);
        return productQuestions;
    }
}
