package com.letmesea.doit.controller;

import com.letmesea.doit.dto.WinHistory;
import com.letmesea.doit.pojo.Kj;
import com.letmesea.doit.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/see")
public class LetmeseeController {
    @Autowired
    private DealService dealService;
    @GetMapping(value = "/ssq")
    public List<Kj> getSsq(){
        return dealService.getData();
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
}
