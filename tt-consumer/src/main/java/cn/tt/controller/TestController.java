package cn.tt.controller;

import cn.tt.common.service.TestService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @Reference
    private TestService testService;

    @RequestMapping("/index")
    public String test(){
        System.out.println(testService.test());
        return "home3";
    }
}
