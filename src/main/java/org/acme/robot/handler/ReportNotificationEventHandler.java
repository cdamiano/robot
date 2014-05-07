package org.acme.robot.handler;

import com.google.common.eventbus.Subscribe;
import org.acme.robot.event.ReportNotificationEvent;

public interface ReportNotificationEventHandler {

    @Subscribe
    void handleEvent(ReportNotificationEvent event);
}
