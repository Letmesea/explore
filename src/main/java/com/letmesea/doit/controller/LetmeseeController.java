package com.letmesea.doit.controller;

import com.letmesea.doit.pojo.Kj;
import com.letmesea.doit.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/see")
public class LetmeseeController {
    @Autowired
    private DealService dealService;
    @GetMapping(value = "/ssq")
    public List<Kj> getSsq(){
        return dealService.getData();
    }
    @GetMapping(value = "/ssqr")
    public List<Kj> getSsqr(){
        return dealService.getData();
    }
    @GetMapping(value = "/issq")
    public Integer insertSsq(){
        return dealService.batchInsert();
    }

    @GetMapping("/dlt")
    public void getDlt(){

    }
}
