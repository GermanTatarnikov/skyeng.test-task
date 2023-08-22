package ru.gtatarnikov.skyeng.testtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan()
public class SkyengApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkyengApplication.class, args);
	}

}
