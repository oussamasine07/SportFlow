package com.sportflow.controller.trainner;

import com.sportflow.dao.TrainerDAO;
import com.sportflow.dao.UserDAO;
import com.sportflow.model.Trainer;
import com.sportflow.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/trainer/edit")
public class UpdateTrainerServlet extends HttpServlet {

    TrainerDAO trainerDAO = null;
    UserDAO userDAO = null;

    public void init () {
        trainerDAO = new TrainerDAO();
        userDAO = new UserDAO();
    }

    protected void doGet (HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        int userId = Integer.parseInt(req.getParameter("id"));

        Trainer trainer = trainerDAO.getTrainerByUserId( userId );
        req.setAttribute("trainer", trainer);

        RequestDispatcher rd = req.getRequestDispatcher("/views/trainer/edit.jsp");
        rd.forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {

        int userId = Integer.parseInt(req.getParameter("id"));
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String specialty = req.getParameter("specialty");

        Trainer updatedTrainer = new Trainer();

        updatedTrainer.setId(userId);
        updatedTrainer.setFirstName(firstName);
        updatedTrainer.setLastName(lastName);
        updatedTrainer.setEmail(email);
        updatedTrainer.setSpecialty(specialty);

        trainerDAO.updateTrainerByUserId( updatedTrainer );

        res.sendRedirect(req.getContextPath() + "/trainer");

    }
}







