package tk.techforge.springdemo.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@Slf4j
public class DataSourceConfiguration {

    @Autowired
    private Environment env;


    @Bean(name = "dataSource")
    public DataSource dataSource() throws SQLException {
        log.info("开始初始化数据源");
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(env.getProperty("spring.datasource.url"));
        datasource.setUsername(env.getProperty("spring.datasource.username"));
        datasource.setPassword(env.getProperty("spring.datasource.password"));
        datasource.setDriverClassName(env.getProperty("spring.datasource. driver-class-name"));
        datasource.setMinIdle(5);
        datasource.setMaxActive(20);
        datasource.setTestWhileIdle(true);
        datasource.setTestOnBorrow(false);
        datasource.setTestOnReturn(false);
        datasource.setTimeBetweenEvictionRunsMillis(60);
        datasource.setValidationQuery("select 'x'");
        datasource.init();
        return datasource;
    }

}
