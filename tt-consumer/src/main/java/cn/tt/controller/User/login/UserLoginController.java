package cn.tt.controller.User.login;

import cn.tt.common.pojo.User;
import cn.tt.common.service.user.UserService;
import cn.tt.common.util.CookieUtil;
import cn.tt.common.vo.JSONResult;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 无恙
 * 登陆业务
 */
@RestController
public class UserLoginController {

    @Reference
    private UserService userService;

    /** 注册 */

    @PostMapping("/user/res")
    public JSONResult Userres(@RequestBody User user, HttpServletRequest servletRequest){
        CookieUtil.getData(user.getCode(),servletRequest.getCookies());
        userService.register(user);
        return new JSONResult();
    }

    /** 登陆页面 */
    @PostMapping("/user/dologin")

    public JSONResult Userlogin(  @RequestBody User user){
        userService.ifExist(user);
        return new JSONResult();
    }

}
