package com.fqyc.demo.util;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fqyc.demo.dto.base.PageDTO;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 字段相同的转换可以直接使用这个帮助类，对效率有要求则慎用
 */
@Slf4j
public class ModelConvertUtils {

    public static <ORIG, DEST> DEST convert(ORIG orig, Class<DEST> destClass) {
        try {
            if (orig == null) {
                return destClass.newInstance();
            }
            DEST dest = destClass.newInstance();
            BeanUtils.copyProperties(orig, dest);
            return dest;
        } catch (Exception e) {
            log.error("convert error=", e);
            return null;
        }
    }

    public static <ORIG, DEST> List<DEST> convertList(List<ORIG> orig, Class<DEST> destClass) {
        List<DEST> dests = Lists.newArrayList();
        if (orig == null) {
            return dests;
        }

        for (ORIG o : orig) {
            DEST d = convert(o, destClass);
            dests.add(d);
        }
        return dests;
    }

    /**
     * 把Mybatis的分页返回转化为starter里面的PageDTO，附带做了mybatis返回到自定义DTO的处理
     *
     * @param originPage mybatis plus的返回
     * @param destClass  自底应DTO
     * @param <ORIG>     初始泛型类型
     * @param <DEST>     返回泛型类型
     * @return PageDTO结果
     */
    public static <ORIG, DEST> PageDTO<DEST> convertPageDTO(IPage<ORIG> originPage, Class<DEST> destClass) {
        PageDTO<DEST> pageDTO = new PageDTO<DEST>();
        BeanUtils.copyProperties(originPage, pageDTO);
        pageDTO.setTotalCount(((Long) originPage.getTotal()).intValue());
        pageDTO.setCurrentPage(((Long) originPage.getCurrent()).intValue());
        pageDTO.setTotalPage(Integer.parseInt(Long.toString(originPage.getPages())));
        pageDTO.setPageSize(Integer.parseInt(Long.toString(originPage.getSize())));
        List<DEST> result = convertList(originPage.getRecords(), destClass);
        pageDTO.setData(result);
        return pageDTO;
    }

}