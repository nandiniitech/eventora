package com.eventora.servlet;

import com.eventora.dao.StudentDAO;
import com.eventora.model.Student;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet({"/login", "/logout"})
public class LoginServlet extends HttpServlet {
    private final StudentDAO dao = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        if ("/logout".equals(req.getServletPath())) {
            HttpSession s = req.getSession(false);
            if (s != null) {
                s.removeAttribute("student");
                s.invalidate();
            }
            // Expire the session cookie so the browser doesn't resend a stale JSESSIONID
            Cookie cookie = new Cookie("JSESSIONID", "");
            cookie.setMaxAge(0);
            cookie.setPath(req.getContextPath().isEmpty() ? "/" : req.getContextPath());
            res.addCookie(cookie);
            res.sendRedirect(req.getContextPath() + "/login?logout=true"); return;
        }
        HttpSession s = req.getSession(false);
        if (s != null && s.getAttribute("student") != null) {
            res.sendRedirect(req.getContextPath() + "/events"); return;
        }
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String email    = req.getParameter("email").trim().toLowerCase();
        String password = req.getParameter("password");

        if (email.isEmpty() || password.isEmpty()) {
            req.setAttribute("error", "Email and password are required.");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, res); return;
        }
        Student student = dao.login(email, password);
        if (student != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("student", student);
            session.setMaxInactiveInterval(30 * 60);
            res.sendRedirect(req.getContextPath() + "/events");
        } else {
            req.setAttribute("error", "Invalid email or password.");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, res);
        }
    }
}
