package com.param.web;

import java.io.IOException;			
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.param.dao.UserDao;

@WebServlet("/Login")
public class Login extends HttpServlet 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException ,NullPointerException
	{
 		response.getWriter().append("Served at: ").append(request.getContextPath());
 		
 		String username=request.getParameter("username");
 		String password=request.getParameter("password");
 		UserDao dao=new UserDao();
 		
 		try {
 			 if(username.equals("root")&&password.equals("root"))
			{

				HttpSession session= request.getSession();
				session.setAttribute("Username",username);
				response.sendRedirect("user-form.jsp");
			
				
			}
 			else if(dao.check(username,password))
			{
				HttpSession session= request.getSession();
				session.setAttribute("Username",username);
				response.sendRedirect("user-form.jsp");
			}
			
			else
			{
				response.sendRedirect("login.jsp");
			}
		} 
 		catch (ClassNotFoundException e) 
 		{
			e.printStackTrace();
		} catch (SQLException e) 
 		{
			e.printStackTrace();
		} catch (IOException e) 
 		{

			e.printStackTrace();
		}
	}


}
