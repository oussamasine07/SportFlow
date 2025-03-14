package com.sportflow.controller.member;

import com.sportflow.dao.MemberDAO;
import com.sportflow.dao.UserDAO;
import com.sportflow.model.Member;
import com.sportflow.model.Trainer;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/member/edit")
public class UpdateMemberServlet extends HttpServlet {

    UserDAO userDAO = null;
    MemberDAO memberDAO = null;

    public void init () {
        userDAO = new UserDAO();
        memberDAO = new MemberDAO();
    }

    protected void doGet (HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException
    {
        int userId = Integer.parseInt(req.getParameter("id"));

        Member member = memberDAO.getMemberByUserId( userId );
        req.setAttribute("member", member);

        RequestDispatcher rd = req.getRequestDispatcher("/views/member/edit.jsp");
        rd.forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        int userId = Integer.parseInt(req.getParameter("id"));
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String dateOfBirth = req.getParameter("dateOfBirth");

        Member updatedMember = new Member();

        updatedMember.setId(userId);
        updatedMember.setFirstName(firstName);
        updatedMember.setLastName(lastName);
        updatedMember.setEmail(email);
        updatedMember.setDateOfBirth(dateOfBirth);

        memberDAO.updateMemberByUserId(updatedMember);

        res.sendRedirect(req.getContextPath() + "/member");

    }
}
