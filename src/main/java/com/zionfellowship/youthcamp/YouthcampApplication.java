package com.zionfellowship.youthcamp;

import com.zionfellowship.youthcamp.config.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class YouthcampApplication {

	public static void main(String[] args) {
		SpringApplication.run(
				YouthcampApplication.class,
				args
		);
	}
}