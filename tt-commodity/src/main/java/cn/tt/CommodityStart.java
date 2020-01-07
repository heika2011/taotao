package cn.tt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class CommodityStart {

    public static void main(String[] args) {

        SpringApplication.run(CommodityStart.class,args);
        System.out.println("cg");
    }
}