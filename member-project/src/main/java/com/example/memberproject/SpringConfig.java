package com.example.memberproject;

import com.example.memberproject.repository.MemberRepository;
import com.example.memberproject.repository.MemoryMemberRepository;
import com.example.memberproject.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
