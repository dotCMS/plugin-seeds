package com.dotmarketing.osgi.webinterceptors;

import com.dotmarketing.beans.Host;
import com.dotmarketing.portlets.languagesmanager.model.Language;
import com.liferay.portal.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Redirect command, handle and does the redirect if needed (return true to avoid the request chain)
 */
@FunctionalInterface
public interface RedirectCommand {

    /**
     * Tries a redirect
     * @param request
     * @param response
     * @param language
     * @param host
     * @param user
     * @param
     * @return boolean true if the redirect happens
     */
    boolean tryRedirect (final HttpServletRequest request,
                         final HttpServletResponse response,
                         final Language language,
                         final Host host,
                         final User user,
                         final String uri);
}
