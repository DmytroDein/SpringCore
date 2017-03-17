package ua.training.spring;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

import static java.lang.System.*;
/**
 * Hello world!
 *
 */
public class App {

    private Client client;
    private EventLogger eventLogger;

    public App() {
    }

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    private void logEvent(Event event) {
        String message = event.getMsg().replaceAll(client.getId(), client.getFullName());
        eventLogger.logEvent(event);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");

        /*app.client = new Client("1", "Jonh Smith");
        app.eventLogger = new ConsoleEventLogger();*/

        Event evt1 = (Event)ctx.getBean("event");
        evt1.setMsg("Some event for user 1");
        app.logEvent(evt1);

        evt1 = (Event)ctx.getBean("event");
        evt1.setMsg("Some event for user 2");
        app.logEvent(evt1);

        ctx.close();
    }


}
