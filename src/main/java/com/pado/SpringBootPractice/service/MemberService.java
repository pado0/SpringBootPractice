package com.pado.SpringBootPractice.service;

import com.pado.SpringBootPractice.domain.Member;
import com.pado.SpringBootPractice.repository.MemberRepository;
import com.pado.SpringBootPractice.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

// 실제 비즈니스 로직을 작성하는 부분. 저장소 관리하는 리포지토리와 연동해서
// 서비스 로직 구현현// 서비스쪽은 비즈니스에 가까운 함수명을 지정해야한다.
//@Service // 스프링 컨테이너에서 관리하기 위한 어노테이션
@Transactional
public class MemberService {

    // 멤버 서비스에서 만든 멤버 메모리 레퍼지토리랑
    // 멤버 서비스 테스트에서 만든 멤버 메모리 레퍼지토리가 같았으면 좋겠음.
    // 각자 new해주면 지금은 다른 메소드
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    // 이렇게 바꾸면 된다. 외부에서 멤버리포지토리를 넣어주도록 바꾸면된다.
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }


    // 회원 가입
    // 같은 이름이 있는 회원은 안됨.
    public long join(Member member){

        long start = System.currentTimeMillis();

        try{
            validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " +timeMs + "ms");
        }
        //optional 바로 반환하는걸 권장하지 않음.
        //Optional<Member> result = memberRepository.findByName(member.getName());

        // 이렇게 구현

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(member1 -> {
                            throw  new IllegalStateException("이미존재");
                        });
    }

    // 전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findALl();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
