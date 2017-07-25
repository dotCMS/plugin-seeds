package com.dotcms.pushpublish.listener;

import com.dotcms.api.system.event.local.EventSubscriber;
import com.dotcms.api.system.event.local.type.AllEndpointsSuccessEvent;

/**
 * Sample class
 */
public class SuccessEndpointsSubscriber implements EventSubscriber<AllEndpointsSuccessEvent> {


    public void notify(AllEndpointsSuccessEvent event) {
        System.out.println("Successfully pushed to all endpoints");
    }

} // EOC MySample