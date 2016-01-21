package com.baselogic.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/jaxrs")
public class OrderApplication extends Application {

    private static final Logger logger = LoggerFactory
            .getLogger(OrderApplication.class);

    public OrderApplication(){
        logger.warn("mapping {} to /jaxrs", this.getClass());
    }

    @Override
    public Set<Class<?>> getClasses() {

            logger.info("REST configuration starting: getClasses()");

			Set<Class<?>> classes = new HashSet<Class<?>>();

			classes.add(OrderResource.class);
			//classes.add(GensonCustomResolver.class);
			return classes;
    }

    @Override
    public Map<String, Object> getProperties() {
        logger.info("getProperties()");
        return Collections.emptyMap();
    }


} // the end...
