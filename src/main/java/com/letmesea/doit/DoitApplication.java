package com.letmesea.doit;

import com.letmesea.doit.config.DruidDataSourceConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@MapperScan({"com.letmesea.doit.dao"})
public class DoitApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoitApplication.class, args);
//        new SpringApplicationBuilder(DoitApplication.class).properties("spring.config.name=app").run(args);
    }

}
