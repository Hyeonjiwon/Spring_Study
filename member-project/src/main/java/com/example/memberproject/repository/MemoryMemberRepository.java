package com.example.memberproject.repository;

import com.example.memberproject.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberReository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // 0,1,2 .. key 값을 생성해 주는 역할

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // id 증가
        store.put(member.getId(), member); // store를 map에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // null이 반환될 것을 대비해 Optional로 감싸기
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
