package ua.training.spring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.training.spring.aspects.StatisticsAspect;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

import static java.lang.System.*;
/**
 * Hello world!
 *
 */
public class App {

    @Autowired
    private Client client;
    //@Autowired
    //@Qualifier("#{T(Event).isDay()? fileEventLogger : consoleEventLogger}")
    @Resource(name="cacheFileEventLogger")
    private EventLogger eventLogger;
    Map<EventType, EventLogger> loggers;
    @Autowired
    StatisticsAspect statisticsAspect;


//    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
    public App(Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.eventLogger = eventLogger;
        this.loggers = loggers;
    }

    private void logEvent(EventType type, Event event) {
        EventLogger logger = loggers.get(type);
        if (logger == null){
            //logger = defaultLogger;
            logger = eventLogger;
        }
        String message = event.getMsg().replaceAll("\\d+", client.getFullName());
        event.setMsg(message);
        logger.logEvent(event);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");

        Event evt1 = (Event)ctx.getBean("event");
        evt1.setMsg("Some event for user 1");
        app.logEvent(EventType.INFO, evt1);

        evt1 = (Event)ctx.getBean("event");
        evt1.setMsg("Some event for user 2");
        app.logEvent(null, evt1);

        evt1 = (Event)ctx.getBean("event");
        evt1.setMsg("Some event for user 3");
        app.logEvent(EventType.ERROR, evt1);

        app.printAspectStatistics();

        ctx.close();
    }

    private void printAspectStatistics() {
        System.out.println("Aspects statistics:");
        for (Map.Entry<?, Integer> entr: statisticsAspect.getCounter().entrySet()){
            System.out.println("Entry: " + entr.getKey().toString() + "  Value: "  + entr.getValue());
        }
    }


}
