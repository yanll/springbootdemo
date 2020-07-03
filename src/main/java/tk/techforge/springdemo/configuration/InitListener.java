package tk.techforge.springdemo.configuration;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContextEvent;

/**
 * Created by YANLL on 2018/03/06.
 */
@Configuration
@Slf4j
public class InitListener extends org.springframework.web.context.ContextLoaderListener {
    protected static final Logger logger = LoggerFactory.getLogger(InitListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {
        logger.info("init components!");

    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        logger.info("destory components!");
        super.contextDestroyed(event);
    }
}