package com.hj.pay;

import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author hj
 * @data 2023/4/13 15:21
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients(basePackages = {"com.hj","com.ruoyi"})
@SpringBootApplication
@MapperScan("com.hj.pay.mapper")
public class PayApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(PayApplication.class, args);
    }
}
