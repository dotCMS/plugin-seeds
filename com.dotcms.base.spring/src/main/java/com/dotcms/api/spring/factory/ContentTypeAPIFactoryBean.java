package com.dotcms.api.spring.factory;

import com.dotcms.api.web.HttpServletRequestThreadLocal;
import com.dotcms.api.web.HttpServletResponseThreadLocal;
import com.dotcms.contenttype.business.ContentTypeAPI;
import com.dotmarketing.business.APILocator;
import com.dotmarketing.business.UserAPI;
import com.liferay.portal.auth.PrincipalThreadLocal;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;
import org.springframework.beans.factory.FactoryBean;

import javax.servlet.http.HttpServletRequest;

public class ContentTypeAPIFactoryBean implements FactoryBean<ContentTypeAPI> {

    private UserAPI userAPI;

    @Override
    public ContentTypeAPI getObject() throws Exception {

        final User systemUser = this.userAPI.getSystemUser();
        User user = systemUser;
        final HttpServletRequest request = HttpServletRequestThreadLocal.INSTANCE.getRequest();
        if (null != request) {

            user = PortalUtil.getUser(request);
        }

        if (null == user) {
            try {
                final String userId = PrincipalThreadLocal.getName();
                user = this.userAPI.loadUserById(userId);
            } catch (Exception e) {

                user = systemUser;
            }
        }

        if (null == user) {

            user = systemUser;
        }

        return APILocator.getContentTypeAPI(user);
    }

    @Override
    public Class<?> getObjectType() {
        return ContentTypeAPI.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public UserAPI getUserAPI() {
        return userAPI;
    }
}
