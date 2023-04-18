package com.fqyc.demo.exception;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * 异常码配置类
 *
 * @author lck
 * @date 2020-03-19 10:37
 * @since 1.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "exception")
public class ExceptionCode {

    /**
     * 异常码和异常信息的Map
     * {10001=参数[%s]为空, 10002=参数异常[%s]}
     */
    private Map<String, String> maps;

    /**
     * 获取异常描述信息
     *
     * @param exceptionCode 异常编码，在yml文件中配置
     * @return exceptionMsg，当exceptionCode没有配置时返回空串
     */
    public String getExceptionMsg(String exceptionCode) {
        if (!CollectionUtils.isEmpty(maps)) {
            final String s = maps.get(exceptionCode);
            if (!StringUtils.isEmpty(s)) {
                return s;
            }
        }
        return "";
    }
}
