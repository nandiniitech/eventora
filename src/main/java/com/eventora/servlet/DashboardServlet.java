package com.eventora.servlet;

import com.eventora.dao.EventDAO;
import com.eventora.model.Event;
import com.eventora.model.Student;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private final EventDAO dao = new EventDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("student") == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        Student student = (Student) session.getAttribute("student");
        List<Event> myEvents = dao.getByStudent(student.getStudentId());

        req.setAttribute("student",  student);
        req.setAttribute("myEvents", myEvents);
        req.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(req, res);
    }
}
