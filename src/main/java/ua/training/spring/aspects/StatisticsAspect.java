package ua.training.spring.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dmytro_Deinichenko on 3/20/2017.
 */

@Aspect
public class StatisticsAspect {

    private Map<Class<?>, Integer> counter = new HashMap<>();

    public Map<Class<?>, Integer> getCounter() {
        return counter;
    }

    @Pointcut("execution (* ua.training.spring.*EventLogger.logEvent(..))")
    private void allLogEventsMethod(){}

    @AfterReturning ("allLogEventsMethod()")
    public void count(JoinPoint joinPoint){
        Class<?> clazz = joinPoint.getTarget().getClass();
        if(!counter.containsKey(clazz)){
            counter.put(clazz, 0);
        }
        counter.put(clazz, counter.get(clazz)+1);
    }

}
