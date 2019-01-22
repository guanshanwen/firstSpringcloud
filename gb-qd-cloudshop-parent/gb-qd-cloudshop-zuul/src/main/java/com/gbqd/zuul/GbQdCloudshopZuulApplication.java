package com.gbqd.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RefreshScope
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableHystrix
public class GbQdCloudshopZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(GbQdCloudshopZuulApplication.class, args);
    }

}

