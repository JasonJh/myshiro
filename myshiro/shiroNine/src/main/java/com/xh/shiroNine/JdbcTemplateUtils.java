package com.xh.shiroNine;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateUtils {
    private static JdbcTemplate jdbcTemplate;

    public static JdbcTemplate jdbcTemplate(){
        if(jdbcTemplate ==null){
           jdbcTemplate=createJdbcTemplate();
        }
        return jdbcTemplate;
    }

    public static JdbcTemplate createJdbcTemplate(){
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/shiro");
        ds.setUsername("root");
        ds.setPassword("123456");
        return new JdbcTemplate(ds);
    }
}
