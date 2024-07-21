package com.example.webapp;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello2")
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("<h1>Hello, World!</h1>");

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
    
            // Connect to MySQL database
            String jdbcUrl = "jdbc:mysql://localhost:3306/java3tapp";
            String username = "root";
            String password = "MyNewPass";
            Connection con = DriverManager.getConnection(jdbcUrl, username, password);
    
            // Execute query
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Users");
    
            // Display results as HTML table
            response.getWriter().println("<table border='1'><tr><th>ID</th><th>Username</th><th>Email</th></tr>");
            while (rs.next()) {
                response.getWriter().println("<tr><td>" + rs.getInt("id") + "</td><td>" + rs.getString("username") + "</td><td>" + rs.getString("email") + "</td></tr>");
            }
            response.getWriter().println("</table>");
    
            // Close resources
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
