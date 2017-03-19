package ua.training.spring;

/**
 * Created by Dmytro_Deinichenko on 3/16/2017.
 */
public class ConsoleEventLogger implements EventLogger {

    @Override
    public void logEvent(Event event){
        System.out.println(event.toString());
    }
}
