package com.saikat.poc.sentimentAnalyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SentimentAnalyzerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SentimentAnalyzerApplication.class, args);
	}

}
