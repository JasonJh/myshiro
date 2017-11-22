package com.xh.realm;

import com.xh.entity.User;
import com.xh.service.UserService;
import com.xh.service.UserServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class UserRealm extends AuthorizingRealm {
    private UserService userService=new UserServiceImpl();


    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username=(String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(userService.findRoles(username));
        authorizationInfo.setStringPermissions(userService.findPermissions(username));
        return authorizationInfo;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String usrename=(String) authenticationToken.getPrincipal();//用户名
        User user = userService.findByUsername(usrename);
        if(user==null){
            throw new UnknownAccountException();//没找到账号
        }
        if(Boolean.TRUE.equals(user.getLocked())){
            throw new LockedAccountException();//账号锁定
        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以再此判断或自定义实现
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(
                user.getUsername(),//用户名
                user.getPassword(),//密码
                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
                getName() );
        return authenticationInfo;
    }
}
