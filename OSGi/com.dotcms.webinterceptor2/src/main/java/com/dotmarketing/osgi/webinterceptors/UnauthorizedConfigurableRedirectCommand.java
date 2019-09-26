package com.dotmarketing.osgi.webinterceptors;

import com.dotmarketing.beans.Host;
import com.dotmarketing.portlets.languagesmanager.model.Language;
import com.dotmarketing.util.Config;
import com.dotmarketing.util.UtilMethods;
import com.liferay.portal.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfigurableRedirectCommand implements FilteredCommand {


    @Override
    public boolean tryRedirect(final HttpServletRequest request,
                               final HttpServletResponse response,
                               final Language language,
                               final Host host,
                               final User user,
                               final String uri) {

        final String[]  filteredUrls = Config.getStringArrayProperty("dotcms.filtered.urls");

        if (UtilMethods.isSet(filteredUrls)) {
            for (final String filteredUrl : filteredUrls) {

                if (uri.contains(filteredUrl)) {

                    return true;
                }
            }
        }

        return false;
    }
}
