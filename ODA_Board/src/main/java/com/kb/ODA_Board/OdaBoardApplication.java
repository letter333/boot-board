package com.kb.ODA_Board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
public class OdaBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(OdaBoardApplication.class, args);
	}

	// delete, put mapping을 위한 빈 등록
	@Bean
	public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
		return new HiddenHttpMethodFilter();
	}
}
