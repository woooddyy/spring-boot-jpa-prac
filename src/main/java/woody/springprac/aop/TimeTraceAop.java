package woody.springprac.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class TimeTraceAop {

    @Around("execution(* woody.springprac..*(..)) && !target(woody.springprac.SpringConfig)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START: "+joinPoint.toString());
        try{
            Object result = joinPoint.proceed();
            return result;
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: "+joinPoint.toString()+" "+timeMs+"ms");
        }
    }
}
