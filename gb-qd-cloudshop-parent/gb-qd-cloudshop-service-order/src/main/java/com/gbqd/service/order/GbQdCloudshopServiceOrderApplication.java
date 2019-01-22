package com.gbqd.service.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableHystrix
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.gbqd")
@MapperScan(basePackages = "com.gbqd.mapper")
public class GbQdCloudshopServiceOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(GbQdCloudshopServiceOrderApplication.class, args);
    }

}

