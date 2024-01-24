package br.com.leomoreiradev.spring3.config;

import br.com.leomoreiradev.spring3.serialization.converter.YamlJackson2HttpMesageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    //Apenas para serializar em yaml
    private static final MediaType MEDIA_TYPE_APPLICATION_YML = MediaType.valueOf("applicaion/x-yaml");

    //usando cors no escopo global
    @Value("${cors.originPatterns}")
    private String corsOriginPatterns = "";

    /*configuração para negociação de conteudo para suportar o formato xml
    VIA QUERY PARAM localhost:8080/person/1?mediaType=xml
    * */
//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//     configurer.favorParameter(true)
//             .parameterName("mediaType")
//             .ignoreAcceptHeader(true)
//             .useRegisteredExtensionsOnly(false)
//             .defaultContentType(MediaType.APPLICATION_JSON)
//             .mediaType("json", MediaType.APPLICATION_JSON)
//             .mediaType("xml", MediaType.APPLICATION_XML);
//    }


    /*configuração para negociação de conteudo para suportar o formato xml
    VIA HEADER PARAM localhost:8080/person/1
    * */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorParameter(false)
                .ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("xml", MEDIA_TYPE_APPLICATION_YML);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
       converters.add(new YamlJackson2HttpMesageConverter());
    }

    //usando cors no escopo global
    @Override
    public void addCorsMappings(CorsRegistry registry) {
       var allowedOrigins = corsOriginPatterns.split(",");
       registry.addMapping("/**")
              // .allowedMethods("GET","POST") ou
              .allowedMethods("*")
               .allowedOrigins(allowedOrigins)
               .allowCredentials(true);
    }
}
