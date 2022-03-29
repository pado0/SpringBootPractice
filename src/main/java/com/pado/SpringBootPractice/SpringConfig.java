package com.pado.SpringBootPractice;

import com.pado.SpringBootPractice.aop.TimeTraceAop;
import com.pado.SpringBootPractice.repository.JpaMemberRepository;
import com.pado.SpringBootPractice.repository.MemberRepository;
import com.pado.SpringBootPractice.repository.MemoryMemberRepository;
import com.pado.SpringBootPractice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

// component scan을 이용하지 않고 수동으로 빈 설정하는 방법
@Configuration
public class SpringConfig {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }


    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        return new JpaMemberRepository(em);
    }

//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }
}
