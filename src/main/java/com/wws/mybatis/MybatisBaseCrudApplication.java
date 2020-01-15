package com.wws.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MybatisBaseCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisBaseCrudApplication.class, args);
    }

}
