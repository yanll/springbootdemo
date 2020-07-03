package tk.techforge.springdemo;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * Created by breez on 2016/03/30.
 */

@Configuration
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackageClasses = {}, basePackages = {"tk.techforge.springdemo"})
@MapperScan(basePackages = {"tk.techforge.springdemo.modules.*.mapper"})
@Slf4j
public class SBDApplication {
    public static void main(String[] args) {
        SpringApplication.run(SBDApplication.class, args);
    }
}