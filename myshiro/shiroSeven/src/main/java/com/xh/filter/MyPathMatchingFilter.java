package com.xh.filter;

import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Arrays;

public class MyPathMatchingFilter extends PathMatchingFilter {

    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue){
        System.out.println("url matches,config is " + Arrays.toString((String[])mappedValue));
        return true;
    }
}
