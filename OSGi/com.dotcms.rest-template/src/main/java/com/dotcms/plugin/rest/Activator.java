package com.dotcms.plugin.rest;

import org.osgi.framework.BundleContext;
import com.dotcms.rest.config.RestServiceUtil;
import com.dotmarketing.osgi.GenericBundleActivator;
import com.dotmarketing.util.Logger;

public class Activator extends GenericBundleActivator {

	Class clazz = DotTemplateResource.class;

	public void start(BundleContext context) throws Exception {

		Logger.info(this.getClass(), "Adding new Template Restful Service:" + clazz.getSimpleName());
		RestServiceUtil.addResource(clazz);
	}

	public void stop(BundleContext context) throws Exception {

		Logger.info(this.getClass(), "Removing new Template Restful Service:" + clazz.getSimpleName());
		RestServiceUtil.removeResource(clazz);
	}

}
