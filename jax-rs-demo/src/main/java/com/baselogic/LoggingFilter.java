package com.baselogic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class LoggingFilter
 */
@WebFilter("/LoggingFilter")
public class LoggingFilter implements Filter {

    private static final Logger logger = LoggerFactory
            .getLogger(AnnotationServlet.class);

    /**
     * Default constructor.
     */
    public LoggingFilter() {
        // TODO Auto-generated constructor stub
        logger.info("LoggingFilter constructor");
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		System.out.println("Logging Filter before Chain: "+new Date());
		logger.info("Logging Filter before Chain: {}", new Date());

		// pass the request along the filter chain
		chain.doFilter(request, response);

		System.out.println("Logging Filter after Chain: "+new Date());
		logger.info("Logging Filter after Chain: {}", new Date());
}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
