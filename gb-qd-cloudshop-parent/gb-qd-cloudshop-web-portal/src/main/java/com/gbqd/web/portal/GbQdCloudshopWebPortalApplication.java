package com.gbqd.web.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages={"com.gbqd"})
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = "com.gbqd")
public class GbQdCloudshopWebPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(GbQdCloudshopWebPortalApplication.class, args);
    }

}

