package tk.techforge.springdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * Created by breez on 2016/03/30.
 */

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = {}, basePackages = {"tk.techforge.springdemo"})
@Slf4j
public class SBDApplication {
    public static void main(String[] args) {
        SpringApplication.run(SBDApplication.class, args);
    }
}