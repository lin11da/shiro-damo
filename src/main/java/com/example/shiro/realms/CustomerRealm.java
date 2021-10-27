package com.example.shiro.realms;

import com.example.mapper.UserMapperServer;
import com.example.pojo.User;
import com.example.util.ApplicationContextUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ObjectUtils;

/**
 * TODO
 *
 * @author chen
 * @version 1.0
 * @date 2021/10/26 16:26
 */
//自定义realm
public class CustomerRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        //获取身份信息
        String primaryPrincipal = principals.getPrimaryPrincipal().toString();

        System.out.println("调用授权验证:" + primaryPrincipal);

        //根据主身份信息获取角色 和 权限信息
        if ("123".equals(primaryPrincipal)){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

            simpleAuthorizationInfo.addRole("admin");

            return simpleAuthorizationInfo;
        }

        return null;
    }



    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("=====================");
        String principal = token.getPrincipal().toString();

        //在工厂中获取service对象
        UserMapperServer userMapperServer = (UserMapperServer)ApplicationContextUtils.getBean("userMapperServer");

        User user = userMapperServer.findbyUserName(principal);

        if (!ObjectUtils.isEmpty(user)){
            return new SimpleAuthenticationInfo(user.getNumber(),user.getPassword(), ByteSource.Util.bytes(user.getSalt()), this.getName());
        }
//        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal,"123",this.getName());
//        if ("xiaochen".equals(principal)){
//            return simpleAuthenticationInfo;
//        }
        return null;
    }
}
