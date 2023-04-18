package com.fqyc.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fqyc.demo.entity.QrCodeOrder;
import com.fqyc.demo.repository.QrCodeOrderRepository;
import com.fqyc.demo.service.QRCodeService;
import com.fqyc.demo.util.QRCodeNewUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * @author lck
 * @date 2020-03-16 16:13
 * @since 1.0
 */
@Service
@Slf4j
public class QRCodeServiceImpl extends ServiceImpl<QrCodeOrderRepository, QrCodeOrder> implements QRCodeService {

    @Override
    public BufferedImage getQRCode() {
        String content = null;
        LambdaQueryWrapper<QrCodeOrder> queryWrapper = new QueryWrapper().lambda();
        queryWrapper.orderByDesc(QrCodeOrder::getQrCode).last("limit 1");
        QrCodeOrder qrCodeMachine = this.baseMapper.selectOne(queryWrapper);
        if (Objects.nonNull(qrCodeMachine)) {
            content = String.valueOf(Long.valueOf(qrCodeMachine.getQrCode()) + 1);
        }
        BufferedImage qrCodeImage = QRCodeNewUtil.getQRCodeImage(content, false, content);
        return qrCodeImage;
    }
}
