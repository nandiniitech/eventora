package com.eventora.servlet;

import com.eventora.dao.StudentDAO;
import com.eventora.model.Student;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private final StudentDAO dao = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String fullName=req.getParameter("fullName").trim();
        String email=req.getParameter("email").trim().toLowerCase();
        String password=req.getParameter("password");
        String confirmPwd=req.getParameter("confirmPassword");
        String rollNumber=req.getParameter("rollNumber").trim().toUpperCase();
        String department=req.getParameter("department").trim();
        String phone=req.getParameter("phone")!=null?req.getParameter("phone").trim():"";

        if(fullName.isEmpty()||email.isEmpty()||password.isEmpty()||rollNumber.isEmpty()||department.isEmpty()){
            req.setAttribute("error","All fields are required.");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req,res); return;
        }
        if(!password.equals(confirmPwd)){
            req.setAttribute("error","Passwords do not match.");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req,res); return;
        }
        if(dao.emailExists(email)){
            req.setAttribute("error","Email already registered.");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req,res); return;
        }
        Student s=new Student(fullName,email,password,rollNumber,department,phone);
        if(dao.register(s)){
            res.sendRedirect(req.getContextPath()+"/login?registered=true");
        } else {
            req.setAttribute("error","Registration failed. Try again.");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req,res);
        }
    }
}
