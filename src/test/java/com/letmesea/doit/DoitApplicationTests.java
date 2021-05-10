package com.letmesea.doit;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


class DoitApplicationTests {

    @Test
    void contextLoads() {
        Set<Integer> set = new HashSet<>();
        for (int i=1;i<=6;){
            Random random=new Random();
            if(set.add(random.nextInt(33)+1)){
                i++;
            }
        }
        System.out.println(JSON.toJSONString(set));
    }

    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> set1 = new HashSet<>();
        for (int i=1;i<=6;){
            Random random=new Random();
            if(set.add(random.nextInt(33)+1)){
                i++;
            }
        }
        System.out.println(JSON.toJSONString(set));
        /***/
        int sum =0;
        for (int i=1;i<=16;i++){
            Random random=new Random();
            int te = random.nextInt(16)+1;
            sum+=te;
        }
//        System.out.println(JSON.toJSONString(set1));
        System.out.println((float)(sum/16));
    }
}
