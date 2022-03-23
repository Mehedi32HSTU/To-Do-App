package com.spring.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ToDoAppApplication {
	public static Logger Log = LoggerFactory.getLogger(ToDoAppApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ToDoAppApplication.class, args);
		Log.info("Welcome to To-Do Application Software");
	}
}
