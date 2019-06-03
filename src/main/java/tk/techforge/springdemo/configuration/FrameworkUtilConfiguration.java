package tk.techforge.springdemo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        WebAppRootContext.class,
        InteceptorConfiguration.class,
        DataSourceConfiguration.class,
        InitBeanConfiguration.class
})
public class FrameworkUtilConfiguration {

}
