package com.dotcms.staticpublish.listener;


import com.dotcms.publishing.BundlerUtil;
import com.dotcms.publishing.PublisherConfig;
import com.dotcms.staticpublish.publisher.StaticPublisher;
import com.dotcms.system.event.local.model.EventSubscriber;
import com.dotcms.system.event.local.type.staticpublish.AllStaticPublishEndpointsSuccessEvent;
import com.dotmarketing.util.Logger;

import java.io.File;
import java.nio.file.Paths;

public class SuccessEndpointsSubscriber implements EventSubscriber<AllStaticPublishEndpointsSuccessEvent> {

    public void notify(AllStaticPublishEndpointsSuccessEvent event) {

        final PublisherConfig config = event.getConfig();
        final File staticBundleRoot = BundlerUtil.getStaticBundleRoot(config);
        final String remotePath = "/var/www/html/";

        Logger.info(this, "Start pushing via SCP");
        Logger.info(this, "Static Bundle Root: " + staticBundleRoot.getPath());
        Logger.info(this, "Remote Bundle Path: " + remotePath);

        StaticPublisher publisher = new StaticPublisher();

        final File[] bundleFiles = staticBundleRoot.listFiles();
        for (File file : bundleFiles) {
            publisher.publish(
                file.toPath(),
                Paths.get(remotePath)
            );
        }

        Logger.info(this, "End of pushing via SCP");

    }

} //SuccessEndpointsSubscriber.