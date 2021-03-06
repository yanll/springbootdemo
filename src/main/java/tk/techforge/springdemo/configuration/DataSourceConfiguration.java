package tk.techforge.springdemo.configuration;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@Slf4j
public class DataSourceConfiguration {

    @Autowired
    private Environment env;


    @Autowired
    private ResourceLoader resourceLoader;


    @Bean(name = "dataSource")
    public DataSource dataSource() throws SQLException {
        log.info("开始初始化数据源");
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setMinimumIdle(5);
        dataSource.setMaximumPoolSize(20);
        dataSource.setMaxLifetime(20);
        dataSource.setPoolName("HikariDataSourcePool");
        dataSource.setConnectionInitSql("select 'x'");
        dataSource.getConnection();
        return dataSource;
    }


    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setVfs(SpringBootVFS.class);
        Properties properties = new Properties();
        properties.put("prefix", "");
        properties.put("blobType", "BLOB");
        properties.put("boolValue", "TRUE");
        sqlSessionFactoryBean.setConfigurationProperties(properties);
        Resource[] uia = ResourcePatternUtils.getResourcePatternResolver(resourceLoader).getResources("classpath*:mapper/*.xml");
        Resource[] flo = ResourcePatternUtils.getResourcePatternResolver(resourceLoader).getResources("classpath*:/META-INF/modeler-mybatis-mappings/*.xml");
        Resource[] res = new Resource[uia.length + flo.length];
        int i = 0;
        for (Resource r : uia) {
            res[i] = r;
            i++;
        }
        for (Resource r : flo) {
            res[i] = r;
            i++;
        }

        sqlSessionFactoryBean.setMapperLocations(res);
        sqlSessionFactoryBean.afterPropertiesSet();
        return sqlSessionFactoryBean.getObject();
    }

    @Bean("jdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Autowired DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }

    @Bean("namedParameterJdbcTemplate")
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(@Autowired DataSource dataSource) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        return namedParameterJdbcTemplate;
    }

}
