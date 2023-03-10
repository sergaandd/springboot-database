package shpp.mentor.springbootdatabase.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

    @Configuration
    @EnableSwagger2
    public class SwaggerConfig {

        @Value("${api.ver}")
        private String version;

        @Bean
        public Docket api(){
            return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("shpp.mentor.springbootdatabase.controllers"))
                    .paths(PathSelectors.any())
                    .build()
                    .apiInfo(new ApiInfoBuilder()
                            .title("H2 ToDo")
                            .description("H2 ToDo api")
                            .version(version).build());
        }
    }