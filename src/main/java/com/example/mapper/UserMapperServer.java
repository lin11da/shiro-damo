package com.example.mapper;

import com.example.pojo.User;
import com.example.util.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO
 *
 * @author chen
 * @version 1.0
 * @date 2021/10/26 20:37
 */
@Service
@Transactional
public class UserMapperServer implements UserMapper{

    @Autowired
    UserMapper userMapper;

    @Override
    public Integer register(User user) {
        //处理业务调用dao

        //1.生成随机盐
        String salt = SaltUtils.getSalt(8);
        //2.将岁挤眼保存到数据库
        user.setSalt(salt);
        //铭文密码进行md5 + salt +hash散列
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);
        user.setPassword(md5Hash.toHex());

        return userMapper.register(user);
    }

    //首先拿到number  查询出数据后 框架会自动匹配密码
    @Override
    public User findbyUserName(String number) {
        return userMapper.findbyUserName(number);
    }


}
