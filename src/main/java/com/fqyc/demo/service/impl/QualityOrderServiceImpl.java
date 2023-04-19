package com.fqyc.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fqyc.demo.constants.ExceptionCodeConstants;
import com.fqyc.demo.dto.*;
import com.fqyc.demo.dto.base.PageDTO;
import com.fqyc.demo.entity.*;
import com.fqyc.demo.enums.QualityStatusEnum;
import com.fqyc.demo.enums.RoleCodeEnum;
import com.fqyc.demo.exception.BizException;
import com.fqyc.demo.repository.QualityRepository;
import com.fqyc.demo.service.*;
import com.fqyc.demo.util.DateUtil;
import com.fqyc.demo.util.ExcelUtil;
import com.fqyc.demo.util.ModelConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author lck
 * @Date 2023/3/15 16:22
 * @Version 1.0
 * @Desc
 */
@Service
@Slf4j
public class QualityOrderServiceImpl extends ServiceImpl<QualityRepository, QualityOrder> implements QualityOrderService {

    @Resource
    private UserRoleService userRoleService;
    @Resource
    private ProductQuestionService productQuestionService;
    @Resource
    private ProductQuestionRepairService productQuestionRepairService;
    @Resource
    private SaleOrderService saleOrderService;
    @Resource
    private QualityOrderService qualityOrderService;

    @Override
    public Boolean addQualityOrder(QualityOrderAddReqDTO requestDTO, UserInfo loginUserInfo) {
        // FIVE_SCAN_MACHINE update other add
        if (RoleCodeEnum.FIVE_SCAN_MACHINE.getCode().equals(loginUserInfo.getRoleCode())) {
            // update
            QualityOrder qualityOrderServiceOne = qualityOrderService.getOne(new QueryWrapper<QualityOrder>().lambda().eq(QualityOrder::getQrCode, requestDTO.getQrCode())
                    .eq(QualityOrder::getQualityStatus, QualityStatusEnum.ERROR.getCode()).orderByDesc(QualityOrder::getRoleCode).last("limit 1"));
            if (Objects.nonNull(qualityOrderServiceOne)) {
                qualityOrderServiceOne.setQualityStatus(QualityStatusEnum.SUCCESS.getCode());
                qualityOrderServiceOne.setRepairContent(requestDTO.getQuestionContent());
                qualityOrderService.updateById(qualityOrderServiceOne);
            }
            return true;
        } else {
            String[] split = requestDTO.getQrCode().split("/");
            String orderCode = split[0];
            SaleOrder saleOrder = saleOrderService.getOne(new QueryWrapper<SaleOrder>().lambda().eq(SaleOrder::getOrderCode, orderCode));
            QualityOrder qualityOrder = new QualityOrder();
            qualityOrder.setQrCode(requestDTO.getQrCode());
            qualityOrder.setRoleCode(loginUserInfo.getRoleCode());
            qualityOrder.setQuestionCode(requestDTO.getQuestionCode());
            qualityOrder.setQuestionContent(requestDTO.getQuestionContent());
            qualityOrder.setOrderCode(orderCode);
            qualityOrder.setBenChangCode(saleOrder.getBenChangCode());
            qualityOrder.setMerchantCode(saleOrder.getMerchantCode());
            qualityOrder.setMerchantSpe(saleOrder.getMerchantSpe());
            qualityOrder.setQuantity(saleOrder.getQuantity());
            qualityOrder.setQualityStatus(requestDTO.getQualityStatus());
            if (StringUtils.isEmpty(qualityOrder.getQuestionCode()) && StringUtils.isNotEmpty(qualityOrder.getQuestionContent())) {
                qualityOrder.setQuestionCode("手动编辑");
            }
            return this.baseMapper.insert(qualityOrder) > 0;
        }
    }

    @Override
    public PageDTO<QualityOrder> queryListByPage(QualityOrderRequestDTO requestDTO) {
        Page page = new Page(requestDTO.getCurrentPage(), requestDTO.getPageSize());
        LambdaQueryWrapper<QualityOrder> queryWrapper = new QueryWrapper().lambda();
        queryWrapper.orderByDesc(QualityOrder::getUpdateTime);
        if (StringUtils.isNotEmpty(requestDTO.getQrCode())) {
            queryWrapper.eq(QualityOrder::getQrCode, requestDTO.getQrCode());
        }
        if (StringUtils.isNotEmpty(requestDTO.getOrderCode())) {
            queryWrapper.eq(QualityOrder::getOrderCode, requestDTO.getOrderCode().trim());
        }
        if (StringUtils.isNotEmpty(requestDTO.getMerchantCode())) {
            queryWrapper.eq(QualityOrder::getMerchantCode, requestDTO.getMerchantCode());
        }
        if (StringUtils.isNotEmpty(requestDTO.getBenChangCode())) {
            queryWrapper.eq(QualityOrder::getBenChangCode, requestDTO.getBenChangCode());
        }
        if (StringUtils.isNotEmpty(requestDTO.getMerchantSpe())) {
            queryWrapper.eq(QualityOrder::getMerchantSpe, requestDTO.getMerchantSpe());
        }
        if (StringUtils.isNotEmpty(requestDTO.getQualityStatus())) {
            queryWrapper.eq(QualityOrder::getQualityStatus, requestDTO.getQualityStatus());
        }
        if (StringUtils.isNotEmpty(requestDTO.getQuestionCode())) {
            queryWrapper.eq(QualityOrder::getQuestionCode, requestDTO.getQuestionCode());
        }
        Page selectPage = this.baseMapper.selectPage(page, queryWrapper);
        return ModelConvertUtils.convertPageDTO(selectPage, QualityOrder.class);
    }

    @Override
    public ScanQueryRspDTO scanQueryList(String qrCode, UserInfo loginUserInfo) {
        ScanQueryRspDTO scanQueryRspDTO = ScanQueryRspDTO.builder().editFunction(true).qrCode(qrCode).build();
        String roleCode = loginUserInfo.getRoleCode();
        LambdaQueryWrapper<QualityOrder> queryWrapper = new QueryWrapper().lambda();
        queryWrapper.eq(QualityOrder::getQrCode, qrCode);
        queryWrapper.orderByAsc(QualityOrder::getQualityStatus, QualityOrder::getCreateTime);
        List<QualityOrder> qualityOrderList = this.baseMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(qualityOrderList) && !RoleCodeEnum.ONE_SCAN_MACHINE.getCode().equals(roleCode)) {
            throw new BizException(ExceptionCodeConstants.BIZ_ERR_CODE, "上一工位尚未完成");
        }
        List<QualityOrder> hasExistList = qualityOrderList.stream().filter(qualityOrder -> QualityStatusEnum.ERROR.getCode().equals(qualityOrder.getQualityStatus()) && roleCode.equals(qualityOrder.getRoleCode())).collect(Collectors.toList());
        if(CollectionUtils.isNotEmpty(hasExistList)){
            throw new BizException(ExceptionCodeConstants.BIZ_ERR_CODE, "已扫描过，请先维修");
        }
        scanQueryRspDTO.setQualityOrderList(qualityOrderList);
        // 不是第一次扫码
        if (CollectionUtils.isNotEmpty(qualityOrderList)) {
            UserRole roleInfoByCode = userRoleService.getRoleInfoByCode(roleCode);
            String beforeRoleCode = roleInfoByCode.getBeforeRoleCode();
            List<QualityOrder> collect = qualityOrderList.stream().filter(qualityOrder -> beforeRoleCode.equals(qualityOrder.getRoleCode()) && QualityStatusEnum.SUCCESS.getCode().equals(qualityOrder.getQualityStatus())).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(collect) && !RoleCodeEnum.FIVE_SCAN_MACHINE.getCode().equals(roleCode)) {
                // 1、前面序号的枪没有成功记录
                throw new BizException(ExceptionCodeConstants.BIZ_ERR_CODE, "上一工位尚未完成");
            }
        } else if (RoleCodeEnum.FIVE_SCAN_MACHINE.getCode().equals(roleCode)) {
            scanQueryRspDTO.setEditFunction(false);
            return scanQueryRspDTO;
        }
        List<ProductQuestion> productQuestionList = new ArrayList<ProductQuestion>();
        if (RoleCodeEnum.FIVE_SCAN_MACHINE.getCode().equals(roleCode)) {
            productQuestionList = productQuestionRepairService.queryListByQuestionCode(qualityOrderList.get(0).getQuestionCode());
        } else {
            productQuestionList = productQuestionService.queryListByRoleCode(roleCode);
        }
        scanQueryRspDTO.setProductQuestionList(productQuestionList);
        return scanQueryRspDTO;
    }

    @Override
    public List<QualityOrder> queryList(QualityQuestionReqDTO reqDTO) {
        LambdaQueryWrapper<QualityOrder> queryWrapper = new QueryWrapper().lambda();
        queryWrapper.orderByDesc(QualityOrder::getUpdateTime);
        // 默认半年时间
        if (StringUtils.isEmpty(reqDTO.getStartTime())) {
            reqDTO.setStartTime(DateUtil.getHalfYearDay());
        }
        if (StringUtils.isEmpty(reqDTO.getEndTime())) {
            reqDTO.setEndTime(DateUtil.format(new Date(), DateUtil.CN_YEAR_MONTH_DAY_FORMAT));
        }
        if (StringUtils.isNotEmpty(reqDTO.getQrCode())) {
            queryWrapper.eq(QualityOrder::getQrCode, reqDTO.getQrCode());
        }
        if (StringUtils.isNotEmpty(reqDTO.getMerchantCode())) {
            queryWrapper.eq(QualityOrder::getMerchantCode, reqDTO.getMerchantCode());
        }
        if (StringUtils.isNotEmpty(reqDTO.getStartTime())) {
            queryWrapper.ge(QualityOrder::getCreateTime, reqDTO.getStartTime());
        }
        if (StringUtils.isNotEmpty(reqDTO.getEndTime())) {
            queryWrapper.le(QualityOrder::getCreateTime, reqDTO.getEndTime());
        }
        if (StringUtils.isNotEmpty(reqDTO.getOrderCode())) {
            queryWrapper.le(QualityOrder::getOrderCode, reqDTO.getOrderCode());
        }
        if (StringUtils.isNotEmpty(reqDTO.getQualityStatus())) {
            queryWrapper.le(QualityOrder::getQualityStatus, reqDTO.getQualityStatus());
        }
        List<QualityOrder> list = this.list(queryWrapper);
        return list;
    }

    @Override
    public void downloadQuery(QualityOrderRequestDTO reqDTO, HttpServletResponse response) {
        try {
            LambdaQueryWrapper<QualityOrder> queryWrapper = new QueryWrapper().lambda();
            queryWrapper.orderByDesc(QualityOrder::getCreateTime).last("limit 10000");
            if (StringUtils.isNotEmpty(reqDTO.getQrCode())) {
                queryWrapper.eq(QualityOrder::getQrCode, reqDTO.getQrCode());
            }
            if (StringUtils.isNotEmpty(reqDTO.getMerchantCode())) {
                queryWrapper.eq(QualityOrder::getMerchantCode, reqDTO.getMerchantCode());
            }
            if (StringUtils.isNotEmpty(reqDTO.getOrderCode())) {
                queryWrapper.le(QualityOrder::getOrderCode, reqDTO.getOrderCode());
            }
            if (StringUtils.isNotEmpty(reqDTO.getBenChangCode())) {
                queryWrapper.le(QualityOrder::getBenChangCode, reqDTO.getBenChangCode());
            }
            if (StringUtils.isNotEmpty(reqDTO.getMerchantSpe())) {
                queryWrapper.le(QualityOrder::getMerchantSpe, reqDTO.getMerchantSpe());
            }
            List<QualityOrder> list = this.list(queryWrapper);
            if (CollectionUtils.isEmpty(list)) {
                throw new BizException(ExceptionCodeConstants.BIZ_ERR_CODE, "暂无数据");
            }
            List<QualityOrderDownloadRsp> qualityOrderDownloadRsp = ModelConvertUtils.convertList(list, QualityOrderDownloadRsp.class);
            String fileName = list.get(0).getOrderCode() + DateUtil.getTodayYMDHMSString();
            ExcelUtil.write(response, QualityOrderDownloadRsp.class, fileName, fileName, qualityOrderDownloadRsp);
        } catch (Exception e) {
            log.error("导出质检记录异常", e);
        }
    }
}
