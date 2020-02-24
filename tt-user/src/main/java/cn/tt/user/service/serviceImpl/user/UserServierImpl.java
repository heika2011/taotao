package cn.tt.user.service.serviceImpl.user;

import cn.tt.common.exception.ServiceException;
import cn.tt.common.pojo.User;
import cn.tt.common.service.user.UserService;
import cn.tt.common.util.CookieUtil;
import cn.tt.user.mapper.UserMapper;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * 无恙
 * user登陆
 */
@Service
public class UserServierImpl  implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(User user ) {

        //获取到用户名为空

        if(user.getUsername()==null||user.getUsername()==""){
            throw  new IllegalArgumentException("用户异常");
        }
        //获取到密码为空
        if(user.getPassword()==null||user.getPassword()==""){
            throw  new IllegalArgumentException("用户密码异常异常");
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("username",user.getUsername());

        if (userMapper.selectOne(queryWrapper)!=null){
            throw  new IllegalArgumentException("用户名存在");


        }
        //获取亚颜值
        String salt = UUID.randomUUID().toString();
        String password =  DigestUtils.md5DigestAsHex((salt+user.getPassword()).getBytes());
        user.setCreateTime(new Date());
        user.setPassword(password);
        user.setSald(salt);
        userMapper.insert(user);
    }

    //用户登录
    @Override
    public void ifExist(User user) {
        //获取到用户名为空
        if (user.getUsername() == null || user.getUsername() == "") {
            throw new IllegalArgumentException("账号不能为空");
        }
        //获取到密码为空
        if (user.getPassword() == null || user.getPassword() == "") {
            throw new IllegalArgumentException("密码不能空");
        }

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username",user.getUsername());
        User user_mysql= userMapper.selectOne(userQueryWrapper);
        if (user_mysql==null){
            throw  new IllegalArgumentException("用户不存在");
        }
        String newPasworld = DigestUtils.md5DigestAsHex((user_mysql.getSald()+user.getPassword()).getBytes());
        if (!newPasworld.equals(user_mysql.getPassword())){
          throw new  IllegalArgumentException("用户名或密码错误");
        }

    }
}
