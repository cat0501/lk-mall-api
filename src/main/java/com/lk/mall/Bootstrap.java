package com.lk.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhangjianlin
 * @description 启动类
 * @date 2022/8/28 3:18 PM
 */
@SpringBootApplication
@MapperScan("com.lk.mall.dao")
public class Bootstrap {
    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
    }
}
