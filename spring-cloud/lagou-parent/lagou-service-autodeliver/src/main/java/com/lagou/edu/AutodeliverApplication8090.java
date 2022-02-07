package com.lagou.edu;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AutodeliverApplication8090 {

    public static void main(String[] args) {
        SpringApplication.run(AutodeliverApplication8090.class, args);
    }

    // 使用RestTemplate模板对象进行远程调用
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
