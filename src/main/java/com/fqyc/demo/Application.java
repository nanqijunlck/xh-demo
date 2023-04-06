package com.fqyc.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author panyi
 */
@SpringBootApplication(scanBasePackages = {"com.fqyc.demo.*"})
@EnableTransactionManagement
@MapperScan("com.fqyc.demo.repository")
@EntityScan("com.fqyc.demo")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
