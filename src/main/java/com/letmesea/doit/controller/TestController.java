package com.letmesea.doit.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {
    private static Logger logger = Logger.getLogger(TestController.class);
    @Value("${server.port}")
    private String port;

    @GetMapping("/test")
    public String test(){
        logger.info(1111);
        System.out.println(port);
        return "test";
    }
}
