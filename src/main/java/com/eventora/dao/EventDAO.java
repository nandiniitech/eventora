package com.eventora.dao;

import com.eventora.model.Event;
import com.eventora.util.DBConnection;
import java.sql.*;
import java.util.*;

public class EventDAO {

    public List<Event> getAllEvents() {
        List<Event> list=new ArrayList<>();
        String sql="SELECT e.*, COUNT(r.registration_id) AS reg_count FROM events e "
                  +"LEFT JOIN registrations r ON e.event_id=r.event_id GROUP BY e.event_id ORDER BY e.event_date";
        Connection conn=null; Statement st=null; ResultSet rs=null;
        try {
            conn=DBConnection.getConnection(); st=conn.createStatement(); rs=st.executeQuery(sql);
            while(rs.next()) list.add(mapRow(rs));
        } catch(SQLException e){ e.printStackTrace();
        } finally { DBConnection.close(rs,st,conn); }
        return list;
    }

    public Event getById(int id) {
        String sql="SELECT e.*, COUNT(r.registration_id) AS reg_count FROM events e "
                  +"LEFT JOIN registrations r ON e.event_id=r.event_id WHERE e.event_id=? GROUP BY e.event_id";
        Connection conn=null; PreparedStatement ps=null; ResultSet rs=null;
        try {
            conn=DBConnection.getConnection(); ps=conn.prepareStatement(sql);
            ps.setInt(1,id); rs=ps.executeQuery();
            if(rs.next()) return mapRow(rs);
        } catch(SQLException e){ e.printStackTrace();
        } finally { DBConnection.close(rs,ps,conn); }
        return null;
    }

    public List<Event> getByStudent(int studentId) {
        List<Event> list=new ArrayList<>();
        String sql="SELECT e.*, COUNT(r2.registration_id) AS reg_count FROM events e "
                  +"INNER JOIN registrations r ON e.event_id=r.event_id AND r.student_id=? "
                  +"LEFT JOIN registrations r2 ON e.event_id=r2.event_id GROUP BY e.event_id";
        Connection conn=null; PreparedStatement ps=null; ResultSet rs=null;
        try {
            conn=DBConnection.getConnection(); ps=conn.prepareStatement(sql);
            ps.setInt(1,studentId); rs=ps.executeQuery();
            while(rs.next()) list.add(mapRow(rs));
        } catch(SQLException e){ e.printStackTrace();
        } finally { DBConnection.close(rs,ps,conn); }
        return list;
    }

    public String register(int studentId, int eventId) {
        Event ev=getById(eventId);
        if(ev==null) return "error";
        if(!ev.hasSlotsAvailable()) return "full";
        String sql="INSERT INTO registrations(student_id,event_id) VALUES(?,?)";
        Connection conn=null; PreparedStatement ps=null;
        try {
            conn=DBConnection.getConnection(); ps=conn.prepareStatement(sql);
            ps.setInt(1,studentId); ps.setInt(2,eventId); ps.executeUpdate();
            return "success";
        } catch(SQLIntegrityConstraintViolationException e){ return "duplicate";
        } catch(SQLException e){ e.printStackTrace(); return "error";
        } finally { DBConnection.close(ps,conn); }
    }

    public boolean isRegistered(int studentId, int eventId) {
        String sql="SELECT COUNT(*) FROM registrations WHERE student_id=? AND event_id=?";
        Connection conn=null; PreparedStatement ps=null; ResultSet rs=null;
        try {
            conn=DBConnection.getConnection(); ps=conn.prepareStatement(sql);
            ps.setInt(1,studentId); ps.setInt(2,eventId); rs=ps.executeQuery();
            if(rs.next()) return rs.getInt(1)>0;
        } catch(SQLException e){ e.printStackTrace();
        } finally { DBConnection.close(rs,ps,conn); }
        return false;
    }

    private Event mapRow(ResultSet rs) throws SQLException {
        Event e=new Event();
        e.setEventId(rs.getInt("event_id"));
        e.setTitle(rs.getString("title"));
        e.setDescription(rs.getString("description"));
        e.setCategory(rs.getString("category"));
        e.setEventDate(rs.getDate("event_date"));
        e.setEventTime(rs.getTime("event_time"));
        e.setVenue(rs.getString("venue"));
        e.setCapacity(rs.getInt("capacity"));
        e.setCreatedAt(rs.getTimestamp("created_at"));
        e.setRegisteredCount(rs.getInt("reg_count"));
        return e;
    }
}
