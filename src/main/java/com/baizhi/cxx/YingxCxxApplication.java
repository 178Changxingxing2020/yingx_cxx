package com.baizhi.cxx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.baizhi.cxx.dao")
public class YingxCxxApplication {

    public static void main(String[] args) {
        SpringApplication.run(YingxCxxApplication.class, args);
    }

}
