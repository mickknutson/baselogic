package com.baselogic.tutorials.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.subethamail.wiser.Wiser;
//import org.subethamail.wiser.Wiser;

@Configuration
@Profile({"embedded", "dev-local"})
@SuppressWarnings("unused")
public class MailConfiguration {

	private static final Logger logger = LoggerFactory
            .getLogger(MailConfiguration.class);

    @Autowired
    private Environment environment;

    @Value("${smtp.host}")
    private String smtpHost;

    @Value("${smtp.port}")
    private String smtpPort;


    //TODO: Add Aspect to fire getSentMessages() when messages are sent.
    @Bean(initMethod="start", destroyMethod="stop")
    public Wiser wiserServer(){
        Wiser wiser = new Wiser();
        wiser.setHostname("localhost");
        wiser.setPort(2500);

        return wiser;
    }

    /*@Bean(initMethod="postConstruct", destroyMethod="destroy")
    public EmbeddedSmtpServer wiserServer(){
        EmbeddedSmtpServer embeddedSmtpServer = new EmbeddedSmtpServer();

        return embeddedSmtpServer;
    }*/
}
