package com.fqyc.demo.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fqyc.demo.dto.SaleOrderImportDTO;
import com.fqyc.demo.dto.SaleOrderPageQueryDTO;
import com.fqyc.demo.dto.SaleOrderPageRspDTO;
import com.fqyc.demo.dto.base.PageDTO;
import com.fqyc.demo.entity.QrCodeOrder;
import com.fqyc.demo.entity.SaleOrder;
import com.fqyc.demo.repository.SaleOrderRepository;
import com.fqyc.demo.service.QRCodeService;
import com.fqyc.demo.service.SaleOrderService;
import com.fqyc.demo.util.DateUtil;
import com.fqyc.demo.util.ExcelNewUtil;
import com.fqyc.demo.util.ModelConvertUtils;
import com.fqyc.demo.util.QRCodeNewUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author lck
 * @Date 2023/3/15 16:22
 * @Version 1.0
 * @Desc
 */
@Service
@Slf4j
public class SaleOrderServiceImpl extends ServiceImpl<SaleOrderRepository, SaleOrder> implements SaleOrderService {

    @Autowired
    private QRCodeService qrCodeService;

    private static final String QUANTITY_PREF = "00000";
    private static final String FILEPATH = "C://Users//Administrator//Desktop//计划单二维码//";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean importSaleOrder(MultipartFile excelFile, HttpServletResponse response) throws Exception {
        File imageFile = new File("C:\\tempImage");
        List<File> needDeleted = new ArrayList<>(16);
        try {
            List<QrCodeOrder> qrCodeOrderList = new ArrayList<>(16);
            List<SaleOrderImportDTO> list = ExcelNewUtil.readExcel(excelFile, SaleOrderImportDTO.class);
            //新增导入的订单
            if (!CollUtil.isEmpty(list)) {
                List<SaleOrder> saleOrderList = ModelConvertUtils.convertList(list, SaleOrder.class);
                this.saveBatch(saleOrderList);
                //当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
                if (imageFile.exists() || !imageFile.isDirectory()) {
                    imageFile.delete();
                }
                imageFile.mkdirs();

                //生成pdf文件
                File pdfFile = new File(FILEPATH);
                if (!pdfFile.exists() || !pdfFile.isDirectory()) {
                    pdfFile.mkdirs();
                }
                String fileName = saleOrderList.get(0).getOrderCode() + "-订单对应的二维码-" + DateUtil.getTodayYMDHMSString();
                //当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
                fileName = FILEPATH + fileName + ".pdf";
                File file = new File(fileName);
                // 第一步：创建一个document对象。
                Document document = new Document();
                document.setMargins(0, 0, 0, 0);
                // 第二步：
                // 创建一个PdfWriter实例，
                PdfWriter.getInstance(document, new FileOutputStream(file));
                // 第三步：打开文档。
                document.open();

                for (SaleOrder saleOrder : saleOrderList) {
                    for (int j = 1; j <= saleOrder.getQuantity(); j++) {
                        String dataString = saleOrder.getOrderCode() + "/" + saleOrder.getBenChangCode() + "/" + saleOrder.getMerchantCode() + "/" + saleOrder.getMerchantSpe() + "/" + saleOrder.getQuantity();
                        String quantityStr = QUANTITY_PREF.substring(0, 5 - String.valueOf(j).length()) + j;
                        String quantitySizeStr = QUANTITY_PREF.substring(0, 5 - String.valueOf(saleOrder.getQuantity()).length()) + saleOrder.getQuantity();
                        dataString = dataString + "/" + quantityStr + "(-" + quantitySizeStr + ")";
                        BufferedImage bufferedImage = QRCodeNewUtil.getQRCodeImage(dataString, false, "/" + quantityStr + "(" + quantitySizeStr + ")");
                        File image = new File(imageFile.getPath() + "/" + saleOrder.getOrderCode() + "-" + saleOrder.getBenChangCode() + "-" + saleOrder.getMerchantCode() + "-" + saleOrder.getMerchantSpe() + "-" + saleOrder.getQuantity() + "-" + j + ".png");
                        FileOutputStream outputStream = new FileOutputStream(image);
                        ImageIO.write(bufferedImage, "png", outputStream);
                        outputStream.close();
                        needDeleted.add(image);
                        qrCodeOrderList.add(QrCodeOrder.builder().qrCode(dataString).orderCode(saleOrder.getOrderCode()).benChangCode(saleOrder.getBenChangCode())
                                .merchantCode(saleOrder.getMerchantCode()).merchantSpe(saleOrder.getMerchantSpe()).quantity(saleOrder.getQuantity()).build());
                        // 第四步：在文档中增加图片。
                        Image img = Image.getInstance(image.getPath());
                        img.setAlignment(Image.ALIGN_CENTER);
                        img.scalePercent(100);
                        // 根据图片大小设置页面，一定要先设置页面，再newPage（），否则无效
                        document.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));
                        document.newPage();
                        document.add(img);
                    }
                }
                // 第五步：关闭文档。
                document.close();
                qrCodeService.saveBatch(qrCodeOrderList);
            }
            return true;
        } finally {
            needDeleted.forEach(file -> file.delete());
            imageFile.delete();
        }
    }

    @Override
    public PageDTO<SaleOrderPageRspDTO> pageQuery(SaleOrderPageQueryDTO queryDTO) {
        PageDTO<SaleOrderPageRspDTO> pageDTO = new PageDTO();
        Page page = new Page(queryDTO.getCurrentPage(), queryDTO.getPageSize());
        LambdaQueryWrapper<SaleOrder> queryWrapper = new QueryWrapper<SaleOrder>().lambda();
        queryWrapper.orderByDesc(SaleOrder::getUpdateTime);
        if (StringUtils.isNotEmpty(queryDTO.getOrderCode())) {
            queryWrapper.eq(SaleOrder::getOrderCode, queryDTO.getOrderCode());
        }
        if (StringUtils.isNotEmpty(queryDTO.getBenChangCode())) {
            queryWrapper.eq(SaleOrder::getBenChangCode, queryDTO.getBenChangCode());
        }
        if (StringUtils.isNotEmpty(queryDTO.getMerchantCode())) {
            queryWrapper.eq(SaleOrder::getMerchantCode, queryDTO.getMerchantCode());
        }
        if (StringUtils.isNotEmpty(queryDTO.getMerchantSpe())) {
            queryWrapper.eq(SaleOrder::getMerchantSpe, queryDTO.getMerchantSpe());
        }
        if (StringUtils.isNotEmpty(queryDTO.getStartTime())) {
            queryWrapper.gt(SaleOrder::getCreateTime, queryDTO.getStartTime());
        }
        if (StringUtils.isNotEmpty(queryDTO.getEndTime())) {
            queryWrapper.lt(SaleOrder::getCreateTime, queryDTO.getEndTime());
        }
        Page selectPage = this.baseMapper.selectPage(page, queryWrapper);
        pageDTO.setTotalCount((int) selectPage.getTotal());
        pageDTO.setData(selectPage.getRecords());
        return pageDTO;
    }
}
