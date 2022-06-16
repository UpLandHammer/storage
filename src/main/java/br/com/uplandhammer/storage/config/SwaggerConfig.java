package br.com.uplandhammer.storage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.uplandhammer.storage.http"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(buildApiInfo());
    }


    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title("Storage Files")
                .description("Set of apis responsible for managing file upload and download on amazon's S3")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .contact(new Contact("Bruno S Rodrigues", "https://github.com/UpLandHammer", "brunorodrigues.tec.info@gmail.com"))
                .build();
    }
}
