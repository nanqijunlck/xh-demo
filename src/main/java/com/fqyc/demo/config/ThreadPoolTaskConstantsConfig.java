package com.fqyc.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * 线程池常量配置
 * @author lck
 * @date 2020/4/2   15:10
 * @since 1.0
 */
@Component
@ConfigurationProperties(prefix = "thread")
@Data
public class ThreadPoolTaskConstantsConfig {

    /** 核心线程数（默认线程数） */
    private int corePoolSize = 20;
    /** 最大线程数 */
    private int maxPoolSize = 100;
    /** 允许线程空闲时间（单位：默认为秒） */
    private int keepAliveTime = 10;
    /** 缓冲队列大小 */
    private int queueCapacity = 200;
}