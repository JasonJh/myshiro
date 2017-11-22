package com.xh.servlet;

import org.apache.shiro.SecurityUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="LogoutServlet",urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SecurityUtils.getSubject().logout();
        request.getRequestDispatcher("/WEB-INF/jsp/logoutSuccess.jsp").forward(request,response);
    }
}
