package tk.techforge.springdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import tk.techforge.springdemo.configuration.FrameworkUtilConfiguration;


/**
 * Created by breez on 2016/03/30.
 */

@Configuration
@EnableAutoConfiguration
@ComponentScan(
        basePackageClasses = {
                SwaggerConfig.class,
                FrameworkUtilConfiguration.class
        },
        basePackages = {"tk.techforge.springdemo.web"})
@Slf4j
public class SBDApplication {


    public static void main(String[] args) {
        SpringApplication.run(SBDApplication.class, args);
    }
}