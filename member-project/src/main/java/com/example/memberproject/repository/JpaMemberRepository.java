package com.example.memberproject.repository;

import com.example.memberproject.domain.Member;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Transactional
public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em; // jpa는 EntityManager로 모든게 동작

    public JpaMemberRepository(EntityManager em) { // 스프링 부트가 자동으로 EntityManager 생성, 만들어 진 것을 그냥 injection 받으면 된다
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // persist 영속하다, 영구저장하다
        return member; // jpa가 insert 쿼리 다 만들어서 db에 집어넣고, setId까지 해줌
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); // 조회할 타입, pk
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class) // jpql : 객체지향 쿼리 언어
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) // 객체 자체를 select
                .getResultList(); // jpql : 객체(entity)를 대상으로 쿼리를 날림, 그럼 sql로 번역
    }
}
