package cn.tt.common.service.user;

import cn.tt.common.pojo.User;

public interface UserService {
     //注册
     void  register(User user);
     //登录
     User ifExist(User user);


}
