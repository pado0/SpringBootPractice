package com.pado.SpringBootPractice.repository;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.pado.SpringBootPractice.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.scope.ScopedProxyUtils;

import java.util.List;
import java.util.Optional;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();
    //테스트 끝날때마다 메모리 비우기

    @AfterEach // 이메소드 끝날때마다 동작하는 메소드
    public void afterEach(){
        repository.clearStore();
    }

    // save라는 메소드를 실행할 수 있게 됨.
    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring name");
        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        //new로 만든것과 저장소에서 꺼낸게 같으면 잘 된것
        // System.out.println("Result = " + (result == member));
        Assertions.assertEquals(member, result); // 오류나면 빨간불, gui로 테스트 결과를 보여주는게 어설션

    }

    @Test
    public void fineByName(){
        Member member1 = new Member();
        member1.setName("aaaa");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("bbbb");
        repository.save(member2);

        Member result1 = repository.findByName(member2.getName()).get();
        System.out.println(member2.getName() + "  " + result1.getName());
        Assertions.assertEquals(member2, result1);
    }

    @Test
    public void findAll(){
        Member member3 = new Member();
        member3.setName("aaaa");
        repository.save(member3);

        Member member4 = new Member();
        member4.setName("bbbb");
        repository.save(member4);

        List<Member> result3 = repository.findALl();
        Assertions.assertEquals(result3.size(), 2);
    }


}
