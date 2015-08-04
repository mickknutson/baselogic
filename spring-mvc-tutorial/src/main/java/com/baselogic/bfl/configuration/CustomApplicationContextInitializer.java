package com.baselogic.tutorials.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Custom Application Initializer
 */
public class CustomApplicationContextInitializer implements
        ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final Logger logger = LoggerFactory
            .getLogger(CustomApplicationContextInitializer.class);

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
//        CloudEnvironment env = new CloudEnvironment();
//        if (env.getInstanceInfo() != null) {
//            logger.info("Application running in cloud. API '{}'",
//                    env.getCloudApiUri());
//            applicationContext.getEnvironment().setActiveProfiles("cloud");
//            applicationContext.refresh();
//        } else {
//            logger.info("Application running local");
//            applicationContext.getEnvironment().setActiveProfiles("dev");
//        }
    }
}
