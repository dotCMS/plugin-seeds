package com.dotcms.pushpublish.listener;


import com.dotcms.pushpublish.util.EventUtil;
import com.dotcms.system.event.local.model.EventSubscriber;
import com.dotcms.system.event.local.type.pushpublish.AllPushPublishEndpointsSuccessEvent;

public class SuccessEndpointsSubscriber implements EventSubscriber<AllPushPublishEndpointsSuccessEvent> {

    public void notify(AllPushPublishEndpointsSuccessEvent event) {
        EventUtil.logAllEndpointsSuccessEvent(event, this.getClass());
    }

} //SuccessEndpointsSubscriber.