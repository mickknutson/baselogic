package com.baselogic.tutorials.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.core.env.Environment;

@Configuration
@EnableLoadTimeWeaving(aspectjWeaving= EnableLoadTimeWeaving.AspectJWeaving.ENABLED)
public class AspectConfiguration {

	private static final Logger logger = LoggerFactory
            .getLogger(AspectConfiguration.class);

    @Autowired
    private Environment environment;



}
