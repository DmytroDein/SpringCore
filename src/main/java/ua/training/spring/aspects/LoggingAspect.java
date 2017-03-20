package ua.training.spring.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by Dmytro_Deinichenko on 3/20/2017.
 */
@Aspect
public class LoggingAspect {

    @Pointcut("execution (* ua.training.spring.*EventLogger.logEvent(..))")
    private void allLogEventsMethod(){
        System.out.println("allLogEventsMethod() called;");  // ??? why don't print anything?
    }

    @Before("allLogEventsMethod()")
    public void logBefore(JoinPoint joinPoint){
        System.out.println("BEFORE: " + joinPoint.getTarget().getClass().getSimpleName()
                + "  " + joinPoint.getSignature().getName());
    }
}
