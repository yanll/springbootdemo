package tk.techforge.springdemo.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;

/**
 * Created by YANLL on 2018/03/06.
 */
public class InitListener extends org.springframework.web.context.ContextLoaderListener {
    protected static final Logger logger = LoggerFactory.getLogger(InitListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {
        logger.info("init components!");
        String appname = event.getServletContext().getInitParameter("soa_app_name");
        logger.info(appname + " contextInitialized.");

    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        logger.info("destory components!");
        try {
        } catch (Throwable e) {
            logger.error("destor components error : " + e.getMessage(), e);
        }
        super.contextDestroyed(event);
    }
}