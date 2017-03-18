package ua.training.spring;

import java.util.Collection;

/**
 * Created by User on 18.03.2017.
 */
public class CombinedEventLogger implements EventLogger{

    private Collection<EventLogger> loggers;

    public CombinedEventLogger(Collection loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        for (EventLogger logger: loggers){
            logger.logEvent(event);
        }
    }
}
