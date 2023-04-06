package com.fqyc.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fqyc.demo.dto.ProductQuestionReqDTO;
import com.fqyc.demo.dto.QrCodeOrderDownloadReqDTO;
import com.fqyc.demo.dto.QrCodeOrderReqDTO;
import com.fqyc.demo.dto.base.PageDTO;
import com.fqyc.demo.entity.ProductQuestion;
import com.fqyc.demo.entity.QrCodeOrder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 首页访问Service
 *
 * @author panyi
 * @date 2020-03-16 16:02
 * @since 1.0
 */
public interface QrCodeOrderService extends IService<QrCodeOrder> {

    PageDTO<QrCodeOrder> pageQuery(QrCodeOrderReqDTO requestDTO);

    void downloadPdf(QrCodeOrderDownloadReqDTO reqDTO, HttpServletResponse response);
}
