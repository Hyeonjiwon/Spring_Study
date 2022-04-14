package com.example.memberproject;

import com.example.memberproject.aop.TimeTraceAop;
import com.example.memberproject.repository.*;
import com.example.memberproject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private MemberRepository memberRepository;

    @Autowired // 생략 가능
    public SpringConfig(MemberRepository memberRepository) { // 스프링 컨테이너에서 MemberRepository를 찾는다.
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    /*
    @Bean // 스프링 빈에 직접 AOP 등록
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }*/

/*    @Bean
    public MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
        // return new JdbcMemberRepository(dataSource);
        // return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }*/

}
