package com.example.memberproject.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
// @Component // 컴포넌트 스캔으로 등록해도 됨
// 컴포넌트 스캔보다 스프링 빈에 등록해서 쓰는것을 더 선호 -> SpringConfig
public class TimeTraceAop { // 이 클래스를 스프링 빈으로 등록해줘야 함

    // @Around("execution(* com.example.memberproject..*(..))") // 어떤 메소드에 적용할 것인지 타겟팅할 수 있음
    @Around("execution(* com.example.meberproject..*(..)) && !target(com.example.memberproject.SpringConfig)") // 스프링 빈에서 등록해서 쓸 경우 AOP 대상에서 SpringConfig를 빼줘야 한다.
    public Object execut(ProceedingJoinPoint joinPoint) throws Throwable {
        // 시간 로직
        long start = System.currentTimeMillis();
        System.out.println("STRAT: " + joinPoint.toString()); // 어떤 메소드를 call 하는지 이름을 얻어올  수 있음
        try {
            // 다음 메소드로 진행
            // Object result = joinPoint.proceed();// 다음 메소드로 진행
            // return result;
            return joinPoint.proceed(); // 인라인으로 변경

        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
