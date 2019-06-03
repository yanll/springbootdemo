package tk.techforge.springdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        List<Parameter> pars = new ArrayList<Parameter>();
        if (true) {
            pars.add(new ParameterBuilder().name("X-Requested-With").modelRef(new ModelRef("string")).parameterType("header").required(false).defaultValue("XMLHttpRequest").build());
        }
        pars.add(new ParameterBuilder().name("token").modelRef(new ModelRef("string")).parameterType("header").required(false).defaultValue("m617f17f-djc2-4aja-bck0-71c90fge4f6f").build());
        return docket
                .globalOperationParameters(pars)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("tk.techforge.springdemo"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接口文档")
                .version("1.0.0")
                .description("<br>文档说明：</b>\n" +
                        "1、会话信息，Header中设置，服务端生成。\n" +
                        "2、\n" +
                        "\n")
                .contact(ApiInfo.DEFAULT_CONTACT)
                .build();
    }


}
