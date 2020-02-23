package cn.tt.controller.User.login;

import cn.tt.common.pojo.User;
import cn.tt.common.service.user.UserService;
import cn.tt.common.vo.JSONResult;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 无恙
 * 登陆业务
 */
@RestController
public class UserLoginController {

    @Reference
    private UserService userService;

    /** 登陆页面 */
    @RequestMapping("/user/res")
    public JSONResult Userres(User user){
        userService.register(user);
        return new JSONResult();
    }

    /** 登陆页面 */
    @RequestMapping("/user/dologin")
    public JSONResult Userlogin(User user){
        userService.ifExist(user);
        return new JSONResult();
    }

}
