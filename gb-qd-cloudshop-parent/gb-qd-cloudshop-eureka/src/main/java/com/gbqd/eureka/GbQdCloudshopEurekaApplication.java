package com.gbqd.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class GbQdCloudshopEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GbQdCloudshopEurekaApplication.class, args);
    }

}

