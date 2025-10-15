package com.education;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// github action을 통해서 CI구출
// CI를 통해서 자동으로 변경이 발생하면 DockerHub에 이미지를 자동으로 배포함
@SpringBootApplication
public class BookmarkerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookmarkerApiApplication.class, args);
	}

}
