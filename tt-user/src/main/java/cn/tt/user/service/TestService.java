package cn.tt.user.service;

import com.alibaba.dubbo.config.annotation.Service;

@Service
public class TestService implements cn.tt.common.service.TestService {

    @Override
    public String test() {
        return "hello";
    }
}
