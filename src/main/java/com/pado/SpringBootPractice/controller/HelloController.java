package com.pado.SpringBootPractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring5.processor.SpringUErrorsTagProcessor;

@Controller
public class HelloController {

    @GetMapping("hello1")
    public String hello(Model model){
        model.addAttribute("data", "hello!");
        return "hello";
    }

    // 방법1.html 템플릿을 열어서 출력
    @GetMapping("hello-mvc")
    // requestparam을 받는다.
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model){
        // 모델에 담아 뷰에서 랜더링할때 사용
        model.addAttribute("name", name);
        // 헬로템플릿에 넘겨줌
        return "hello-template";
    }

    // 방법2. 스트링 그대로 출력
    @GetMapping("hello-spring")
    @ResponseBody // 응답의 바디 부분에 리턴값을 바로 넣어주겠다는 뜻이다. 뷰 없이 문자만 그대로 내려줌.
    public String helloSpring(@RequestParam("name") String name){
        return "hello" + name; // 뷰 없이 문자를 그대로 내려줌.
    }

    // 방법3. 객체를 받아 키벨류 형태의 json으로 출력
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){ // 객체를 넣었더니 json 형식으로 내려줌.
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    //자바빈 방식/ 프로퍼티 접근방식
    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
