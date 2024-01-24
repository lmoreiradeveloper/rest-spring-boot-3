package br.com.leomoreiradev.spring3.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    /*https://springdoc.org/#getting-started*/
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Restful API with Java and Spring Boot 3")
                        .version("v1")
                        .description("Some description about this API")
                        .termsOfService("http://terms.com")
                        .license(new License()
                                .name("Apche 2.0")
                                .url("http://license.com")));
    }

}
