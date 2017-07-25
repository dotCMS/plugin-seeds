package com.dotcms.pushpublish.listener;

import com.dotcms.api.system.event.local.EventSubscriber;
import com.dotcms.api.system.event.local.type.AddedToQueueEvent;

/**
 * Sample class
 */
public class AddToQueueSubscriber implements EventSubscriber<AddedToQueueEvent> {


    public void notify(AddedToQueueEvent event) {
        System.out.println("Assets were added to queue");
    }

} // EOC MySample