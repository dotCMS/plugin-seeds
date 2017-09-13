package com.dotcms.plugin.autologin;

import com.dotcms.cms.login.LoginServiceAPI;
import com.dotcms.filters.interceptor.Result;
import com.dotcms.filters.interceptor.WebInterceptor;
import com.dotmarketing.business.APILocator;
import com.dotmarketing.business.UserAPI;
import com.dotmarketing.cms.factories.PublicEncryptionFactory;
import com.dotmarketing.exception.DotDataException;
import com.dotmarketing.exception.DotSecurityException;
import com.liferay.portal.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This example just receives the userid on a query string parameter an do the autologin based on it.
 */
public class ExampleAutoLoginWebInterceptor implements WebInterceptor {

    private  final LoginServiceAPI loginServiceAPI;
    private  final UserAPI userAPI;

    public ExampleAutoLoginWebInterceptor() {
        this(APILocator.getLoginServiceAPI(), APILocator.getUserAPI());
    }

    public ExampleAutoLoginWebInterceptor(final LoginServiceAPI loginServiceAPI,
                                          final UserAPI userAPI) {
        this.loginServiceAPI = loginServiceAPI;
        this.userAPI         = userAPI;
    }

    @Override
    public Result intercept(final HttpServletRequest request,
                            final HttpServletResponse response) throws IOException {
        final HttpSession session  = request.getSession(false);
        Result result = Result.NEXT;

        if (null != session) {

            final String userId = getUserId(request, session);

            if (null != userId) {

                final User user;

                try {
                    user = this.userAPI.loadUserById
                            (userId, this.userAPI.getSystemUser(), false);

                    if (null != user) {
                        if (this.checkUserRoles (user)) {

                            // If the user is active, so do the login
                            if (this.loginServiceAPI.doCookieLogin // LDAP communication and checks the user exist
                                    (PublicEncryptionFactory.encryptString
                                            (userId), request, response)) {


                                System.out.println("Autologin done for the user: " + userId);
                                result = Result.SKIP;
                            }
                        } else {
                            // login with the guest user, since the user does not have valid roles.
                        }
                    }
                } catch (DotDataException | DotSecurityException e) {
                    // todo: do something or log me
                }
            }
        }

        return result;
    } // intercept

    private boolean checkUserRoles(User user) {
        // check the user roles
        return true;
    }

    private String getUserId(final HttpServletRequest request, final HttpSession session) {
        // todo: get the user from the session, cookie or whatever.
        return "dotcms.org.1"; //(String)session.getAttribute("userEmail");
    }
}
