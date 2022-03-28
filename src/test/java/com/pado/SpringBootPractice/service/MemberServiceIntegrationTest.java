package com.pado.SpringBootPractice.service;

import com.pado.SpringBootPractice.domain.Member;
import com.pado.SpringBootPractice.repository.MemberRepository;
import com.pado.SpringBootPractice.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    // tc는 생성자 인젝션까지 할 필요 없이.. 제일 간단하게 필드 인젝션 해줘도 된다
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    // 다른 객체 레퍼지토리가 생성이 되면.. 조금 애매
    // MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    // 실행하기 전마다, 같은 메모리 멤버 리포지토리를 사용함.
    // private으로 만들어진 멤버리포지토리에 새로 생성한 멤보 리포지터리 값을 넣어주는 방식으로 사용
    // 멤버서비스 입장에서 외부에서 멤버레포지토리를 넣어줌. 이런것을 DI,
    // dependency injection이라 한다.
//    @BeforeEach
//    public void beforeEach(){
//        memberRepository = new MemoryMemberRepository();
//        memberService = new MemberService(memberRepository);
//    }

//    @AfterEach
//    public void afterEach(){
//        memberRepository.clearStore();
//    }
//
    @Test
    void join() {
        // given 이런 상황이주어졌을 때
        Member member = new Member();
        member.setName("spring");

        // when 이 함수를 실행하면
        Long saveId = memberService.join(member);

        // then 이렇게 되어야함
        // 우리가 저장한게 리포지토리에 있는게 맞는지?
        Member findMember =  memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1= new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        // class / 함수를 인풋으로 받나봄. 람다가 넘어가야돼서. 람다 완전 ㅇㅋ
        // 이 로직 함수 실행할 떄, 이 익셉션이 터지면 성공! > 원할떄 익셉션 터졌으니 테스트 결과도 성공
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미존재");


        //        try{
//            memberService.join(member2); // 여기서 이슈터져서 catch로 감
//            fail();
//        }catch(IllegalStateException e){ // 예외 발생할 경우 catch를 실
//            assertThat(e.getMessage()).isEqualTo("이미존재");
//        }

        //then
    }


}