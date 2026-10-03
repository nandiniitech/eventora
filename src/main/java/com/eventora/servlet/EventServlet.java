package com.eventora.servlet;

import com.eventora.dao.EventDAO;
import com.eventora.model.Event;
import com.eventora.model.Student;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet({"/events","/events/register"})
public class EventServlet extends HttpServlet {
    private final EventDAO dao = new EventDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession s=req.getSession(false);
        if(s==null||s.getAttribute("student")==null){
            res.sendRedirect(req.getContextPath()+"/login"); return;
        }
        Student student=(Student)s.getAttribute("student");
        List<Event> events=dao.getAllEvents();
        for(Event ev:events){
            req.setAttribute("reg_"+ev.getEventId(),
                dao.isRegistered(student.getStudentId(),ev.getEventId()));
        }
        req.setAttribute("events",events);
        req.setAttribute("student",student);
        req.getRequestDispatcher("/WEB-INF/views/events.jsp").forward(req,res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession s=req.getSession(false);
        if(s==null||s.getAttribute("student")==null){
            res.sendRedirect(req.getContextPath()+"/login"); return;
        }
        Student student=(Student)s.getAttribute("student");
        int eventId=Integer.parseInt(req.getParameter("eventId"));
        String result=dao.register(student.getStudentId(),eventId);
        res.sendRedirect(req.getContextPath()+"/events?msg="+result);
    }
}
