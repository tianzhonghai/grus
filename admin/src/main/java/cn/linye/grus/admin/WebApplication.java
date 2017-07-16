package cn.linye.grus.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Tim on 2017/7/16.
 */
@SpringBootApplication
@ComponentScan("cn.linye.grus")
public class WebApplication {
    public static void main(String[] args){
        SpringApplication.run(WebApplication.class,args);
    }
}
