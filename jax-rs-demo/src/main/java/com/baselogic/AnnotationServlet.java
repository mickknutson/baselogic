package com.baselogic;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/example")
public class AnnotationServlet extends HttpServlet{

    private static final Logger logger = LoggerFactory
            .getLogger(AnnotationServlet.class);

    public AnnotationServlet(){
        logger.warn("mapping {} to /example", this.getClass());
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        logger.info("init {}", config.toString());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.write("Example servlet 3 annotation example configuration...");
    }

}
