package cn.tt.common.service.user;

import cn.tt.common.pojo.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public interface UserService  {
     //注册
     void  register(User user);
     //登录
     void ifExist(User user);


}
