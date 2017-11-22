package com.jh;

import org.junit.Assert;
import org.junit.Test;

public class AuthorizerTest extends BaseTest {
    @Test
    public void testIsPermitted(){
        login("classpath:shiro-authorizer.ini", "zhang", "123");
        //判断拥有权限：user:create
        Assert.assertTrue(subject().isPermitted("user1:update"));
        Assert.assertTrue(subject().isPermitted("user2:update"));
        //通过二进制位的方式表示权限
        Assert.assertTrue(subject().isPermitted("+user1+2"));//新增权限
        Assert.assertTrue(subject().isPermitted("+user1+8"));//查看权限
        Assert.assertTrue(subject().isPermitted("+user2+10"));//新增及查看

        Assert.assertFalse(subject().isPermitted("+user1+4"));//没有删除权限

        Assert.assertTrue(subject().isPermitted("menu:view"));//通过MyRolePermissionResolver解析得到的权限
    }

    @Test
    public void testIsPermitted2() {
        login("classpath:shiro-jdbc-authorizer.ini", "zhang", "123");
        //判断拥有权限：user:create
        Assert.assertTrue(subject().isPermitted("user1:update"));
        System.out.println("user1:update=="+subject().isPermitted("user1:update"));

        Assert.assertTrue(subject().isPermitted("user2:update"));
        System.out.println("user2:update=="+subject().isPermitted("user2:update"));
        //通过二进制位的方式表示权限
        Assert.assertTrue(subject().isPermitted("+user1+2"));//修改权限
        System.out.println("+user1+2=="+subject().isPermitted("+user1+2"));

        Assert.assertTrue(subject().isPermitted("+user1+8"));//查看权限
        System.out.println("+user1+8"+subject().isPermitted("+user1+8"));

        Assert.assertTrue(subject().isPermitted("+user2+10"));//修改及查看
        System.out.println("+user2+10=="+subject().isPermitted("+user2+10"));

        Assert.assertFalse(subject().isPermitted("+user1+4"));//没有删除权限
        System.out.println("+user1+4=="+subject().isPermitted("+user1+4"));

        Assert.assertTrue(subject().isPermitted("menu:view"));//通过MyRolePermissionResolver解析得到的权限
        System.out.println("menu:view=="+subject().isPermitted("menu:view"));
    }
}
