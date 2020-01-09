package cn.tt.controller.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
