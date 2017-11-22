package org.apache.shiro.session.mgt;

import org.apache.shiro.session.Session;

public class OnlineSessionFactory implements SessionFactory {
    public Session createSession(SessionContext initData) {
        OnlineSession sesion=new OnlineSession();
        return null;
    }
}
