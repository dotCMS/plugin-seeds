package com.dotcms.pushpublish.util;

import com.dotcms.publisher.business.PublishQueueElement;
import com.dotcms.system.event.local.type.pushpublish.AllEndpointsSuccessEvent;
import com.dotcms.system.event.local.type.pushpublish.PushPublishEvent;
import com.dotmarketing.util.Logger;

import java.util.List;

public class EventUtil {

    public static void logBasicEvent(final PushPublishEvent event, final Class clazz){
        Logger.info(clazz, "----------------------------");
        Logger.info(clazz, "Notifying event named: " + event.getName());
        Logger.info(clazz, "Event Date: " + event.getDate());

        final List<PublishQueueElement> publishQueueElements = event.getPublishQueueElements();
        for (PublishQueueElement publishQueueElement : publishQueueElements) {
            Logger.info(clazz, "Pushed Element: " + publishQueueElement.toString());
        }

        Logger.info(clazz, "----------------------------");
    } //logBasicEvent.

    public static void logAllEndpointsSuccessEvent(final AllEndpointsSuccessEvent event, final Class clazz){
        Logger.info(clazz, "----------------------------");
        Logger.info(clazz, "Successfully pushed to all endpoints");
        Logger.info(clazz, "Notifying event named: " + event.getName());
        Logger.info(clazz, "Config Id: " + event.getConfig().getId());
        Logger.info(clazz, "Config endpoint: " + event.getConfig().getEndpoint());
        Logger.info(clazz, "Event Date: " + event.getDate());

        final List<PublishQueueElement> publishQueueElements = event.getPublishQueueElements();
        for (PublishQueueElement publishQueueElement : publishQueueElements) {
            Logger.info(clazz, "Pushed Element: " + publishQueueElement.toString());
        }

        Logger.info(clazz, "----------------------------");
    } //logAllEndpointsSuccessEvent.

} //EventUtil.
