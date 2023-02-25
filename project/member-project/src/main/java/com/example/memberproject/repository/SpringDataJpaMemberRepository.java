package com.example.memberproject.repository;

import com.example.memberproject.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository { // interface가 interface를 받을 땐 implements가 아니라 extends

    // JPQL select m from Member m where m.name = ? 을 짜준다.
    @Override
    Optional<Member> findByName(String name);
}
