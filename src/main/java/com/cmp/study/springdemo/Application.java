package com.cmp.study.springdemo;

import com.cmp.study.springdemo.configuration.FrameworkUtilConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by breez on 2016/03/30.
 */

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.cmp.study.springdemo.web", "com.cmp.study.springdemo.service"}, basePackageClasses = FrameworkUtilConfiguration.class)
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}