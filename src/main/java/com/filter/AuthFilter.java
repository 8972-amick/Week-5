package com.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter { // acts as a middleware between the request and responses

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

        boolean isStatic = path.endsWith(".css") ||
                path.endsWith(".js") ||
                path.endsWith(".jsp") ||
                path.endsWith(".jpeg");

        if (isLoggedIn && (isLogin && isRegister)) {
            res.sendRedirect(req.getContextPath() + "/views/dashboard.jsp"); // webapp contents
            return;
        }
        if (!isLoggedIn && !(isPublic && isStatic)) {
            res.sendRedirect(req.getContextPath() + "/views/login.jsp");
            return;
        }
        filterChain.doFilter(req, res);
    }
}
