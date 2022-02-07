package com.lagou.edu;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableEurekaClient  // 开启Eureka Client（Eureka独有）
@EnableDiscoveryClient // 开启注册中心客户端 （通用型注解，比如注册到Eureka、Nacos等）
// 说明：从SpringCloud的Edgware版本开始，不加注解也ok，但是建议大家加上
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
