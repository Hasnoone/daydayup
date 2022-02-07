package com.lagou.edu.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/autodeliver")
public class AutodeliverController {

    @Autowired
    private RestTemplate restTemplate;


    // /autodeliver/checkState/1545132 老版
/*    @GetMapping("/checkState/{userId}")
    public Integer findResumeOpenState(@PathVariable Long userId) {
        // 调用远程服务—> 简历微服务接口  RestTemplate  -> JdbcTempate
        // httpclient封装好多内容进行远程调用
        Integer forObject = restTemplate.getForObject("http://localhost:8080/resume/openstate/" + userId, Integer.class);
        return forObject;
    }*/


    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 服务注册到eureka后得改造
     * @param userId
     * @return
     */
    @GetMapping("/checkState/{userId}")
    public Integer findResumeOpenState(@PathVariable Long userId) {
        // TODO 从Eureka Server中获取我们关注的那个服务的实例信息以及接口信息
        // 1、从 Eureka Server中获取lagou-service-resume服务的实例信息（使用客户端对象做这件事）
        List<ServiceInstance> instances = discoveryClient.getInstances("LAGOU-RESUME-SERVICE");
        // 2、如果有多个实例，选择一个使用(负载均衡的过程)
        ServiceInstance serviceInstance = instances.get(0);
        // 3、从元数据信息获取host port
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        String url = "http://" + host + ":" + port + "/resume/openstate/" + userId;
        System.out.println("===============>>>从EurekaServer集群获取服务实例拼接的url：" + url);
        // 调用远程服务—> 简历微服务接口  RestTemplate  -> JdbcTempate
        // httpclient封装好多内容进行远程调用
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        return forObject;
    }



}
