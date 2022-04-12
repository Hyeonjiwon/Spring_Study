package com.example.memberproject;

import com.example.memberproject.repository.JdbcMemberRepository;
import com.example.memberproject.repository.JdbcTemplateMemberRepository;
import com.example.memberproject.repository.MemberRepository;
import com.example.memberproject.repository.MemoryMemberRepository;
import com.example.memberproject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    DataSource dataSource; // 스프링 부트가 자체적으로 만들어 줌

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
        // return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
