package com.xh.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class MyRealm2 extends AuthorizingRealm {

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username="liu";//用户名及salt1
        String salt2="0072273a5d87322163795118fdd7c45e";
        String password="be320beca57748ab9632c4121ccac0db"; //加密后的密码
        SimpleAuthenticationInfo ai=new SimpleAuthenticationInfo(username,password,getName());
        ai.setCredentialsSalt(ByteSource.Util.bytes(username+salt2));//盐是用户名+随机数
        return ai;
    }
}
