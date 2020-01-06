package cn.tt.user.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

@Service
public class TestService implements cn.tt.common.service.TestService {

    @Autowired
    private Jedis jedis;

    @Override
    public String test() {
        jedis.set("1","2++++++++++++++++++++++++++++");
        System.out.println(jedis.get("1"));
        return "hello";
    }
}
