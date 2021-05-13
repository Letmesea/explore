package com.letmesea.doit;

import com.alibaba.fastjson.JSON;
import com.letmesea.doit.pojo.Kj;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Optional;
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
        Kj kj = new Kj();
        Optional.ofNullable(kj)
                .map(changeOrderRes1 -> {
                    changeOrderRes1.getNumber();
                    return changeOrderRes1;
                }).orElse(null);

        Set<Integer> set = new HashSet<>();
        Set<Integer> set1 = new HashSet<>();
        for (int i=1;i<=5;){
            Random random=new Random();
            if(set.add(random.nextInt(35)+1)){
                i++;
            }
        }
        System.out.println(JSON.toJSONString(set));
        /***/
        int sum =0;
        for (int i=1;i<=2;){
            Random random=new Random();
            if(set1.add( random.nextInt(12)+1)){
                i++;
            }
        }
//        System.out.println(JSON.toJSONString(set1));
        System.out.println(JSON.toJSONString(set1));
    }
}
