package com.letmesea.doit.controller;

import com.alibaba.fastjson.JSON;
import com.letmesea.doit.dto.A;
import com.letmesea.doit.dto.WinHistory;
import com.letmesea.doit.pojo.Kj;
import com.letmesea.doit.service.DealService;
import com.letmesea.doit.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/see")
public class LetmeseeController {
    @Autowired
    private DealService dealService;
    @Autowired
    RedisUtils redisUtils;
    @GetMapping(value = "/ssq")
    public List<String> getSsq(){
        return dealService.getData();
    }
    @ResponseBody
    @GetMapping(value = "/ssq/{qi}")
    public List<String> getSsqn(@PathVariable("qi") Integer qi){
        return dealService.get100(qi);
    }
    @GetMapping(value = "/issq")
    public Integer insertSsq(){
        return dealService.batchInsert();
    }
    @GetMapping(value = "/ssqAll")
    public void insertSsqAll(){
         dealService.ssqAllNumber();
    }
    @GetMapping("/dlt")
    public void getDlt(){

    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }
    @GetMapping("/redis")
    @ResponseBody
    public void redisTest(){
        A a = new A();
        a.setAir("qq");
        redisUtils.set("12", JSON.toJSONString(a));
        com.letmesea.doit.dto.entity.A a1 = JSON.parseObject((String)redisUtils.get("12") , com.letmesea.doit.dto.entity.A.class);
    }
}
