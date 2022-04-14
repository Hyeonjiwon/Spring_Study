package com.example.memberproject.service;

import com.example.memberproject.domain.Member;
import com.example.memberproject.repository.MemberRepository;
import com.example.memberproject.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// @Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    // 직접 생성하는 것이 아니라 외부에서 넣어주도록
    // @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {

        long start = System.currentTimeMillis(); // millisecond로 받을 수 있음

        // 로직이 끝날 때 시간을 찍어야 함
        try {
            validateDuplicateMember(member); // 중복 회원 검증
            memberRepository.save(member);
            return member.getId();
        } finally { // 예외가 생겨도 시간 출력
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }
    }

    private void validateDuplicateMember(Member member) {
        // 같은 이름이 있는 중복 회원 X
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { // null이 아닌 어떤 값이 있으면 동작
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        long start = System.currentTimeMillis();

        try {
            return memberRepository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers = " + timeMs + "ms");
        }
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
