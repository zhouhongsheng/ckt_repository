package com.sioeye.sidecar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableFeignClients
@EnableSidecar
public class SideCarApp {
    public static void main(String[] args) {
        SpringApplication.run(SideCarApp.class, args);
        System.out.println("test sidecar server start successfully . ");
    }
}