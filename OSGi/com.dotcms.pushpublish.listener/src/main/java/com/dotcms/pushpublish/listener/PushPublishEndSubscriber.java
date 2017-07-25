package com.dotcms.pushpublish.listener;


import com.dotcms.system.event.local.domain.Subscriber;
import com.dotcms.system.event.local.type.pushpublish.PushPublishEndEvent;

public class PushPublishEndSubscriber {
    @Subscriber
    public void notify(PushPublishEndEvent event) {
        System.out.println("Executing subscriber for " + event.getName());
    }
}
