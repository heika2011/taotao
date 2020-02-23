package cn.tt.user.service.serviceImpl.user;

import cn.tt.common.exception.ServiceException;
import cn.tt.common.pojo.User;
import cn.tt.common.service.user.UserService;
import cn.tt.user.mapper.UserMapper;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

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
    public void register(User user) {
        //获取到用户名为空

        if(user.getUsername()==null||user.getUsername()==""){
            throw  new IllegalArgumentException("参数异常");
        }
        //获取到密码为空
        if(user.getPassword()==null||user.getPassword()==""){
            throw  new IllegalArgumentException("参数异常");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("username",user.getUsername());

        if (userMapper.selectOne(queryWrapper)!=null){
            throw  new IllegalArgumentException("用户名存在");


        }
        String salt = UUID.randomUUID().toString();
        String password =  DigestUtils.md5DigestAsHex((salt+user.getPassword()).getBytes());
        user.setCreateTime(new Date());
        user.setPassword(password);
        user.setSald(salt);
        userMapper.insert(user);
    }

    //判断用户是否存在
    @Override
    public User ifExist(User user) {
        //获取到用户名为空
        if(user.getUsername()==null||user.getUsername()==""){
            throw  new IllegalArgumentException("参数异常");
        }
        //获取到密码为空
        if(user.getPassword()==null||user.getPassword()==""){
            throw  new IllegalArgumentException("参数异常");
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>(user);

        User user1 = userMapper.selectOne(userQueryWrapper);
        if(user1==null){
        throw  new ServiceException("用户不存在");
    }
        return user1;
    }
}
