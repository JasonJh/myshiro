package com.jh;

import org.apache.shiro.authz.UnauthorizedException;
import org.junit.Assert;
import org.junit.Test;

public class PermissionTest extends BaseTest {

    @Test
    public void testIsPermission(){
        login("classpath:shiro-permission.ini","zhang","123");
        //判断拥有权限：user：create
        Assert.assertTrue(subject().isPermitted("user:create"));
        System.out.println("user:create "+subject().isPermitted("user:create"));

        //判断拥有权限:user:update and user:delete
        Assert.assertTrue(subject().isPermittedAll("user:update", "user:delete"));
        System.out.println("user:update,user:delete "+subject().isPermittedAll("user:update", "user:delete"));

        //判断没有权限：user:view
        Assert.assertFalse(subject().isPermitted("user:view"));
        System.out.println("user:view "+subject().isPermitted("user:view"));
    }

    @Test(expected = UnauthorizedException.class)
    public void testCheckPermission(){
        login("classpath:shiro-permission.ini", "zhang", "123");
        //断言拥有权限：user:create
        subject().checkPermissions("user:create");
        //断言拥有权限：user:delete and user:update
        subject().checkPermissions("user:delete", "user:update");
        //断言拥有权限：user:view 失败抛出异常
        subject().checkPermissions("user:view");
    }
}

