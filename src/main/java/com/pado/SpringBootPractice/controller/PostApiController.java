package com.pado.SpringBootPractice.controller;

import com.pado.SpringBootPractice.dto.PostRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/post-api")
public class PostApiController {

    // post에서 Request body로 받는 방법
    // 방법1. 일반변수
    @PostMapping("/post")
    public void post(@RequestBody Map<String, Object> requestData){ //body에 데이터 실을 것이므로 @Requestbody
        requestData.entrySet().forEach(stringObjectEntry -> {
            System.out.println("key: " +stringObjectEntry.getKey());
            System.out.println("value : " + stringObjectEntry.getValue());
        });
    }

    // 방법2. 객체로
    // Dto 객체로 요청을 받아옴. json key와 객체명이 같아야한다.
    @PostMapping("/post2")
    public void post2(@RequestBody PostRequestDto postRequestDto){
        System.out.println(postRequestDto.toString());
    }

    // 방법3. json 대소문자 맵핑으로 jsonProperty로 특정 이름 할당하기
    // camel case vs snake case
    // dto가 camel case인데 아무작업도 안해주면 오류가 난다.
    // object mapper가 snake case를 디폴트로 찾기 때문이다.
    // 이경우 dto쪽 가서 JsonProperty로 처리한다.
    @PostMapping("/post3")
    public void post3(@RequestBody PostRequestDto postRequestDto){
        System.out.println(postRequestDto.toString());
    }

}
