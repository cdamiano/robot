package org.acme.robot.handler;

import com.google.common.eventbus.Subscribe;
import org.acme.robot.event.ErrorEvent;

public interface ErrorEventHandler {

    @Subscribe
    void handleEvent(ErrorEvent error);

}
