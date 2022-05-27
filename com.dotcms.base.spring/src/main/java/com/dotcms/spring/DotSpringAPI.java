package com.dotcms.spring;

import org.springframework.context.ApplicationContext;

public class DotSpringAPI {

    private ApplicationContext context;
    private static class SingletonHolder {
        private static final DotSpringAPI INSTANCE = new DotSpringAPI();
    }
    /**
     * Get the instance.
     * @return DotSpringAPI
     */
    public static DotSpringAPI getInstance() {

        return DotSpringAPI.SingletonHolder.INSTANCE;
    } // getInstance.

    public void setContext (final ApplicationContext context) {

        this.context = context;
    }

    public ApplicationContext getContext() {
        return this.context;
    }
}
