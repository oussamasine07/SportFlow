package com.sportflow.controller.trainner;

import com.sportflow.dao.TrainerDAO;
import com.sportflow.model.Trainer;
import com.sportflow.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/trainer")
public class ListTrainersServlet extends HttpServlet {

    TrainerDAO trainerDAO = null;
    public void init () {
        trainerDAO = new TrainerDAO();
    }

    protected void doGet (HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {

        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");

        List<Trainer> trainers = trainerDAO.getAllTrainersByAuthenticatedUser(user.getId());
        req.setAttribute("trainers", trainers);
        RequestDispatcher rd = req.getRequestDispatcher("/views/trainer/index.jsp");
        rd.forward(req, res);

    }

}
