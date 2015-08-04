package com.baselogic.tutorials.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import ru.xpoft.vaadin.VaadinMessageSource;

@Configuration
//@ImportResource({
//        "classpath:/applicationContext-ui.xml"
//})
@PropertySource(value= "classpath:ui.properties")
@SuppressWarnings("unused")
public class VaadinConfiguration implements ApplicationContextAware {

	private static final Logger logger = LoggerFactory
            .getLogger(VaadinConfiguration.class);

    @Autowired
    private Environment environment;

    @Autowired
    ApplicationContext applicationContext;

    @Bean
    public VaadinMessageSource vaadinMessageSource(){
        logger.info("*** VaadinConfiguration::vaadinMessageSource()");
        return new VaadinMessageSource();
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
