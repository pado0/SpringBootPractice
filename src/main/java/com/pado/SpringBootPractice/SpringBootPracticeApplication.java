package com.pado.SpringBootPractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 스프링은 톰캣 웹서버를 내장하고 있다. SpringBootApplication 어노케이션 실행시 톰캣을 띄우며 서버를 실행한다.
@SpringBootApplication
public class SpringBootPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootPracticeApplication.class, args);
	}

}
