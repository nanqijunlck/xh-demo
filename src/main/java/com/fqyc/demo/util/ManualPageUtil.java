package com.fqyc.demo.util;

import com.fqyc.demo.dto.base.PageDTO;

import java.util.Collections;
import java.util.List;

/**
 * @author hujun
 * @date 2021/4/16
 */
public class ManualPageUtil {

    /**
     * 分页
     * @param list
     * @param currentPage
     * @param pageSize
     * @param <T>
     * @return
     */
    public static <T> PageDTO<T> listToPage(List<T> list, int currentPage, int pageSize) {
        // 创建分页对象
        PageDTO<T> pageDTO = new PageDTO<>(currentPage, pageSize);
        if (list != null && list.size() > 0) {
            int count = list.size();
            pageDTO.setTotalCount(count);
            // 计算分页的页数
            int pageTotal = count % pageSize == 0 ? (count / pageSize) : (count / pageSize) + 1;
            pageDTO.setTotalPage(pageTotal);
            // 计算分页偏移量
            int offset = (pageDTO.getCurrentPage() - 1) * pageSize;
            // 截取数据
            if (offset < count) {
                if ((offset + pageSize) < count) {
                    pageDTO.setData(list.subList(offset, offset + pageSize));
                } else {
                    pageDTO.setData(list.subList(offset, count));
                }
            } else {
                pageDTO.setData(Collections.emptyList());
            }
            return pageDTO;
        }
        pageDTO.setTotalCount(0);
        pageDTO.setTotalPage(0);
        pageDTO.setData(Collections.emptyList());
        return pageDTO;
    }

}
