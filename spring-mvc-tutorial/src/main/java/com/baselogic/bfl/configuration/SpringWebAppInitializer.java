package com.baselogic.tutorials.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ru.xpoft.vaadin.SpringVaadinServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * Initialize Servlets, Filters and Listeners for Tomcat 7
 */
public class SpringWebAppInitializer implements WebApplicationInitializer {

    private static final Logger logger = LoggerFactory
            .getLogger(SpringWebAppInitializer.class);

    private static final String DEFAULT_SERVLET_MAPPING = "/";

    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

    private static final String VAADIN_SERVLET_NAME = "SpringConfigVaadinServlet";

    @Override
    public void onStartup(ServletContext servletContext) {
        logger.info("SpringWebAppInitializer::onStartup(ServletContext)");
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        registerInitParameter(servletContext);
        registerDefaultServlet(servletContext);

        registerListeners(servletContext);
        registerDispatcherServlet(servletContext);
        registerVaadinServlet(servletContext);

//        // Start Annotation Context
//        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
//        rootContext.register(SpringContextConfiguration.class);
//
        //-------------------------------------------------------------------//
        // Start Spring Dispatcher
//        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(rootContext));
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping(DEFAULT_SERVLET_MAPPING);
//
//        // <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
//        servletContext.addListener(new ContextLoaderListener(rootContext));

        //-------------------------------------------------------------------//
        // Start Vaadin Servlet
//        ServletRegistration.Dynamic vaadinServlet = servletContext.addServlet(VAADIN_SERVLET_NAME, new SpringVaadinServlet());
//        vaadinServlet.setInitParameter("beanName", "appUI");
//        vaadinServlet.setInitParameter("systemMessagesBeanName", "DEFAULT");
//        vaadinServlet.setLoadOnStartup(1);
//        vaadinServlet.addMapping(DEFAULT_SERVLET_MAPPING);
//        vaadinServlet.addMapping("/VAADIN/*");
    }

    protected void registerInitParameter(ServletContext servletContext) {
        logger.info("*** SpringWebAppInitializer::registerInitParameter()");

        servletContext.setInitParameter("spring.profiles.default", "dev");
        servletContext.setInitParameter("spring.profiles.active", "dev");
    }

    protected void registerListeners(ServletContext servletContext) {
        logger.info("*** SpringWebAppInitializer::registerListeners()");
        final AnnotationConfigWebApplicationContext rootContext = createContext(SpringContextConfiguration.class);
        ContextLoaderListener contextLoaderListener = new ContextLoaderListener(rootContext);

        contextLoaderListener.initWebApplicationContext(servletContext);
        servletContext.addListener(new RequestContextListener());
    }

    protected void registerDefaultServlet(ServletContext servletContext) {
        logger.info("*** SpringWebAppInitializer::registerDefaultServlet()");
        // Static resource handling using "default" servlet
//        servletContext.getServletRegistration ("default").addMapping ("*.js", "*.css", "*.jpg", "*.gif", "*.png");
        servletContext.getServletRegistration("default").addMapping ("/static/*");
    }

    protected void registerDispatcherServlet(ServletContext servletContext) {
        logger.info("*** SpringWebAppInitializer::registerDispatcherServlet()");
        final AnnotationConfigWebApplicationContext dispatcherContext = createContext(WebAppContext.class);
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(DISPATCHER_SERVLET_NAME,
                new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping(DEFAULT_SERVLET_MAPPING);
    }

    protected void registerVaadinServlet(ServletContext servletContext) {
        logger.info("*** SpringWebAppInitializer::registerVaadinServlet()");
        final AnnotationConfigWebApplicationContext vaadinContext = createContext(VaadinConfiguration.class);
        ServletRegistration.Dynamic vaadinServlet = servletContext.addServlet(VAADIN_SERVLET_NAME,
                new SpringVaadinServlet());
        vaadinServlet.setInitParameter("beanName", "appUI");
        vaadinServlet.setInitParameter("systemMessagesBeanName", "DEFAULT");
        vaadinServlet.setLoadOnStartup(1);
        vaadinServlet.addMapping(DEFAULT_SERVLET_MAPPING);
        vaadinServlet.addMapping("/VAADIN/*");

//        vaadinServlet.setInitParameter("productionMode", "true");
//        servletContext.setInitParameter("productionMode", "true");

        /*
        <context-param>
        <description>Vaadin production mode</description>
        <param-name>productionMode</param-name>
        <param-value>false</param-value>
    </context-param>
         */
    }

    protected AnnotationConfigWebApplicationContext createContext(final Class<?>... annotatedClasses) {
        logger.info("*** SpringWebAppInitializer::createContext()");
        final AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(annotatedClasses);
        return context;
    }

        /*XmlWebApplicationContext rootContext = new XmlWebApplicationContext();
        rootContext.setConfigLocations(new String[]{"classpath*:META-INF/spring/applicationContext-security.xml", "classpath*:META-INF/spring/applicationContext.xml"});

        servletContext.addListener(new ContextLoaderListener(rootContext));

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", DispatcherServlet.class);
        dispatcher.setInitParameter("contextConfigLocation", "/WEB-INF/spring/webmvc-config.xml");
        dispatcher.addMapping("/");

        servletContext.addFilter("Spring OpenEntityManagerInViewFilter", org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter.class)
                .addMappingForUrlPatterns(null, false, "*//*");

        servletContext.addFilter("HttpMethodFilter", org.springframework.web.filter.HiddenHttpMethodFilter.class)
                .addMappingForUrlPatterns(null, false, "*//*");

        servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"))
                .addMappingForUrlPatterns(null, false, "*//*");

        FilterRegistration charEncodingfilterReg = servletContext.addFilter("CharacterEncodingFilter", CharacterEncodingFilter.class);
        charEncodingfilterReg.setInitParameter("encoding", "UTF-8");
        charEncodingfilterReg.setInitParameter("forceEncoding", "true");
        charEncodingfilterReg.addMappingForUrlPatterns(null, false, "*//*");*/

}
