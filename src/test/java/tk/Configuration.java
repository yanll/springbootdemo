package tk;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@Slf4j
public class Configuration {

    @Autowired
    Environment environment;


    @Bean
    public DataSourceConfig dataSourceConfig() {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(environment.getProperty("spring.datasource.url"));
        dsc.setUsername(environment.getProperty("spring.datasource.username"));
        dsc.setPassword(environment.getProperty("spring.datasource.password"));
        dsc.setDriverName(environment.getProperty("spring.datasource.driver-class-name"));
        return dsc;
    }

}
