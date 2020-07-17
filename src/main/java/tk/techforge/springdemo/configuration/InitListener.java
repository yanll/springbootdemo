package tk.techforge.springdemo.configuration;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import java.util.Map;

/**
 * Created by YANLL on 2018/03/06.
 */
@Configuration
@Slf4j
public class InitListener extends org.springframework.web.context.ContextLoaderListener {
    protected static final Logger logger = LoggerFactory.getLogger(InitListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ApplicationContext app = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        Map m = app.getBeansWithAnnotation(RequestMapping.class);
        logger.info("init components!");

    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        logger.info("destory components!");
        super.contextDestroyed(event);
    }
}