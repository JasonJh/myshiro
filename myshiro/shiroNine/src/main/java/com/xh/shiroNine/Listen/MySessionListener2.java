package com.xh.shiroNine.Listen;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;

public class MySessionListener2 extends SessionListenerAdapter {
    public void onStart(Session session) {
        System.out.println("会话创建：" + session.getId());
    }
}
