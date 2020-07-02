package tk.techforge.springdemo.configuration;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.WebAppRootListener;

import javax.servlet.ServletContext;

/**
 * Created by YANLL on 2018/03/06.
 */
@Configuration
public class WebAppRootContext extends SpringBootServletInitializer {


    @Override
    public void onStartup(ServletContext servletContext) {
        servletContext.addListener(WebAppRootListener.class);
    }

}
