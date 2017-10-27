package com.dotcms.pushpublish.listener;


import com.dotcms.publisher.business.PublishQueueElement;
import com.dotcms.pushpublish.util.EventUtil;
import com.dotcms.system.event.local.model.EventSubscriber;
import com.dotcms.system.event.local.type.pushpublish.AddedToQueueEvent;
import com.dotmarketing.util.Logger;

import java.util.List;

public class AddToQueueSubscriber implements EventSubscriber<AddedToQueueEvent> {


    public void notify(AddedToQueueEvent event) {
        EventUtil.logBasicEvent(event, this.getClass());
    }

} //AddToQueueSubscriber.