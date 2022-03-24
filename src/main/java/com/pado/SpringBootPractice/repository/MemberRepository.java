package com.pado.SpringBootPractice.repository;

import com.pado.SpringBootPractice.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name); // Optional: null반환 처리 방법
    List<Member> findALl();
}
