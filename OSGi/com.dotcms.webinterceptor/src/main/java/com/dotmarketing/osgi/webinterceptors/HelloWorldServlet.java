package com.dotmarketing.osgi.webinterceptors;

import com.dotcms.util.CloseUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class HelloWorldServlet extends HttpServlet {

    private static final long serialVersionUID = 42L;

    protected void doGet ( final HttpServletRequest httpServletRequest,
                           final HttpServletResponse httpServletResponse ) throws ServletException, IOException {

        httpServletResponse.setContentType( "text/html" );

        ServletOutputStream out = null;

        try {

            out = httpServletResponse.getOutputStream();
            out.println( "<html><body>" );

            out.println("<b>hello:</b> "        + httpServletRequest.getAttribute("hello")       + "<br/>");
            out.println("<b>pi:</b> "           + httpServletRequest.getAttribute("pi")          + "<br/>");
            out.println("<b>golden ratio:</b> " + httpServletRequest.getAttribute("goldenratio") + "<br/>");

            out.println( "</body></html>" );
        } finally {

            CloseUtils.closeQuietly(out);
        }
    }

}