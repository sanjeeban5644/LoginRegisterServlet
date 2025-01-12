package com.sanjeeban.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sanjeeban.db.DbConnection;

@WebServlet("/registerForm")
public class Register extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String city = req.getParameter("city");

		try {
			Connection connection = DbConnection.getConnection();
			
			String query = "INSERT INTO REGISTER VALUES(?,?,?,?)";
			
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1,name);
			statement.setString(2,email);
			statement.setString(3,password);
			statement.setString(4,city);
			
			int count = statement.executeUpdate();
			
			
			
			if(count>0) {
				out.println("<h3 style='color:green'>Registered Successfully</h3>");
				
				RequestDispatcher rd = req.getRequestDispatcher("/login.html");
				rd.include(req, res);
				
			}else {
				out.println("<h3 style='color:red'>--Error--</h3>");
				
				RequestDispatcher rd = req.getRequestDispatcher("/register.html");
				rd.include(req, res);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
}
