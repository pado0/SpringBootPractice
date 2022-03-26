package com.pado.SpringBootPractice;

import com.pado.SpringBootPractice.repository.MemberRepository;
import com.pado.SpringBootPractice.repository.MemoryMemberRepository;
import com.pado.SpringBootPractice.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// component scan을 이용하지 않고 수동으로 빈 설정하는 방법
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
