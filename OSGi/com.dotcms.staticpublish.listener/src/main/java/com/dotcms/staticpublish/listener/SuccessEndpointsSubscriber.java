package com.dotcms.staticpublish.listener;


import com.dotcms.publishing.BundlerUtil;
import com.dotcms.publishing.PublisherConfig;
import com.dotcms.staticpublish.publisher.StaticPublisher;
import com.dotcms.system.event.local.model.EventSubscriber;
import com.dotcms.system.event.local.type.staticpublish.AllStaticPublishEndpointsSuccessEvent;
import com.dotmarketing.util.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.nio.file.Paths;
import java.util.Collection;

public class SuccessEndpointsSubscriber implements EventSubscriber<AllStaticPublishEndpointsSuccessEvent> {

    public void notify(AllStaticPublishEndpointsSuccessEvent event) {

        final PublisherConfig config = event.getConfig();
        final File staticBundleRoot = BundlerUtil.getStaticBundleRoot(config);
        final String staticBundleRootPath = staticBundleRoot.getPath();

        final boolean amIPublishing = PublisherConfig.Operation.PUBLISH.equals(config.getOperation());

        if (amIPublishing) {
            pushSFTP(staticBundleRootPath);
            //pushSCP(staticBundleRoot);
        } else {
            removeSFTP(staticBundleRoot);
        }
    }

    private void pushSCP(final File staticBundleRoot) {
        Logger.info(this, "Start Push via SCP");
        Logger.info(this, "Static Bundle Root: " + staticBundleRoot.getPath());

        StaticPublisher publisher = new StaticPublisher();
        final File[] files = staticBundleRoot.listFiles();

        for (File file : files) {
            publisher.publishSCP(file.toPath());
        }

        Logger.info(this, "End of Push via SCP");
    }

    private void pushSFTP(String staticBundleRootPath) {
        Logger.info(this, "Start Push via SFTP");
        Logger.info(this, "Static Bundle Root: " + staticBundleRootPath);

        StaticPublisher publisher = new StaticPublisher();
        publisher.publishSFTP(Paths.get(staticBundleRootPath));

        Logger.info(this, "End of Push via SFTP");
    }

    private void removeSFTP(File staticBundleRoot) {
        Logger.info(this, "Start Push REMOVE via SFTP");
        Logger.info(this, "Static Bundle Root: " + staticBundleRoot);

        StaticPublisher publisher = new StaticPublisher();

        Collection<File> listFiles =
            FileUtils.listFiles(staticBundleRoot, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);

        for (File file : listFiles) {
            final String absolutePath = file.getPath().replace(staticBundleRoot.getPath(), "");
            publisher.removeSFTP(absolutePath);
        }

        Logger.info(this, "End of Push REMOVE via SFTP");
    }

} //SuccessEndpointsSubscriber.