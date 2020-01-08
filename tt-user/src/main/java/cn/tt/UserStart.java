package cn.tt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * 用户启动类
 * @XIE XIN
 */

@SpringBootApplication
@MapperScan("cn.tt.user.mapper")
public class UserStart {

    public static void main(String[] args) {
        SpringApplication.run(UserStart.class,args);
        System.out.println("启动成功了");

    }

}
