package com.pado.SpringBootPractice.controller;

// Member service를 통해 회원가입, 데이터 조회를 할 수 있어야 함

import com.pado.SpringBootPractice.domain.Member;
import com.pado.SpringBootPractice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    // 아래와 같이 new로 관리할 수도 있지만, 문제가 생긴다
    // 다른 컨트롤러에서 멤버 서비스를 생성할 경우, 불필요한 객체가 계속 생긴다.
    // private final MemberService memberService = new MemberService();

    // 이에 스프링 컨테이너에 등록을 해 딱 하나의 멤버 객체만 생성되게 한다.
    private final MemberService memberService;

    // Autowired를 쓰면 스프링 컨테이너에있는 멤버서비스 객체를 자동으로 연결해서 가져와준다.
    // 그런데 그냥 선언하면 멤버서비스를 찾을 수 없다고 나온다.
    // 0. @Controller
    // 1. @Service // 스프링 컨테이너에서 관리하기 위한 어노테이션을 서비스쪽에 넣어야된다.
    // 2. @Repository // 구현체 레퍼지토리에 어노테이션을 넣어준다
    // 3. @Autowired를 서비스에도 선언
    // 정리 : 정형화된 패턴임. 컨트롤러-외부요청받기, 서비스-비즈니스로직, 레퍼지토리-데이터 접근
    // DI: Autowired를 쓰면, 멤버 컨트롤러가 생성이 될 때 스프링 빈에 등록된 멤 버 서비스 객체를 넣어준다. 이게 바로 Dependency Injection !!!!
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member: " + member.getName());
        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";

    }

}
