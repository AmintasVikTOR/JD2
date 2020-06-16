package by.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
@Aspect
public class LogAspect {

    private static final Logger log = Logger.getLogger(LogAspect.class);

    private static Map<String, Integer> methodInvocations = new ConcurrentHashMap<>();

    public static String showStatistics() {
        return methodInvocations.entrySet().stream().map(e -> e.getKey() + " " + e.getValue()).collect(Collectors.joining(","));
    }

    public static Map<String, Integer> getMethodInvocations() {
        return methodInvocations;
    }
//    private StopWatch stopWatch ;//= new StopWatch();
//
//    public LogAspect() {
//
//    }
//
//    public LogAspect(StopWatch stopWatch) {
//        this.stopWatch=stopWatch;
//    }

//    @Before("aroundRepositoryPointcut()")
//    public void logBefore(JoinPoint joinPoint) {
//        log.info("Method " + joinPoint.getSignature().getName() + " start");
//    }
//
//    @AfterReturning(pointcut = "aroundRepositoryPointcut()")
//    public void doAccessCheck(JoinPoint joinPoint) {
//        log.info("Method " + joinPoint.getSignature().getName() + " finished");
//    }

    @Pointcut("execution(* by.dao.jdbctemplate.UserRepository.*(..))")
    public void aroundRepositoryPointcut() {
    }

    @Around("aroundRepositoryPointcut()")
    public Object logAroundMethods(ProceedingJoinPoint joinPoint) throws Throwable {

        String mapKey = " \nIt's a statistics queries: " + joinPoint.getSignature().getDeclaringTypeName() + " " +
                joinPoint.getSignature().getName();

        Integer prevValue = methodInvocations.get(mapKey);
        methodInvocations.put(mapKey, prevValue == null ? 1 : prevValue + 1);

        log.info("Method " + joinPoint.getSignature().getName() + " START!");
        Object proceed = joinPoint.proceed();
        log.info("Method " + joinPoint.getSignature().getName() + " FINISH");
        return proceed;

    }


}
