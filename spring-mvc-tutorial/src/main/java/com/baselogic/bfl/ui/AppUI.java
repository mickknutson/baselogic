package com.baselogic.tutorials.ui;

import com.baselogic.bfl.service.ApplicationCounter;
import com.baselogic.bfl.service.SessionCounter;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.xpoft.vaadin.DiscoveryNavigator;

/**
 *
 * https://github.com/xpoft/vaadin-samples/tree/master/spring-session/src
 *
 */
//@Configurable(preConstruction = true)
@Configurable
@Component
@Scope("prototype")
@Theme("baselogic")
public class AppUI extends UI {

    @Autowired
    private transient ApplicationContext applicationContext;

    @Autowired
    private SessionCounter sessionCounter;

    @Autowired
    private transient ApplicationCounter applicationCounter;

    @Override
    protected void init(final VaadinRequest request){
        setSizeFull();

        DiscoveryNavigator navigator = new DiscoveryNavigator(this, this);

        Notification.show(String.format("Session counter: %d, application counter: %d", sessionCounter.getCount(), applicationCounter.getCount()));
//        Notification.show(String.format("Session counter: %d, application counter: %d", null, applicationCounter.getCount()));
    }
}
