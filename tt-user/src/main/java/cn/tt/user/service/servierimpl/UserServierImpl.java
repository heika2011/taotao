package cn.tt.user.service.servierimpl;

import cn.tt.common.exception.ServiceException;
import cn.tt.common.pojo.User;
import cn.tt.common.service.UserService;
import cn.tt.user.mapper.UserMapper;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 无恙
 * user登陆
 */
@Service
public class UserServierImpl  implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User ifExist(User user) {
        if(user.getUsername()==null||user.getUsername()==""){
            throw  new ServiceException("参数异常");
        }
        if(user.getPassword()==null||user.getPassword()==""){
            throw  new ServiceException("参数异常");
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>(user);

        User user1 = userMapper.selectOne(userQueryWrapper);
        if(user1==null){
        throw  new ServiceException("用户不存在");
    }


        return user1;
    }
}
