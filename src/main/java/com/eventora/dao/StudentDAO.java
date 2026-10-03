package com.eventora.dao;

import com.eventora.model.Student;
import com.eventora.util.DBConnection;
import java.sql.*;

public class StudentDAO {

    public boolean register(Student s) {
        String sql = "INSERT INTO students(full_name,email,password,roll_number,department,phone) VALUES(?,?,?,?,?,?)";
        Connection conn=null; PreparedStatement ps=null;
        try {
            conn=DBConnection.getConnection(); ps=conn.prepareStatement(sql);
            ps.setString(1,s.getFullName()); ps.setString(2,s.getEmail());
            ps.setString(3,s.getPassword()); ps.setString(4,s.getRollNumber());
            ps.setString(5,s.getDepartment()); ps.setString(6,s.getPhone());
            return ps.executeUpdate()>0;
        } catch(SQLIntegrityConstraintViolationException e){ return false;
        } catch(SQLException e){ e.printStackTrace(); return false;
        } finally { DBConnection.close(ps,conn); }
    }

    public Student login(String email, String password) {
        String sql = "SELECT * FROM students WHERE email=? AND password=?";
        Connection conn=null; PreparedStatement ps=null; ResultSet rs=null;
        try {
            conn=DBConnection.getConnection(); ps=conn.prepareStatement(sql);
            ps.setString(1,email); ps.setString(2,password);
            rs=ps.executeQuery();
            if(rs.next()) return mapRow(rs);
        } catch(SQLException e){ e.printStackTrace();
        } finally { DBConnection.close(rs,ps,conn); }
        return null;
    }

    public boolean emailExists(String email) {
        String sql = "SELECT COUNT(*) FROM students WHERE email=?";
        Connection conn=null; PreparedStatement ps=null; ResultSet rs=null;
        try {
            conn=DBConnection.getConnection(); ps=conn.prepareStatement(sql);
            ps.setString(1,email); rs=ps.executeQuery();
            if(rs.next()) return rs.getInt(1)>0;
        } catch(SQLException e){ e.printStackTrace();
        } finally { DBConnection.close(rs,ps,conn); }
        return false;
    }

    private Student mapRow(ResultSet rs) throws SQLException {
        Student s=new Student();
        s.setStudentId(rs.getInt("student_id"));
        s.setFullName(rs.getString("full_name"));
        s.setEmail(rs.getString("email"));
        s.setPassword(rs.getString("password"));
        s.setRollNumber(rs.getString("roll_number"));
        s.setDepartment(rs.getString("department"));
        s.setPhone(rs.getString("phone"));
        s.setCreatedAt(rs.getTimestamp("created_at"));
        return s;
    }
}
