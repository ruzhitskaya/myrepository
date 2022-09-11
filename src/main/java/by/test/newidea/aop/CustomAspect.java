package by.test.newidea.aop;

import by.test.newidea.SpringText;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
@Aspect
public class CustomAspect {

    private static final Logger log = Logger.getLogger(CustomAspect.class);

//    @Before("aroundRepositoryPointcut()")
//    public void logBefore(JoinPoint joinPoint) {
//        log.info("Method " + joinPoint.getSignature().getName() + " start");
//    }
//
//    @AfterReturning(pointcut = "aroundRepositoryPointcut()")
//    public void doAccessCheck(JoinPoint joinPoint) {
//        log.info("Method " + joinPoint.getSignature().getName() + " finished");
//    }

    @Pointcut("execution(* by.test.newidea.repository.jdbctemplate.JdbcTemplateUserRepository.*(..))")
    public void aroundRepositoryPointcut() {
    }

    @Around("aroundRepositoryPointcut()")
    public Object logAroundMethods(ProceedingJoinPoint joinPoint) throws Throwable {
//        passportsAndNames.put(162348, "Иван Михайлович Серебряков");
//        passportsAndNames.put(8082771, "Дональд Джон Трамп");
//
//        String lidiaName = passportsAndNames.get(212133);
//        System.out.println(lidiaName);
//
//
//        passportsAndNames.remove(162348);
//        System.out.println(passportsAndNames);


        System.out.println(joinPoint.getArgs().length);
        long beginTime = System.currentTimeMillis();

        //measuring elapsed time using Spring StopWatch
        StopWatch watch = new StopWatch();
        watch.start();

        log.info("Method " + joinPoint.getSignature().getName() + " start");


        Object proceed = joinPoint.proceed();

        watch.stop();

        log.info("Method " + joinPoint.getSignature().getName() + " finished");
        log.info("Total execution time StopWatch in nanoseconds: "
                + watch.getNanoTime());

        // статистика
        // Получаем имя метода
        String methodName = joinPoint.getSignature().getName();
        // Получаем имя класса
        String className = joinPoint.getSignature().getDeclaringTypeName();

      //  statistic.put(methodName,);
        if (SpringText.statistic.containsKey(methodName)) {
            Integer kol = SpringText.statistic.get(methodName);
            SpringText.statistic.put(methodName,++kol);
        }
        else {
            SpringText.statistic.put(methodName,1);
        };
        System.out.println("===="+ SpringText.statistic);
        Long myTime = System.currentTimeMillis () - beginTime;
        log.info ("Класс: "+ className + " Метод: " + methodName + " Время выполнения: " +  myTime + " миллисекунды");

        return proceed;
    }
}
