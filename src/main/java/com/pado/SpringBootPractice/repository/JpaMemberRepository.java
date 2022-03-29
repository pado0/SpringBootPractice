package com.pado.SpringBootPractice.repository;

import com.pado.SpringBootPractice.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    // jpa는 Entity manager를 기준으로 모든게 동작함
    // data-jpa를 Implement 해주면 spring jpa가 알아서 엔티티 매니저를 만들어준다
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // member를 em에 영구 저정, 이렇게 하면 jpa가 insert query, id세팅까지 다 해준다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); // 조회할 타입, 식별자 pk값 넣어주면 조회가 됨
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) { // 키가 아닌 다른 값으로 찾을 땐 jpql이라는 문법을 이용해야한다.
        //아래 쿼리에서 명확한 클래스명 Member를 사용할 것
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findALl() {
        // member 엔티티를 조회하고, 멤버 객체 m 자체를 select할 수 있다.
        return em.createQuery("select m from Member as m", Member.class).getResultList();
    }
}
