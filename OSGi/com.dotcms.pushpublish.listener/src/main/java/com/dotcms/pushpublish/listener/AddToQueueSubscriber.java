package com.dotcms.pushpublish.listener;


import com.dotcms.system.event.local.model.EventSubscriber;
import com.dotcms.system.event.local.type.pushpublish.AddedToQueueEvent;

/**
 * Sample class
 */
public class AddToQueueSubscriber implements EventSubscriber<AddedToQueueEvent> {


    public void notify(AddedToQueueEvent event) {
        System.out.println("Assets were added to queue");
    }

} // EOC MySample