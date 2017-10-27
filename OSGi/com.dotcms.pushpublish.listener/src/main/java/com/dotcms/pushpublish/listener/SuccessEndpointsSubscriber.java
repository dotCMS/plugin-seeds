package com.dotcms.pushpublish.listener;


import com.dotcms.pushpublish.util.EventUtil;
import com.dotcms.system.event.local.model.EventSubscriber;
import com.dotcms.system.event.local.type.pushpublish.AllEndpointsSuccessEvent;
import com.dotmarketing.util.Logger;

public class SuccessEndpointsSubscriber implements EventSubscriber<AllEndpointsSuccessEvent> {

    public void notify(AllEndpointsSuccessEvent event) {
        EventUtil.logAllEndpointsSuccessEvent(event, this.getClass());
    }

} //SuccessEndpointsSubscriber.