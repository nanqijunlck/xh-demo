package com.fqyc.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fqyc.demo.dto.SaleOrderPageQueryDTO;
import com.fqyc.demo.dto.SaleOrderPageRspDTO;
import com.fqyc.demo.dto.base.PageDTO;
import com.fqyc.demo.entity.ProductQuestion;
import com.fqyc.demo.entity.SaleOrder;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 首页访问Service
 *
 * @author panyi
 * @date 2020-03-16 16:02
 * @since 1.0
 */
public interface SaleOrderService extends IService<SaleOrder> {


    Boolean importSaleOrder(MultipartFile excelFile, HttpServletResponse response) throws Exception;

    PageDTO<SaleOrderPageRspDTO> pageQuery(SaleOrderPageQueryDTO queryDTO);
}
