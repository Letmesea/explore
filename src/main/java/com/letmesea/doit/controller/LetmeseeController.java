package com.letmesea.doit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/see")
public class LetmeseeController {
    @GetMapping(value = "/ssq")
    public void getSsq(){

    }
    @GetMapping("/dlt")
    public void getDlt(){

    }
}
