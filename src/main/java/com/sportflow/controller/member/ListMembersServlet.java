package com.sportflow.controller.member;

import com.sportflow.dao.MemberDAO;
import com.sportflow.model.Member;
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

@WebServlet("/member")
public class ListMembersServlet extends HttpServlet {

    MemberDAO memberDAO = null;
    public void init () {
        memberDAO = new MemberDAO();
    }

    protected void doGet (HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");

        List<Member> members = memberDAO.getAllMembersByAuthenticatedUser(user.getId());
        members.forEach(mem -> System.out.println(mem.getFirstName()));
        req.setAttribute("members", members);

        RequestDispatcher rd = req.getRequestDispatcher("/views/member/index.jsp");
        rd.forward(req, res);
    }

}
