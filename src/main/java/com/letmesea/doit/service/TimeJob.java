package com.letmesea.doit.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@Slf4j
public class TimeJob {

    private final DealService dealService;

    @Autowired
    public TimeJob(DealService dealService) {
        this.dealService = dealService;
    }

    @Scheduled(cron = "0 0 22 ? * TUE,THU,SUN")
    private void ssq(){
        log.info("定时任务执行...");
        dealService.batchInsert();
    }
    @Scheduled(cron = "0 0 21 ? * MON,WED,SAT")
    private void dlt(){

    }

//    @Scheduled(cron = "0/1 * * * * ?")
    private void test(){
        log.info("32");
    }
}
