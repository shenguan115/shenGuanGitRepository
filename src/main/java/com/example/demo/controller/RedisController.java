package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/test")
    public JSONObject test(){
        stringRedisTemplate.opsForValue().set("name","沈观同学");

        return (JSONObject) new JSONObject().put("resultCode","ok");
    }

    @RequestMapping("/get")
    public JSONObject get(){
        JSONObject json = new JSONObject();
        String name = stringRedisTemplate.opsForValue().get("name");
        json.put("data",name);
        json.put("resultCode","ok");
        return json;
    }
}
