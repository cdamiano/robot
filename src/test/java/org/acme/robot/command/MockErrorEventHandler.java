package org.acme.robot.command;

import com.google.common.eventbus.Subscribe;
import org.acme.robot.event.ErrorEvent;
import org.acme.robot.handler.ErrorEventHandler;

public class MockErrorEventHandler implements ErrorEventHandler {
    public boolean handleEventCalled;

    @Override
    @Subscribe
    public void handleEvent(ErrorEvent error) {
        handleEventCalled = true;
    }
}
