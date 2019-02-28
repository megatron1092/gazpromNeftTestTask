package ru.gazprom_neft.testTask.application;
// Created by IntelliJ IDEA.
// User: Sergey Telitsyn
// Date: 05.09.2018

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath*:task-context.xml")
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "ru.gazprom_neft.testTask")
public class TestTaskApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TestTaskApplication.class, args);
    }

}
