package com.xh.realm;

import com.xh.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class MyRealm3 implements Realm {
    public String getName() {
        return "C";
    }

    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        User user=new User("zhang","123");
        return new SimpleAuthenticationInfo(
                user,
                "123",
                getName()
        );
    }
}
