package com.dotcms.pushpublish.listener;

import com.dotcms.api.system.event.local.Subscriber;
import com.dotcms.api.system.event.local.type.PushPublishStartEvent;

public class PushPublishStartSubscriber {
    @Subscriber
    public void notify(PushPublishStartEvent event) {
        System.out.println("Executing subscriber for " + event.getName());
    }
}
