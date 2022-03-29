package com.pado.SpringBootPractice.repository;

import com.pado.SpringBootPractice.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//jpareppository에 객체 / id타입
// 구현체를 자동으로 만들어준다. 그걸 우리는 가져다 쓰면 된다.
public interface SpringDataJpaMemberRepository {//extends JpaRepository<Member, Long>, MemberRepository {

    // 이게 끝난거다...
    // 쿼리 규칙이 있다.
    // Jpql select m from Member m where m.name =?
    // 인터페이스 명 만으로도 자동으로 쿼리를 짜줌.
    //@Override
    //Optional<Member> findByName(String name);
}
