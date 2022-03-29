package com.pado.SpringBootPractice.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// 이 어노테이션 해야 aop 적용 가능
@Aspect
@Component
public class TimeTraceAop {

    @Around("execution(* com.pado.SpringBootPractice..*(..))") // 패키지 하위에 다 적용
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START: "  + joinPoint.toString());
        try {
            Object result = joinPoint.proceed(); // 다음 메소드로 진행
            return result;
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + " ms");
        }
    }
}
