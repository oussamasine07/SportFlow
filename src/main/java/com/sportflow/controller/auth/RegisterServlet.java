package com.sportflow.controller.auth;

import com.password4j.BcryptFunction;
import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.types.Bcrypt;
import com.sportflow.dao.UserDAO;
import com.sportflow.dto.RegisterDTO;
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

@WebServlet("/auth/register")
public class RegisterServlet extends HttpServlet {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    UserDAO userDAO = null;

    public void init () {
        userDAO = new UserDAO();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {

        RequestDispatcher rd = req.getRequestDispatcher("/views/auth/register.jsp");
        rd.forward(req, res);

    }

    protected void doPost (HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {

        User user = null;

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        System.out.println(firstName);

        RegisterDTO registerDTO = new RegisterDTO(firstName, lastName, email, password, confirmPassword);


        Set<ConstraintViolation<RegisterDTO>> violations = validator.validate(registerDTO);
        HttpSession session = req.getSession();
        Map<String, String> errors = new HashMap<>();

        if (!violations.isEmpty()) {
            System.out.println("you have some errors");
            for (ConstraintViolation<RegisterDTO> violation : violations) {
                errors.put(violation.getPropertyPath().toString(), violation.getMessage());
            }

            session.setAttribute("errors", errors);
            session.setAttribute("old", registerDTO);

            res.sendRedirect(req.getContextPath() + "/auth/register");

        } else {
            user = userDAO.getUserByEmail(registerDTO.getEmail());
            if (user != null ) {
                session.setAttribute("errorUser", "this email is already taken");
                res.sendRedirect(req.getContextPath() + "/auth/register");
            } else {
                // hash password
                BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);
                Hash hash = Password.hash(registerDTO.getPassword())
                        .addPepper("somethignrealyhard")
                        .with(bcrypt);
                registerDTO.setPassword(hash.getResult());
                userDAO.registerUser( registerDTO );
                session.setAttribute("registerSuccess", "Register success, Please login");
                res.sendRedirect(req.getContextPath() + "/auth/login");
            }

        }
    }

}
