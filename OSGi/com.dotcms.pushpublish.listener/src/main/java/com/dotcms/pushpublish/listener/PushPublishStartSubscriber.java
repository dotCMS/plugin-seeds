package com.dotcms.pushpublish.listener;


import com.dotcms.system.event.local.domain.Subscriber;
import com.dotcms.system.event.local.type.pushpublish.PushPublishStartEvent;

public class PushPublishStartSubscriber {
    @Subscriber
    public void notify(PushPublishStartEvent event) {
        System.out.println("Executing subscriber for " + event.getName());
    }
}
