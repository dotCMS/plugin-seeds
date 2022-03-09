package com.dotmarketing.osgi.webinterceptors;

import static com.dotcms.util.CollectionsUtils.map;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import com.dotcms.filters.interceptor.Result;
import com.dotcms.filters.interceptor.WebInterceptor;
import com.dotmarketing.util.UtilMethods;

/**
 * This web interceptor adds the access control allow origin in addition to overrides the request and response
 * @author jsanca
 */
public class PreWebInterceptor implements WebInterceptor {

    @Override
    public String[] getFilters() {
        return new String[] {"/app/helloworld"};
    }

    @Override
    public Result intercept(final HttpServletRequest request,
                            final HttpServletResponse response) throws IOException {

        Result result = Result.NEXT;

        final String action = request.getParameter("action");
        switch (action) {

            case "wrap":
                result = new Result.Builder().next()
                    .wrap(new PreloadRequest(request))
                    .wrap(new HeaderDecoratorResponse(response, request.getHeader("Origin"))).build();
            break;
            case "break":
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getOutputStream().println("Breaking the interceptor, bad request");
                result = Result.SKIP_NO_CHAIN;
            break;
        }

        return result;
    }

    // just includes some dummy attributes by default
    private static class PreloadRequest extends HttpServletRequestWrapper {

        private Map<String, Object> attributes = map(
                "hello", "This is a hello test",
                "pi", new Double(3.14159265359),
                "goldenratio", new Double(1.6180339887));

        public PreloadRequest(final HttpServletRequest request) {
            super(request);
        }

        @Override
        public Object getAttribute(final String name) {
            return this.attributes.getOrDefault(name, super.getAttribute(name));
        }
    }

    private static class HeaderDecoratorResponse extends HttpServletResponseWrapper {

        public HeaderDecoratorResponse(final HttpServletResponse response, final String origin) {
            super(response);
            this.setHeader("Access-Control-Allow-Origin", UtilMethods.isSet(origin)?origin:"http://dotcms.webinterceptor.com");
            this.setHeader("Access-Control-Allow-Credentials", "true");
            this.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            this.setHeader("Access-Control-Max-Age", "3600");
            this.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
        }
    }
}
