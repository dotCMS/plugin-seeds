package com.dotcms.interceptor;

import com.dotcms.api.system.event.Payload;
import com.dotcms.api.system.event.SystemEventType;
import com.dotcms.api.system.event.SystemEventsAPI;
import com.dotcms.api.system.event.Visibility;
import com.dotcms.filters.interceptor.Result;
import com.dotcms.filters.interceptor.WebInterceptor;
import com.dotcms.notifications.business.NotificationAPI;
import com.dotcms.rendering.velocity.util.VelocityUtil;
import com.dotmarketing.business.UserAPI;
import com.dotmarketing.exception.DotDataException;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.StringPool;
import org.apache.velocity.context.Context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DotSpringWebInterceptor implements WebInterceptor {

    private NotificationAPI notificationAPI;
    private UserAPI         userAPI;

    @Override
    public String[] getFilters() {
        return new String [] {"/spring/*"};
    }

    @Override
    public Result intercept(final HttpServletRequest request,
                            final HttpServletResponse response) throws IOException {


        try {
            // send a notification
            final User requestUser = PortalUtil.getUser(request);
            notificationAPI.info("Spring OSGI Works", requestUser.getUserId());

            // say hello
            final String userId = request.getParameter("userId");
            User user = requestUser;
            if(null != userId) {

                user = this.userAPI.loadUserById(userId);
            }

            response.setContentType("text/html");

            final Context context = VelocityUtil.getInstance().getContext(request, response);
            context.put("user", user);
            try (InputStream is = getClass().getClassLoader().getResourceAsStream("velocity/hello.vtl")) {

                VelocityUtil.getEngine().evaluate(context, response.getWriter(), StringPool.BLANK, new InputStreamReader(is));
            }
        } catch (Exception e) {
            response.sendError(500);
        }
        return Result.SKIP_NO_CHAIN;
    }

    public void setNotificationAPI(NotificationAPI notificationAPI) {
        this.notificationAPI = notificationAPI;
    }

    public void setUserAPI(UserAPI userAPI) {
        this.userAPI = userAPI;
    }
}
