package com.dotcms.pushpublish.listener;


import com.dotcms.pushpublish.util.EventUtil;
import com.dotcms.system.event.local.model.Subscriber;
import com.dotcms.system.event.local.type.pushpublish.PushPublishStartEvent;

public class PushPublishStartSubscriber {

    @Subscriber
    public void notify(PushPublishStartEvent event) {
        EventUtil.logBasicEvent(event, this.getClass());
    }

} //PushPublishStartSubscriber.
