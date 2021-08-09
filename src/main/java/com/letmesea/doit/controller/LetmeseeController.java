package com.letmesea.doit.controller;

import com.letmesea.doit.service.DealService;
import com.letmesea.doit.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/see")
@Slf4j
public class LetmeseeController {
    private static Logger logger = Logger.getLogger(LetmeseeController.class);
    @Autowired
    private DealService dealService;
    @Value("${server.port}")
    private String port;

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
        logger.info("端口"+port);
        return "test";
    }
    @GetMapping("/testlb")
    @ResponseBody
    public String testlb(){
        logger.info("端口"+port);
        return port;
    }

}
