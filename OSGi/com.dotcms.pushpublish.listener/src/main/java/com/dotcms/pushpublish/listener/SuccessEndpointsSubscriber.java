package com.dotcms.pushpublish.listener;


import com.dotcms.system.event.local.model.EventSubscriber;
import com.dotcms.system.event.local.type.pushpublish.AllEndpointsSuccessEvent;

/**
 * Sample class
 */
public class SuccessEndpointsSubscriber implements EventSubscriber<AllEndpointsSuccessEvent> {


    public void notify(AllEndpointsSuccessEvent event) {
        System.out.println("Successfully pushed to all endpoints");
    }

} // EOC MySample