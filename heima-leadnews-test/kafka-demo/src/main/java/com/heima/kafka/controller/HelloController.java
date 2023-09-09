package com.heima.kafka.controller;

import com.alibaba.fastjson.JSON;
import com.heima.kafka.pojo.User;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Date: 2023/7/24 21:58
 * @author: Qeem
 */
@RestController
public class HelloController {
    @Resource
    private KafkaTemplate<String,String> kafkaTemplate;
    @GetMapping("/hello")
    public String hello(){
//        kafkaTemplate.send("itcast-topic","Qeem简直是个天才!");
        User user = new User();
        user.setAge(9);
        user.setName("Qeem");
        kafkaTemplate.send("user-topic", JSON.toJSONString(user));
        return "ok";
    }
}
