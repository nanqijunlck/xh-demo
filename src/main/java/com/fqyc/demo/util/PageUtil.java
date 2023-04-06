package com.fqyc.demo.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fqyc.demo.dto.base.PageDTO;

import java.util.List;

/**
 * @author QYP
 * @date 2021/6/18 10:16
 */

public class PageUtil {

    public static PageDTO getPageDTO(IPage iPage, List list){
        PageDTO<Object> pageDTO = new PageDTO<>();
        pageDTO.setPageSize((int)iPage.getSize());
        pageDTO.setCurrentPage((int)iPage.getCurrent());
        pageDTO.setTotalPage((int)iPage.getPages());
        pageDTO.setTotalCount((int)iPage.getTotal());
        pageDTO.setData(list);
        return pageDTO;
    }
}
