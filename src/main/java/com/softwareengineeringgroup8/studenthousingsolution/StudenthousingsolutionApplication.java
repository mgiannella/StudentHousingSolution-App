package com.softwareengineeringgroup8.studenthousingsolution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import org.springframework.web.bind.annotation.*;
import com.softwareengineeringgroup8.studenthousingsolution.controller.*;

@SpringBootApplication

public class StudenthousingsolutionApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudenthousingsolutionApplication.class, args);
    }

}
