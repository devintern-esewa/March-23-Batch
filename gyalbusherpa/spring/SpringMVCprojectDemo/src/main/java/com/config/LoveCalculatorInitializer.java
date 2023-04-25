package com.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

//public class LoveCalculatorInitializer implements WebApplicationInitializer {
//    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {
//
//        AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
//        webApplicationContext.register(LoveCalculatorAppConfig.class);
//
//        // create a dispatcher servlet object
//        DispatcherServlet dispatcherServlet = new DispatcherServlet(webApplicationContext);
//
//        // register the dispatcher servlet with the servlet context object
//        ServletRegistration.Dynamic myCustomDispatcherServlet = servletContext.addServlet("myDispatcherServlet",dispatcherServlet);
//        myCustomDispatcherServlet.setLoadOnStartup(1);
//        myCustomDispatcherServlet.addMapping("/don.com/*");
//    }
//}

public class LoveCalculatorInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{LoveCalculatorAppConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
