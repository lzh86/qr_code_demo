package com.demo.controller;

import com.demo.redisUtil.RedisTool;
import com.demo.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api")
public class RedisController {
    @Autowired
    private RedisTool redisTool;

    @RequestMapping(value = "/add/redis")
    @ResponseBody
    public String addredis(HttpServletRequest httpServletRequest){
        String ipAddress = HttpUtils.getIPAddress(httpServletRequest);
        System.out.println(ipAddress);
        redisTool.set(ipAddress,"hello",100);
        Object hello = redisTool.get("hello");
        System.out.println(hello.toString());
        return hello.toString();
    }

    @RequestMapping(value = "/del/redis")
    @ResponseBody
    public String delRedis(HttpServletRequest httpServletRequest){
        String ipAddress = HttpUtils.getIPAddress(httpServletRequest);
        System.out.println(ipAddress);
        redisTool.del(ipAddress);
        return ipAddress;
    }

}
