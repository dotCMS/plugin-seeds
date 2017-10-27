package com.dotcms.pushpublish.listener;


import com.dotcms.pushpublish.util.EventUtil;
import com.dotcms.system.event.local.model.Subscriber;
import com.dotcms.system.event.local.type.pushpublish.PushPublishEndEvent;
import com.dotmarketing.util.Logger;

public class PushPublishEndSubscriber {

    @Subscriber
    public void notify(PushPublishEndEvent event) {
        EventUtil.logBasicEvent(event, this.getClass());
    }

} //PushPublishEndSubscriber.
