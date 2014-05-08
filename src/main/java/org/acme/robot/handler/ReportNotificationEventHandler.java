package org.acme.robot.handler;

import com.google.common.eventbus.Subscribe;
import org.acme.robot.event.ReportNotificationEvent;

/**
 * <p>Implementation of this interface will handle the {@link org.acme.robot.event.ReportNotificationEvent} that get posted to the
 * {@link com.google.common.eventbus.EventBus}.</p>
 * <br/>
 * <B>NOTE</B>
 * <p>
 * Implementation classes must still apply the {@link com.google.common.eventbus.Subscribe} annotation to the {@link
 * #handleEvent(org.acme.robot.event.ReportNotificationEvent)}  method.  If you don't then the
 * {@link com.google.common.eventbus.EventBus} wont post events to implementing class.
 * </p>
 */
public interface ReportNotificationEventHandler {

    @Subscribe
    void handleEvent(ReportNotificationEvent event);
}
