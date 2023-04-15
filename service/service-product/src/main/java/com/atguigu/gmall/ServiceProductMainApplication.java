package com.atguigu.gmall;

import com.atguigu.gmall.common.config.Swagger2Config;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Import;

@SpringCloudApplication
@Import(Swagger2Config.class)
@MapperScan(basePackages = "com.atguigu.gmall.mapper")
public class ServiceProductMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceProductMainApplication.class,args);

    }
}
