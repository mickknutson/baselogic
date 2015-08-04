package com.baselogic.tutorials.service;

import com.baselogic.bfl.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;


/**
 * TODO: Helps to internationalize based on messages.properties
 *
 * @author Mick Knutson
 */
@Component
public class I18nManager implements Serializable {

    private static final long serialVersionUID = 1L;

//    @Autowired
//    @Qualifier("messageSource")
    private ResourceBundle messageSource;

    public String getMessage(String key) {
        //assert messageSource != null;

        if (messageSource == null) {
            createResourceBundle();
        }
        try{
            return messageSource.getString(key);
        } catch (MissingResourceException e){
            return "missing:" + key;
        }
    }

    public String getMessage(String key, Object... arguments) {
        return MessageFormat.format(getMessage(key), arguments);
    }

    public Locale getLocale() {
        /*
           * Fetch the locale resolved by Spring in the application servlet
           */
        return LocaleContextHolder.getLocale();
    }

    public void createResourceBundle() {
        //Locale locale = CodewashApplication.get().getLocale();
        Locale locale = getLocale();
        this.messageSource = ResourceBundle.getBundle(Constants.RESOURCE_BUNDLE, locale);
    }

}
