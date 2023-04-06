package com.fqyc.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fqyc.demo.dto.QualityOrderAddReqDTO;
import com.fqyc.demo.dto.QualityOrderRequestDTO;
import com.fqyc.demo.dto.QualityQuestionReqDTO;
import com.fqyc.demo.dto.ScanQueryRspDTO;
import com.fqyc.demo.dto.base.AppResponseBase;
import com.fqyc.demo.dto.base.PageDTO;
import com.fqyc.demo.entity.QualityOrder;
import com.fqyc.demo.entity.UserInfo;

import java.util.List;

/**
 * 首页访问Service
 *
 * @author panyi
 * @date 2020-03-16 16:02
 * @since 1.0
 */
public interface QualityOrderService extends IService<QualityOrder> {

    Boolean addQualityOrder(QualityOrderAddReqDTO requestDTO, UserInfo loginUserInfo);

    PageDTO<QualityOrder> queryListByPage(QualityOrderRequestDTO requestDTO);

    ScanQueryRspDTO scanQueryList(String qrCode, UserInfo loginUserInfo);

    List<QualityOrder> queryList(QualityQuestionReqDTO reqDTO);
}
