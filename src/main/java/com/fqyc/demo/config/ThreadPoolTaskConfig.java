package com.fqyc.demo.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
//import java.util.concurrent.ThreadPoolExecutor;
//
//
///**
// * @author panyi
// */
//@Configuration
//@EnableAsync
//public class ThreadPoolTaskConfig {
//
///**
// *   默认情况下，在创建了线程池后，线程池中的线程数为0，当有任务来之后，就会创建一个线程去执行任务，
// *	当线程池中的线程数目达到corePoolSize后，就会把到达的任务放到缓存队列当中；
// *  当队列满了，就继续创建线程，当线程数量大于等于maxPoolSize后，开始使用拒绝策略拒绝
// */
//
//    /**
//     * 核心线程数（默认线程数）
//     */
//    @Value("${async-core-pool-size:20}")
//    private int corePoolSize;
//    /**
//     * 最大线程数
//     */
//    @Value("${async-max-pool-size:100}")
//    private int maxPoolSize;
//    /**
//     * 允许线程空闲时间（单位：默认为秒）
//     */
//    @Value("${async-keep-alive-time:10}")
//    private int keepAliveTime;
//    /**
//     * 缓冲队列大小
//     */
//    @Value("${async-queue-capacity:10000000}")
//    private int queueCapacity;
//    /**
//     * 线程池名前缀
//     */
//    private static final String THREAD_NAME_PREFIX = "Async-Service-";
//
//    /**
//     * bean的名称，默认为首字母小写的方法名
//     */
//    @Bean("taskExecutor")
//    public ThreadPoolTaskExecutor taskExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(corePoolSize);
//        executor.setMaxPoolSize(maxPoolSize);
//        executor.setQueueCapacity(queueCapacity);
//        executor.setKeepAliveSeconds(keepAliveTime);
//        executor.setThreadNamePrefix(THREAD_NAME_PREFIX);
//
//        // 线程池对拒绝任务的处理策略
//        // CallerRunsPolicy：由调用线程（提交任务的线程）处理该任务
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//        // 初始化
//        executor.initialize();
//        return executor;
//    }
//}
//
//
////此代码来自 https://blog.csdn.net/Muscleheng/article/details/81409672