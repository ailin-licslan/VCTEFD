package com.lin.licslan.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author licslan
 */

@RestController
public class RedisController {


    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * 一般 save用POST 这里方便浏览器测试不用postman
     * 注解@postmapping
     */
    @GetMapping("/save")
    public String saveToRedis(@RequestParam("key")String key) {
        redisTemplate.opsForValue().set(key, 20000);
        return "ok";
    }


    /**
     * key = licslan
     * */
    @GetMapping("/get")
    public String getDataFromRedis(@RequestParam("key") String key) {
        Object o = redisTemplate.opsForValue().get(key);
        if (o != null && o.toString() != null) return o.toString();
        return "ok";
    }


    /**
     * 这里方便浏览器测试都用get
     * del key = licslan
     * 注解@Deletemapping
     * */
    @GetMapping("/del")
    public String delDataFromRedis(@RequestParam("key") String key) {
        Object o = redisTemplate.opsForValue().getAndDelete(key);
        if (o != null && o.toString() != null) return o.toString();
        return "ok";
    }
}
