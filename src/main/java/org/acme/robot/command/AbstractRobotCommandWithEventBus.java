package org.acme.robot.command;

import com.google.common.eventbus.EventBus;
import org.acme.robot.event.ErrorEvent;
import org.acme.robot.model.Robot;

import static com.google.common.base.Preconditions.checkArgument;

public abstract class AbstractRobotCommandWithEventBus implements RobotCommand {

    private EventBus eventBus;

    public AbstractRobotCommandWithEventBus(EventBus eventBus) {
        checkArgument(eventBus != null, "EventBus parameter can not be null");
        this.eventBus = eventBus;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    @Override
    public void execute(Robot robot) {
        try {
            executeCommand(robot);
        } catch (Exception ex) {
            eventBus.post(new ErrorEvent(getTag(), ex));
        }
    }

    protected abstract void executeCommand(Robot robot) throws Exception;

    protected abstract String getTag();
}
