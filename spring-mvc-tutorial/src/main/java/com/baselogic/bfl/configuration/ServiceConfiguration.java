package com.baselogic.tutorials.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Profile({"dev", "prod"})
@SuppressWarnings("unused")
public class ServiceConfiguration {

	private static final Logger logger = LoggerFactory
            .getLogger(ServiceConfiguration.class);

    @Autowired
    private Environment environment;

    /*@Bean
    public FactoryBean<RestTemplate> restTemplateFactoryBean() throws Exception{
        return new RestTemplateFactoryBean();
    }*/

    /*@Bean
    public RestTemplate projectRestService() throws Exception{
        RestTemplate restTemplate = restTemplateFactoryBean().getObject();

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new MappingJacksonHttpMessageConverter());
        //messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new StringHttpMessageConverter());
        restTemplate.setMessageConverters(messageConverters);

        return restTemplate;
    }*/


    /*@Bean
    public ProjectService projectService(){
        return new ProjectServiceImpl();
    }*/

    /*@Bean
    public SudRequestService sudRequestService(){
        return new SudRequestServiceImpl();
    }*/






//    @PostConstruct
//    public void postConstruct() {
//        logger.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//        logger.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//        logger.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//    }

}
