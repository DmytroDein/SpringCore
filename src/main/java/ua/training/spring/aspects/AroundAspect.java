package ua.training.spring.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import ua.training.spring.Event;
import ua.training.spring.EventLogger;
import ua.training.spring.EventType;

/**
 * Created by Dmytro_Deinichenko on 3/20/2017.
 */
@Aspect
public class AroundAspect {

    private static final int MAX_COUNT = 1;
    private static int consoleCallsCount = 0;
    private EventLogger otherLogger;

    public AroundAspect(EventLogger otherLogger) {
        this.otherLogger = otherLogger;
    }

    @Pointcut("execution (* ua.training.spring.ConsoleEventLogger.logEvent(..))")
    public void consoleLoggerMethod(){}


    @Around("consoleLoggerMethod() && args(evt)")
    public void aroundLogEvent(ProceedingJoinPoint pjp, Object evt){
        consoleCallsCount++;
        if (consoleCallsCount <= MAX_COUNT){
            try {
                pjp.proceed(new Object[]{evt});
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            System.out.println("AroundAspect: called typical logger.");
        } else {
            otherLogger.logEvent((Event)evt);
            System.out.println("AroundAspect: called other logger.");
        }


    }
}
