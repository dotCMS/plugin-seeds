package com.dotcms.pushpublish.listener;


import com.dotcms.pushpublish.util.EventUtil;
import com.dotcms.system.event.local.model.EventSubscriber;
import com.dotcms.system.event.local.type.staticpublish.AllStaticPublishEndpointsSuccessEvent;

public class SuccessEndpointsSubscriber implements EventSubscriber<AllStaticPublishEndpointsSuccessEvent> {

    public void notify(AllStaticPublishEndpointsSuccessEvent event) {
        EventUtil.logAllEndpointsSuccessEvent(event, this.getClass());
    }

} //SuccessEndpointsSubscriber.