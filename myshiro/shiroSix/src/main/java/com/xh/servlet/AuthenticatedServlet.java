package com.xh.servlet;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="authenticatedServlet",urlPatterns = "/authenticated")
public class AuthenticatedServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Subject subject = SecurityUtils.getSubject();

        if(subject.isAuthenticated()) {
            request.setAttribute("subject",subject);
            request.getRequestDispatcher("/WEB-INF/jsp/authenticated.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }
    }

}
