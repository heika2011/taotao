package cn.tt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 用户启动类
 * @XIE XIN
 */
@SpringBootApplication
public class UserStart {

    public static void main(String[] args) {
        SpringApplication.run(UserStart.class,args);
    }
}
