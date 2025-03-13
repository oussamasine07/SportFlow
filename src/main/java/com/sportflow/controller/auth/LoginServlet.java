package com.sportflow.controller.auth;

import com.password4j.BcryptFunction;
import com.password4j.Password;
import com.password4j.types.Bcrypt;
import com.sportflow.dao.UserDAO;
import com.sportflow.dto.LoginDTO;
import com.sportflow.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    UserDAO userDAO = null;
    public void init () {
        userDAO = new UserDAO();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException
    {
        RequestDispatcher rs = req.getRequestDispatcher("/views/auth/login.jsp");
        rs.forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException
    {
        User user = null;

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        LoginDTO loginDTO = new LoginDTO(email, password);
        HttpSession session = req.getSession();
        Map<String, String> errors = new HashMap<>();

        Set<ConstraintViolation<LoginDTO>> violations = validator.validate(loginDTO);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<LoginDTO> violation : violations) {
                errors.put(violation.getPropertyPath().toString(), violation.getMessage());
            }



            session.setAttribute("errors", errors);
            session.setAttribute("old", loginDTO);

            res.sendRedirect(req.getContextPath() + "/auth/login");
        } else {
            user = userDAO.authenticateByEmail(loginDTO.getEmail());
            if (user == null) {
                session.setAttribute("errorUser", "Invalid Credential");
                res.sendRedirect(req.getContextPath() + "/auth/login");
            } else {
                BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);
                boolean isMatch = Password.check(loginDTO.getPassword(), user.getPassword())
                        .addPepper("somethignrealyhard")
                        .with(bcrypt);

                if (!isMatch) {
                    session.setAttribute("errorUser", "Invalid Credential");
                    res.sendRedirect(req.getContextPath() + "/auth/login");
                } else {

                    session.setAttribute("user", user);

                    res.sendRedirect(req.getContextPath() + "/user/dashboard");

                }
            }
        }
    }
}
