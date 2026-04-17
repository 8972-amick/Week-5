package com.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        res.setHeader("Cache-Control", "no-cache , no-store, must-revalidate"); //works on new type browsers
        res.setHeader("Pragma", "no-cache"); // works on old type browsers
        res.setDateHeader("Expires",0);

        String path = req.getRequestURI();
        HttpSession session = req.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

        boolean isLogin = path.endsWith("Login.jsp");
        boolean isRegister = path.endsWith("register.jsp");
        boolean isIndex = path.endsWith("index.jsp");
        boolean isAuth = path.contains("user-auth");

        boolean isPublic = isLogin || isRegister || isIndex || isAuth;
    }
}
