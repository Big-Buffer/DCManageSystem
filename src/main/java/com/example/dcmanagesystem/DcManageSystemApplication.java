package com.example.dcmanagesystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.example.dcmanagesystem.mapper")
@SpringBootApplication
//@ServletComponentScan
public class DcManageSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(DcManageSystemApplication.class, args);
    }

}
