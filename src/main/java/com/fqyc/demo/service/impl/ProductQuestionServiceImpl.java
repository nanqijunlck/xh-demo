package com.fqyc.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fqyc.demo.dto.ProductQuestionReqDTO;
import com.fqyc.demo.dto.RepairProductQuestionReqDTO;
import com.fqyc.demo.dto.base.PageDTO;
import com.fqyc.demo.entity.ProductQuestion;
import com.fqyc.demo.repository.ProductQuestionRepository;
import com.fqyc.demo.service.ProductQuestionService;
import com.fqyc.demo.util.ModelConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author lck
 * @Date 2023/3/15 16:22
 * @Version 1.0
 * @Desc
 */
@Service
@Slf4j
public class ProductQuestionServiceImpl extends ServiceImpl<ProductQuestionRepository, ProductQuestion> implements ProductQuestionService {

    @Override
    public List<ProductQuestion> queryListByRoleCode(String roleCode, String productQuestionCode) {
        LambdaQueryWrapper<ProductQuestion> queryWrapper = new QueryWrapper().lambda();
        queryWrapper.eq(ProductQuestion::getRoleCode, roleCode);
        queryWrapper.orderByDesc(ProductQuestion::getUpdateTime);
        if(StringUtils.isNotEmpty(productQuestionCode)){
            queryWrapper.eq(ProductQuestion::getRepairQuestionCode, productQuestionCode);
        }
        List<ProductQuestion> productQuestionList = this.baseMapper.selectList(queryWrapper);
        return productQuestionList;
    }

    @Override
    public Boolean addOrUpdate(ProductQuestionReqDTO requestDTO) {
        ProductQuestion productQuestion = ModelConvertUtils.convert(requestDTO, ProductQuestion.class);
        boolean saveOrUpdate = this.saveOrUpdate(productQuestion);
        return saveOrUpdate;
    }

    @Override
    public Boolean deleteQuestion(Integer id) {
        int deleteById = this.baseMapper.deleteById(id);
        return deleteById > 0;
    }

    @Override
    public PageDTO<ProductQuestion> pageQuery(ProductQuestionReqDTO requestDTO) {
        Page page = new Page(requestDTO.getCurrentPage(), requestDTO.getPageSize());
        LambdaQueryWrapper<ProductQuestion> queryWrapper = new QueryWrapper().lambda();
        queryWrapper.eq(ProductQuestion::getQuestionType, 0);
        queryWrapper.orderByDesc(ProductQuestion::getUpdateTime, ProductQuestion::getQuestionCode);
        if (StringUtils.isNotEmpty(requestDTO.getQuestionCode())) {
            queryWrapper.eq(ProductQuestion::getQuestionCode, requestDTO.getQuestionCode());
        }
        if (StringUtils.isNotEmpty(requestDTO.getQuestionContent())) {
            queryWrapper.like(ProductQuestion::getQuestionContent, requestDTO.getQuestionContent());
        }
        if (StringUtils.isNotEmpty(requestDTO.getRoleCode())) {
            queryWrapper.eq(ProductQuestion::getRoleCode, requestDTO.getRoleCode());
        }
        Page selectPage = this.baseMapper.selectPage(page, queryWrapper);
        return ModelConvertUtils.convertPageDTO(selectPage, ProductQuestion.class);
    }

    @Override
    public Boolean addOrUpdateRepair(RepairProductQuestionReqDTO requestDTO) {
        ProductQuestion productQuestion = ModelConvertUtils.convert(requestDTO, ProductQuestion.class);
        productQuestion.setQuestionType(1);
        boolean saveOrUpdate = this.saveOrUpdate(productQuestion);
        return saveOrUpdate;
    }

    @Override
    public PageDTO<ProductQuestion> repairPageQuery(RepairProductQuestionReqDTO requestDTO) {
        Page page = new Page(requestDTO.getCurrentPage(), requestDTO.getPageSize());
        LambdaQueryWrapper<ProductQuestion> queryWrapper = new QueryWrapper().lambda();
        queryWrapper.eq(ProductQuestion::getQuestionType, 1);
        queryWrapper.orderByDesc(ProductQuestion::getUpdateTime, ProductQuestion::getQuestionCode);
        if (StringUtils.isNotEmpty(requestDTO.getQuestionCode())) {
            queryWrapper.eq(ProductQuestion::getQuestionCode, requestDTO.getQuestionCode());
        }
        if (StringUtils.isNotEmpty(requestDTO.getQuestionContent())) {
            queryWrapper.like(ProductQuestion::getQuestionContent, requestDTO.getQuestionContent());
        }
        if (StringUtils.isNotEmpty(requestDTO.getRoleCode())) {
            queryWrapper.eq(ProductQuestion::getRoleCode, requestDTO.getRoleCode());
        }
        Page selectPage = this.baseMapper.selectPage(page, queryWrapper);
        return ModelConvertUtils.convertPageDTO(selectPage, ProductQuestion.class);
    }
}
