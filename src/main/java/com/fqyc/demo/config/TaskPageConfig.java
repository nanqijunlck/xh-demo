package com.fqyc.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * task page配置
 * @author lck
 * @date 2020/4/2   15:10
 * @since 1.0
 */
@Component("taskPageConfig")
@ConfigurationProperties(prefix = "task")
@Data
public class TaskPageConfig {

    private Long pageSize;

    private String reviewIntervalTime;

    private String reviewDefaultIntervalTime;

}
