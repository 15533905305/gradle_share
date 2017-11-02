package com.share;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
@SpringBootApplication
@MapperScan("com.share.dao")
public class GradleShareApplication {

	public static void main(String[] args) {
		SpringApplication.run(GradleShareApplication.class, args);
	}
}
