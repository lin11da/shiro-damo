package com.example.controller;

import com.example.mapper.UserMapper;
import com.example.mapper.UserMapperServer;
import com.example.pojo.User;
import com.example.util.SaltUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * TODO
 *
 * @author chen
 * @version 1.0
 * @date 2021/10/26 17:11
 */
@RestController
public class UserController {


    @Autowired
    UserMapper userMapper;

    @Autowired
    UserMapperServer userMapperServer;

    /**
     * 用来处理身份认证
     * @param number
     * @param password
     * @return
     */
    @GetMapping("/login")
    public String login(@RequestParam("number")String number,@RequestParam("password")String password){

        //获取主体对象
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(new UsernamePasswordToken(number,password));
            return "yes";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误！");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            System.out.println("密码错误");
        }
        return "no";
    }

    /**
     * 退出登录
     */
    @GetMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();  //退出登录
        return "退出成功";
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public String register(@RequestBody User user){
        String salt = SaltUtils.getSalt(16);
        user.setUserid(salt);
        Integer register = userMapperServer.register(user);
        if (register==1){
            return "yes";
        }else {
            return "no";
        }
    }

    /**
     * 测试
     * @return
     */
    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
