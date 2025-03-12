package com.sportflow.controller.auth;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/auth/register")
public class RegisterServlet extends HttpServlet {

    public void init () {}

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {

        RequestDispatcher rd = req.getRequestDispatcher("/views/auth/register.jsp");
        rd.forward(req, res);

    }

    protected void doPost (HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {


        res.sendRedirect("");
    }

}
