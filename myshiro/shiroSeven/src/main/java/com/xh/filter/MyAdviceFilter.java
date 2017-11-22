package com.xh.filter;

import org.apache.shiro.web.servlet.AdviceFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyAdviceFilter extends AdviceFilter {
    protected boolean preHandle(ServletRequest request, ServletResponse response){
        System.out.println("====预处理/前置处理");
        return true;//返回false将中断后续过滤器链的执行
    }

    protected  void postHandle(ServletRequest request,ServletResponse response){
        System.out.println("===后期处理/后置返回处理");
    }

    public void afterCompletion(ServletRequest request, ServletResponse response, Exception exception){
        System.out.println("===完成处理/后置最终处理");
    }
}
