package com.nibm.mothercare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * MotherCarePlus Spring Boot Application Entry Point.
 * Replaces the plain PHP scripts in MotherCareAPI/.
 *
 * Run:  mvn spring-boot:run
 * JAR:  mvn clean package  →  java -jar target/mothercare-api-1.0.0.jar
 */
@SpringBootApplication
public class MotherCareApplication {

    public static void main(String[] args) {
        SpringApplication.run(MotherCareApplication.class, args);
    }
}
