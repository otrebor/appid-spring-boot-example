package com.example.appid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example"
//        , "com.ibm.cloud.appid.spring.boot"
})
public class AppidApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppidApplication.class, args);
    }
}
