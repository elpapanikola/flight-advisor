package com.nikolanedeljkovic.flightadvisor;
import static com.google.common.collect.Lists.newArrayList;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * Filtering API to be included in Swagger's response/scope. 
	 * 
	 * @return
	 */
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.globalOperationParameters(
                        Collections.singletonList(new ParameterBuilder()
                            .name("Authorization")
                            .description("Access Token")
                            .modelRef(new ModelRef("string"))
                            .parameterType("header")
                            .defaultValue("Bearer ")
                            .required(false)
                            .build()))
                .select()
                .apis(RequestHandlerSelectors.basePackage(SwaggerConfig.class.getPackage().getName()))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData());
    }

    /**
     * Customization of default values in Swagger response.
     * 
     * @return
     */
    private ApiInfo metaData() {
        return new ApiInfo(
                "Flight Advisor",
                "Contains business logic for flight advisor related functionality",
                "1.0",
                "Terms of service",
                new Contact("", "", ""),
                "",
                "", 
                Collections.emptyList());
    }
    
}
