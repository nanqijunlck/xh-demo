package com.fqyc.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fqyc.demo.dto.ProductQuestionReqDTO;
import com.fqyc.demo.dto.QrCodeOrderDownloadReqDTO;
import com.fqyc.demo.dto.QrCodeOrderReqDTO;
import com.fqyc.demo.dto.base.PageDTO;
import com.fqyc.demo.entity.ProductQuestion;
import com.fqyc.demo.entity.QrCodeOrder;
import com.fqyc.demo.entity.SaleOrder;
import com.fqyc.demo.repository.ProductQuestionRepository;
import com.fqyc.demo.repository.QrCodeOrderRepository;
import com.fqyc.demo.service.ProductQuestionService;
import com.fqyc.demo.service.QrCodeOrderService;
import com.fqyc.demo.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author lck
 * @Date 2023/3/15 16:22
 * @Version 1.0
 * @Desc
 */
@Service
@Slf4j
public class QrCodeOrderServiceImpl extends ServiceImpl<QrCodeOrderRepository, QrCodeOrder> implements QrCodeOrderService {

    @Override
    public PageDTO<QrCodeOrder> pageQuery(QrCodeOrderReqDTO requestDTO) {
        Page page = new Page(requestDTO.getCurrentPage(), requestDTO.getPageSize());
        LambdaQueryWrapper<QrCodeOrder> queryWrapper = new QueryWrapper().lambda();
        queryWrapper.orderByDesc(QrCodeOrder::getUpdateTime, QrCodeOrder::getOrderCode);
        if (StringUtils.isNotEmpty(requestDTO.getOrderCode())) {
            queryWrapper.eq(QrCodeOrder::getOrderCode, requestDTO.getOrderCode());
        }
        if (StringUtils.isNotEmpty(requestDTO.getQrCode())) {
            queryWrapper.eq(QrCodeOrder::getQrCode, requestDTO.getQrCode());
        }
        if (StringUtils.isNotEmpty(requestDTO.getMerchantCode())) {
            queryWrapper.eq(QrCodeOrder::getMerchantCode, requestDTO.getMerchantCode());
        }
        if (StringUtils.isNotEmpty(requestDTO.getBenChangCode())) {
            queryWrapper.eq(QrCodeOrder::getBenChangCode, requestDTO.getBenChangCode());
        }
        if (StringUtils.isNotEmpty(requestDTO.getMerchantSpe())) {
            queryWrapper.eq(QrCodeOrder::getMerchantSpe, requestDTO.getMerchantSpe());
        }
        Page selectPage = this.baseMapper.selectPage(page, queryWrapper);
        return ModelConvertUtils.convertPageDTO(selectPage, QrCodeOrder.class);
    }

    @Override
    public void downloadPdf(QrCodeOrderDownloadReqDTO requestDTO, HttpServletResponse response) {
        File file = null;
        List<File> needDeleted = new ArrayList<>(16);
        try {
            LambdaQueryWrapper<QrCodeOrder> queryWrapper = new QueryWrapper().lambda();
            queryWrapper.orderByDesc(QrCodeOrder::getCreateTime, QrCodeOrder::getOrderCode);
            if (StringUtils.isNotEmpty(requestDTO.getOrderCode())) {
                queryWrapper.eq(QrCodeOrder::getOrderCode, requestDTO.getOrderCode());
            }
            if (StringUtils.isNotEmpty(requestDTO.getQrcode())) {
                queryWrapper.like(QrCodeOrder::getQrCode, requestDTO.getQrcode());
            }
            if (StringUtils.isNotEmpty(requestDTO.getMerchantCode())) {
                queryWrapper.eq(QrCodeOrder::getMerchantCode, requestDTO.getMerchantCode());
            }
            if (StringUtils.isNotEmpty(requestDTO.getBenChangCode())) {
                queryWrapper.eq(QrCodeOrder::getBenChangCode, requestDTO.getBenChangCode());
            }
            if (StringUtils.isNotEmpty(requestDTO.getMerchantSpe())) {
                queryWrapper.eq(QrCodeOrder::getMerchantSpe, requestDTO.getMerchantSpe());
            }
            List<QrCodeOrder> qrCodeOrders = this.baseMapper.selectList(queryWrapper);
            if (CollectionUtils.isNotEmpty(qrCodeOrders)) {
                String property = System.getProperty("user.dir");
                file = new File(property + "/image");
                file.mkdirs();
                file.setExecutable(true);
                for (QrCodeOrder qrCodeOrder : qrCodeOrders) {
                    String dataString = qrCodeOrder.getQrCode();
                    String[] split = dataString.split("/");
                    String quantityStr = split[split.length - 1];
                    String[] split1 = quantityStr.split("\\(");
                    BufferedImage bufferedImage = QRCodeNewUtil.getQRCodeImage(dataString, false, quantityStr);
                    File image = new File(file.getPath() + "/" + qrCodeOrder.getOrderCode() + "-" + qrCodeOrder.getBenChangCode() + "-" + qrCodeOrder.getMerchantCode() + "-" + qrCodeOrder.getMerchantSpe() + "-" + quantityStr + "-" + Integer.valueOf(split1[0]) + ".png");
                    FileOutputStream outputStream = new FileOutputStream(image);
                    ImageIO.write(bufferedImage, "png", outputStream);
                    outputStream.close();
                    needDeleted.add(image);

                }
                PdfUtil.imagesToPdfByOs(DateUtil.getTodayYMDHMSString() + "订单对应的二维码", file.getPath(), response);
            }
        } catch (Exception e) {
            log.error("下载失败", e);
        } finally {
            needDeleted.forEach(file1 -> file1.delete());
            file.delete();
        }
        return;
    }
}
