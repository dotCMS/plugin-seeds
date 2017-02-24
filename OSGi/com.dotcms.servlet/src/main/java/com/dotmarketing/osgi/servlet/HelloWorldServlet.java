package com.dotmarketing.osgi.servlet;

import org.osgi.util.tracker.ServiceTracker;
import com.dotmarketing.osgi.service.HelloWorld;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class HelloWorldServlet extends HttpServlet {

    private static final long serialVersionUID = 42L;
    private ServiceTracker serviceTracker;

    public HelloWorldServlet ( ServiceTracker serviceTracker ) {
        this.serviceTracker = serviceTracker;
    }

    protected void doGet ( HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse ) throws ServletException, IOException {

        System.out.println("Saying hello");

        httpServletResponse.setContentType( "text/html" );

        ServletOutputStream out = httpServletResponse.getOutputStream();

        out.println( "<html><body>" );

        HelloWorld service = (HelloWorld) serviceTracker.getService();

        if ( service != null ) {
            out.println( service.hello() );
        } else {
            System.out.println("No Hello World service");
        }

        out.println( "</body></html>" );
        out.close();
    }

}