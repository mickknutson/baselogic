package com.baselogic.tutorials.configuration.dev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
//import org.subethamail.wiser.Wiser;
//import org.subethamail.wiser.WiserMessage;

import javax.annotation.PostConstruct;

/**
 * see: http://logback.qos.ch/xref-test/ch/qos/logback/classic/net/SMTPAppender_SubethaSMTPTest.html
 */
//@Component(value="embeddedSmtpServer")
//@Profile("embedded")
public class EmbeddedSmtpServer implements DisposableBean {

    private static final Logger logger = LoggerFactory
            .getLogger(EmbeddedSmtpServer.class);

//    private final Wiser wiser;

    public EmbeddedSmtpServer() {
//        this.wiser = new Wiser();
//        this.wiser.setHostname("localhost");
//        this.wiser.setPort(2500);
        //this.wiser.start();
        logger.info("wiser is started...");
    }

    public String getSentMessages(){
        StringBuilder sb = new StringBuilder();
//        sb.append("");
//        for (WiserMessage message : wiser.getMessages()) {
//            String envelopeSender = message.getEnvelopeSender();
//            String envelopeReceiver = message.getEnvelopeReceiver();
//
//            try{
//                MimeMessage mess = message.getMimeMessage();
//                sb.append(mess.getAllRecipients().toString());
//                sb.append((String) mess.getContent());
//            } catch (Exception e){
//                logger.error(e.getMessage(), e);
//            }
//
//            // now do assert the message headers and contents
//        }

        return sb.toString();
    }

    @PostConstruct
    public void postConstruct() {
        logger.info("starting wiser SMTP server()");
        logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//        this.wiser.start();
    }

    @Override
    public void destroy(){
        logger.info("**************************************************************************");
        logger.info("**************************************************************************");
        logger.debug("**************************************************************************");
        logger.debug("**************************************************************************");
        logger.debug("**************************************************************************");
        logger.debug("**************************************************************************");
//        wiser.stop();
    }

}
