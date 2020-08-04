package com.covid19tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication
@EnableScheduling
public class Covid19trackerApplication{
	
	public static void main(String[] args) throws Exception{
		SpringApplication.run(Covid19trackerApplication.class, args);
	
	}
}
