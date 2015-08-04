package com.baselogic.tutorials.dao.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

@Configuration
@ComponentScan(basePackages =
        {
                "com.baselogic.tutorials.dao.configuration",
                "com.baselogic.tutorials"
        }
)
@PropertySource(value = {"classpath:test.properties"})
@Import(JdbcEmbeddedConfig.class)
public class JavaConfig {

    @Value("${location.id.us}")
    private String locationUS;


    /**
     * Note: If you don't want to use @PropertySource, and/or need more custom PropertySourcesPlaceholderConfigurer
     * configuration, you can create a @Bean as seen here.
     * @return
     * @throws java.io.IOException
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() throws IOException {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(Boolean.TRUE);
        propertySourcesPlaceholderConfigurer.setLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:*.properties"));
        return propertySourcesPlaceholderConfigurer;
    }

}
