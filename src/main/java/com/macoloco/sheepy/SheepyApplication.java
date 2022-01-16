package com.macoloco.sheepy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author Aya
 */
@SpringBootApplication
@MapperScan("com.macoloco.sheepy")
public class SheepyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SheepyApplication.class, args);
    }

}
