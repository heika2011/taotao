package cn.tt.controller.User;

import cn.tt.common.pojo.User;
import cn.tt.common.service.UserService;
import cn.tt.common.vo.JSONResult;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 用户类请求
 *
 * @谢鑫
 */
@Controller
@RequestMapping("/user/")
public class UserController {





    /** 注册页面 */
    @RequestMapping("regist")
    public String registPage(){
        return "register";
    }
    /** 登陆页面 */
    @RequestMapping("login")
    public String Userlogin(){
        return "login";
    }
}
