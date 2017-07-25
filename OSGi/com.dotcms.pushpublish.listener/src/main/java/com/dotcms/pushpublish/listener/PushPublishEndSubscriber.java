package com.dotcms.pushpublish.listener;

import com.dotcms.api.system.event.local.Subscriber;
import com.dotcms.api.system.event.local.type.PushPublishEndEvent;

public class PushPublishEndSubscriber {
    @Subscriber
    public void notify(PushPublishEndEvent event) {
        System.out.println("Executing subscriber for " + event.getName());
    }
}
