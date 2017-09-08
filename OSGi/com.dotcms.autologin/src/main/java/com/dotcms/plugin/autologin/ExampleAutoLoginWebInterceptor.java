package com.dotcms.plugin.autologin;

import com.dotcms.cms.login.LoginServiceAPI;
import com.dotcms.filters.interceptor.Result;
import com.dotcms.filters.interceptor.WebInterceptor;
import com.dotmarketing.business.APILocator;
import com.dotmarketing.cms.factories.PublicEncryptionFactory;
import com.dotmarketing.util.UtilMethods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This example just receives the userid on a query string parameter an do the autologin based on it.
 */
public class ExampleAutoLoginWebInterceptor implements WebInterceptor {

    private final LoginServiceAPI loginServiceAPI;

    public ExampleAutoLoginWebInterceptor() {
        this(APILocator.getLoginServiceAPI());
    }

    public ExampleAutoLoginWebInterceptor(final LoginServiceAPI loginServiceAPI) {
        this.loginServiceAPI = loginServiceAPI;
    }

    @Override
    public Result intercept(final HttpServletRequest request,
                            final HttpServletResponse response) throws IOException {
        final HttpSession session  = request.getSession(false);
        final String      userId   = request.getParameter("exampleUserId");
        Result result = Result.NEXT;

        if (UtilMethods.isSet(userId) && null != session) {

            if (this.loginServiceAPI.doCookieLogin(PublicEncryptionFactory.encryptString
                    (userId), request, response)) {

                System.out.println("Autologin done for the user: " + userId);
                result = Result.SKIP;
            }
        }

        return result;
    } // intercept
}
