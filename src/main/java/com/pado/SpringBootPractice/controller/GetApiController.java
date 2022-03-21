package com.pado.SpringBootPractice.controller;

import com.pado.SpringBootPractice.dto.UserRequest;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController // 어노테이션으로 컨트롤러 명시. 해당 Class는 REST APi를 처리하는 컨트롤러
@RequestMapping("/api") // 주소 맵핑해주는 어노테이션
public class GetApiController {


    @GetMapping("/hello") // get 방식의 주소를 맵핑해주는 어노테이션
    public String hello(){

        // 위 주소로 호출시 클라이언트에 해당 값을 리턴한다
        // Html이 아닌 그냥 스트링값
        return "hello spring boot!";

    }

    // get method 만드는
    // 방법 1. get Mapping
    @GetMapping(path = "/gethello") // http://localhost:8080/api/gethello
    public String getHello(){
        return "get hello";
    }

    // 방법2. Requestmapping시 모든 메소드(delete post) 등이 다 만들어지는데
    // get만 사용하도록. 이건 옛날 방식이므로, getMapping을 하자.
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String h1(){
        return "hi";
    }

    // 방법3. path variable 사용법. 변화하는 값 받기.
    // http://localhost:8080/api/path-variable/{name}
    @GetMapping("/path-variable/{name}")
    public String pathVariable(@PathVariable String name){

        System.out.println("PathVariable : " + name);
        return name;
    }

    // 방법4. path variable 명과 변수명 매칭시키는 법
    // http://localhost:8080/api/path-variable/{name}
    @GetMapping("/path-variable2/{id}")
    public String pathVariable2(@PathVariable(name = "id") String pathName){ // 어느 이름에 매칭시킬것인지, name=

        System.out.println("PathVariable : " + pathName);
        return pathName;
    }

    // 방법5. Query Parameter
    // 물음표 뒤에 붙은게 쿼리파라미터
    // localhost:8080/api/query-param?user=steve&email=stv@gmail.com&age=30
    @GetMapping(path = "/query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam){

        StringBuilder sb = new StringBuilder();
        // 람다식 공부하기
        queryParam.entrySet().forEach(entry ->{
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());

            sb.append(entry.getKey() +" = " + entry.getValue() +"\n");
        });
        return sb.toString();
    }

    // 방법6. 지정할 쿼리파람 종류가 명확할때 (방법 5는 map은 모든 키를 받을 수 있다.)
    @GetMapping(path = "/query-param2")
    public String queryParam2(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam int age){

        return name + " " + email + " " + age ;
    }

    // 방법7. 파라미터가 너무 많아질때 DTO를 사용한다. 현업에서 제일 많이 씀.!! 중요!!
    @GetMapping(path = "/query-param3")

    //객체에 들어있지 않은 값은 자동으로 누락하여 파싱함.
    public String queryParam2(UserRequest userRequest){

        System.out.println(userRequest.getName());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());


        return userRequest.toString();
    }

}
