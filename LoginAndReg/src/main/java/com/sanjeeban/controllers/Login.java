package com.sanjeeban.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sanjeeban.db.DbConnection;
import com.sanjeeban.model.User;


@WebServlet("/loginForm")
public class Login extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		
		try {
			Connection connection = DbConnection.getConnection();
			
			String query = "SELECT * FROM REGISTER WHERE EMAIL=? AND PASSWORD=?";
			
			
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, email);
			statement.setString(2, password);
			
			
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				User user = new User();
				user.setName(rs.getString("NAME"));
				user.setEmail(rs.getString("EMAIL"));
				user.setCity(rs.getString("CITY"));
				
				HttpSession session = req.getSession();
				session.setAttribute("session_user",user);
				
				RequestDispatcher rd = req.getRequestDispatcher("/profile.jsp");
				rd.forward(req,res);
				
				
				
			}else {
				out.println("<h3 style='color:red'>Non Existant User</h3>");
				
				RequestDispatcher rd = req.getRequestDispatcher("/login.html");
				rd.include(req,res);
				
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}
}
