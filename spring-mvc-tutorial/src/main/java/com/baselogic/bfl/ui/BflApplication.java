package com.baselogic.tutorials.ui;

import com.baselogic.bfl.configuration.SpringContextHelper;
import com.baselogic.bfl.service.I18nManager;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

//@Configurable(preConstruction = true)
public class BflApplication extends UI/* implements TransactionListener*/ {

    @Autowired
    private transient I18nManager i18nManager;

//    @Autowired
//    protected transient CurrentUserFactoryBean currentUserFactoryBean;

    /*@Override
    public void init() {
        setTheme("baselogic");
        getContext().addTransactionListener(this);
        setMainWindow(new MainWindow());
        *//*
        final Window main = new Window("Hello window");
        setMainWindow(main);

        SpringContextHelper helper = new SpringContextHelper(this);
        MyBeanInterface bean = (MyBeanInterface) helper.getBean("myBean");
        main.addComponent(new Label( bean.myMethod() ));
         *//*
    }*/

    @Override
    protected void init(VaadinRequest request) {

        SpringContextHelper helper = new SpringContextHelper(VaadinServlet.getCurrent().getServletContext());
        final VerticalLayout content = new VerticalLayout();
//        final MyBeanInterface bean = (MyBeanInterface) helper.getBean("myBean");
        content.setMargin(true);
        setContent(content);
        // Create the content root layout for the UI
        setContent(content);

        Button button = new Button("Click Me");
        button.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
//                content.addComponent(new Label(String.valueOf(bean.myMethod())));
                content.addComponent(new Label(String.valueOf("Testing")));
            }
        });
        content.addComponent(button);
    }

    /*@Override
    public void close() {
        setUser(null);
        getContext().removeTransactionListener(this);
        ((MainWindow) getMainWindow()).destroy();
        super.close();
    }*/

//    @Override
//    public void transactionStart(Application application, Object transactionData) {
//        String username = (String) getUser();
////        currentUserFactoryBean.setCurrentUsername(username);
//    }

//    @Override
//    public void transactionEnd(Application application, Object transactionData) {
////        currentUserFactoryBean.setCurrentUsername(null);
//    }
}
