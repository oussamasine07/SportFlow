package com.sportflow.controller.trainner;

import com.sportflow.dao.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/trainer/create")
public class CreateTrainerServlet extends HttpServlet {

    UserDAO userDAO = null;
    public void init () {
        userDAO = new UserDAO();
    }

    protected void doGet (HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        RequestDispatcher rd = req.getRequestDispatcher("/views/trainer/create.jsp");
        rd.forward(req, res);
    }

}
