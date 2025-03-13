package com.sportflow.filters;

import jakarta.servlet.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;

@WebFilter("/*")
public class IsAuthenticated implements Filter {

    private String[] protectedURIs = {
            "/user/dashboard",
            "/user/profile"
    };

    private HttpServletRequest req = null;
    private HttpServletResponse res = null;

    public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain)
        throws ServletException, IOException
    {
        req = (HttpServletRequest) request;
        res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        boolean isLoggedInUser = (session != null && session.getAttribute("user") != null);

        String loginURI = req.getContextPath() + "/auth/login";
        String registerURI = req.getContextPath() + "/auth/register";

        boolean isAuthRequest = (req.getRequestURI().equals(loginURI) || req.getRequestURI().equals(registerURI));

        boolean isAuthPage = (req.getRequestURI().endsWith("/login") || req.getRequestURI().endsWith("/register"));

        // start the check
        if (isLoggedInUser && (isAuthRequest || isAuthPage)) {
            res.sendRedirect(req.getContextPath() + "/user/dashboard");
        }
        else if (!isLoggedInUser && isLoginRequired()) {
            res.sendRedirect(req.getContextPath() + "/auth/login");
        }
        else {
            chain.doFilter(req, res);
        }
    }

    boolean isLoginRequired () {
        String requestURL = req.getRequestURL().toString();
        for (String protectedUrl : protectedURIs) {
            if (requestURL.contains(protectedUrl)) {
                return true;
            }
        }
        return false;
    }

}
