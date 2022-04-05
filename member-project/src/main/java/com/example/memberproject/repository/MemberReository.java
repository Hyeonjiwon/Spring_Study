package com.example.memberproject.repository;

import com.example.memberproject.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberReository {
    Member save(Member member); // 회원정보 저장
    // null 처리를 위해 Optional로 감싸기
    Optional<Member> findById(Long id); // id로 회원 찾기
    Optional<Member> findByName(String name); // name으로 회원 찾기
    List<Member> findAll(); // 모든 회원 list를 반환
}
