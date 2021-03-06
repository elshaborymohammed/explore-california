package com.smart.california;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class ExploreCaliforniaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExploreCaliforniaApplication.class, args);
    }

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.smart.california"))
                .build();
//                .apiInfo(new ApiInfo("Explore California API's",
//                        "API's for the Explore California Travel Service", "2.0", null,
//                        new Contact("LinkedIn Learning", "https://www.linkedin.com/learning", ""),
//                        null, null, Collections.emptyList()));
    }
}