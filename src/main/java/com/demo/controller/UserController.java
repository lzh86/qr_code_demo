package com.demo.controller;

import com.demo.constant.WebConst;
import com.demo.redisUtil.RedisTool;
import com.demo.service.impl.QRCodeServiceImpl;
import com.demo.utils.HttpUtils;
import com.demo.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api")
public class UserController {

    @Resource
    public QRCodeServiceImpl qrCodeService;

    @Autowired
    private RedisTool redisTool;

    @GetMapping(value = "/singin")
    public String hello(){
        return "login";
    }

    @GetMapping(value = "/enter")
    public String demo(HttpServletRequest httpServletRequest){
        String ipAddress = HttpUtils.getIPAddress(httpServletRequest);
        System.out.println("请求ip地址："  +  ipAddress);
        Object o = redisTool.get(ipAddress);
        if(o != null) {
            return "enter";
        }else {
            return "login";
        }
    }

    @GetMapping(value = "/index")
    public String index(HttpServletRequest httpServletRequest){
        String ipAddress = HttpUtils.getIPAddress(httpServletRequest);
        System.out.println("请求ip地址："  +  ipAddress);
        Object o = redisTool.get(ipAddress);
        if(o != null){
            return "index";
        }else {
            return "login";
        }
    }

    @RequestMapping(value = "/login/login")
    @ResponseBody
    public Result login(String userName, String passWord, HttpServletRequest httpServletRequest){
        Boolean login = qrCodeService.login(userName, passWord);
        if(login){
            String ipAddress = HttpUtils.getIPAddress(httpServletRequest);
            redisTool.set(ipAddress,"hello",30*60);
            System.out.println("ip地址登录成功："  +  ipAddress);
        }
        Result result = new Result();
        result.setCode(200);
        result.setMsg(WebConst.SUCCESS_RESULT);
        return result;
    }
}
