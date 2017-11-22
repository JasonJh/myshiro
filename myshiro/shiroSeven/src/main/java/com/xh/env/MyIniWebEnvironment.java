package com.xh.env;

import org.apache.shiro.util.ClassUtils;
import org.apache.shiro.web.env.IniWebEnvironment;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.filter.mgt.*;

import javax.servlet.Filter;

public class MyIniWebEnvironment extends IniWebEnvironment {
    protected FilterChainResolver createFilterChainResolver(){
        //在此处扩展自己的FIlterChainResolver
        //1、创建FilterChainResolver
        PathMatchingFilterChainResolver filterChainResolver=new PathMatchingFilterChainResolver();
        //2创建FilterChainResolver
        DefaultFilterChainManager filterChainManager=new DefaultFilterChainManager();
        //3注册Filter
        for(DefaultFilter filter:DefaultFilter.values()){
              filterChainManager.addFilter(filter.name(), (Filter) ClassUtils.newInstance(filter.getFilterClass()));
        }
        //4注册URL-Filter的映射关系
        filterChainManager.addToChain("/login.jsp","authc");
        filterChainManager.addToChain("/unauthorized.jsp","anon");
        filterChainManager.addToChain("/**","authc");
        filterChainManager.addToChain("/**","roles","admin");

        //5设置Filter的属性
        FormAuthenticationFilter authcFilter= (FormAuthenticationFilter) filterChainManager.getFilter("authc");
        authcFilter.setLoginUrl("/login.jsp");
        RolesAuthorizationFilter rolesFilter= (RolesAuthorizationFilter) filterChainManager.getFilter("roles");
        rolesFilter.setUnauthorizedUrl("/unauthorized.jsp");

        filterChainResolver.setFilterChainManager(filterChainManager);
        return filterChainResolver;

    }
}
