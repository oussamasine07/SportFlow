package com.sportflow.controller.trainner;

import com.sportflow.dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/trainer/delete")
public class DeleteTrainerServlet extends HttpServlet {

    UserDAO userDAO = null;

    public void init () {
        userDAO = new UserDAO();
    }

    protected void doPost (HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {

        int userId = Integer.parseInt(req.getParameter("id"));

        userDAO.deleteUserById(userId);

        res.sendRedirect(req.getContextPath() + "/trainer");
    }

}




















