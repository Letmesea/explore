package com.letmesea.doit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan({"com.letmesea.doit.dao"})
public class DoitApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DoitApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(DoitApplication.class, args);
//        new SpringApplicationBuilder(DoitApplication.class).properties("spring.config.name=app").run(args);
    }

}
