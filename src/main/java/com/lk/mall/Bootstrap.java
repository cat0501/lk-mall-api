package com.lk.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author zhangjianlin
 * @description 启动类
 * @date 2022/8/28 3:18 PM
 */
@SpringBootApplication
@MapperScan("com.lk.mall.mapper")
public class Bootstrap {
    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
        System.out.println("  _      _  __                     _ _                   _ \n" +
                " | |    | |/ /                    | | |                 (_)\n" +
                " | |    | ' /_____ _ __ ___   __ _| | |______ __ _ _ __  _ \n" +
                " | |    |  <______| '_ ` _ \\ / _` | | |______/ _` | '_ \\| |\n" +
                " | |____| . \\     | | | | | | (_| | | |     | (_| | |_) | |\n" +
                " |______|_|\\_\\    |_| |_| |_|\\__,_|_|_|      \\__,_| .__/|_|\n" +
                "                                                  | |      \n" +
                "                                                  |_|      ");
    }
}
