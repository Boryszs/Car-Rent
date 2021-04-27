package com.Server.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;
/**
 *   This class SwaggerConfig is use to API documentation.
 *   @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 *   @version 2.0.
 *   @since 2020-04-27.
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * This method postsApi is used to configuration swagger library
     * @return Docket
     */
    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
                .apiInfo(apiInfo()).select().paths(postPaths()).build()
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(basicAuthScheme()));
    }

    /**
     * This method postsApi is used to configuration swagger library
     * @return Predicate String
     */
    private Predicate<String> postPaths() {
        return or(regex("/test/"),
                regex("/admin/")
                ,regex("/car/")
                ,regex("/city/")
                ,regex("/reservation/")
                ,regex("/user/")
                ,regex("/authentication")
                ,regex("/register"));
    }

    /**
     * This method postsApi is used to configuration swagger library
     * @return Date to swagger documentation
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("JavaInUse API")
                .description("JavaInUse API reference for developers")
                .termsOfServiceUrl("http://javainuse.com")
                .contact("javainuse@gmail.com").license("JavaInUse License")
                .licenseUrl("javainuse@gmail.com").version("1.0").build();
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Arrays.asList(basicAuthReference()))
                .forPaths(PathSelectors.ant("/api/v1/**"))
                .build();
    }

    private SecurityScheme basicAuthScheme() {
        return new BasicAuth("basicAuth");
    }

    private SecurityReference basicAuthReference() {
        return new SecurityReference("basicAuth", new AuthorizationScope[0]);
    }
}
