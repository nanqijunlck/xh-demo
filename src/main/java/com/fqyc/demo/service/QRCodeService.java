package com.fqyc.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fqyc.demo.entity.QrCodeOrder;

import java.awt.image.BufferedImage;

/**
 * 首页访问Service
 *
 * @author panyi
 * @date 2020-03-16 16:02
 * @since 1.0
 */
public interface QRCodeService extends IService<QrCodeOrder> {
    BufferedImage getQRCode();
}
