package com.param.dao;

import java.sql.Connection;			
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.param.user.User;



public class UserDao 
{
	private String URL = "jdbc:mysql://localhost:3307/firstspring";
	private String Usernamee = "root";
	private String Passworde = "root";

	private static final String INSERT_USERS_SQL = "insert into users" + "  (username , password, contact , email, address) VALUES "
			+ "( ? , ? , ? , ?, ?)";

	private static final String SELECT_USER_BY_ID = "select id,username,password,contact,email,address from users where id =?";
	private static final String SELECT_ALL_USERS = "select * from users";
	private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
	private static final String UPDATE_USERS_SQL = "update users set username = ?,password= ?,contact= ?,email= ?, address =? where id = ?;";
	private static final String USERNAME_PASSWORD ="select*from users HAVING username= ? and password= ?";
	public UserDao() 
	{
		
	}


	public boolean check(String username,String password) throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(USERNAME_PASSWORD);
		preparedStatement.setString(1,username); 
		preparedStatement.setString(2,password);
		ResultSet rs=(ResultSet) preparedStatement.executeQuery();
		if(rs.next())
		{
			return true;
		}
		return false;
		
	}
	


	protected Connection getConnection() 
	{
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL,Usernamee,Passworde);
		} catch (SQLException e) 
		{
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		return connection;
	}
	
	public void insertUser(User user) throws SQLException 
	{
		System.out.println(INSERT_USERS_SQL);

		try (Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) 
		{
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getContact());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getAddress());
			
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) 
		{
			printSQLException(e);
		}
	}

	public User selectUser(int id) 
	{
		User user = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) 
		{
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) 
			{
				
				String username = rs.getString("username");
				String password =rs.getString("password");
				String contact=rs.getString("contact");
				String email = rs.getString("email");
				String address = rs.getString("address");
				user = new User(id,username,password,contact, email,address);
			}
		} catch (SQLException e) 
		{
			printSQLException(e);
		}
		return user;
	}
	
	public List<User> selectAllUsers() {

		
		List<User> users = new ArrayList<>();
		
		try (Connection connection = getConnection();

			
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) 
		{
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String contact = rs.getString("contact");
				String email = rs.getString("email");
				String address = rs.getString("address");
				users.add(new User(id,username,password,contact,email,address));
			}
		} catch (SQLException e) 
		{
			printSQLException(e);
		}
		return users;
	} 
	
	
	public boolean deleteUser(int id) throws SQLException 
	{
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) 
		{
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateUser(User user) throws SQLException 
	{
		boolean rowUpdated;
		try (Connection connection = getConnection();
		
			PreparedStatement preparedStatement= connection.prepareStatement(UPDATE_USERS_SQL);)
		{
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getContact());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getAddress());
			preparedStatement.setInt(6, user.getId());
			rowUpdated = preparedStatement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) 
	{
		for (Throwable e : ex) 
		{
			if (e instanceof SQLException) 
			{
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) 
				{
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
























/*private void insertUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException 
	{
		String username = request.getParameter("username");
		String password =request.getParameter("password");
		String contact= request.getParameter("contact");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		User newUser = new User(username,password,contact,email, address);
		userDAO.insertUser(newUser);
		response.sendRedirect("list");
	}
	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException 
	{
		int id = Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username");
		String password =request.getParameter("password");
		String contact= request.getParameter("contact");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		User up = new User(username,password,contact, email, address);
		userDAO.updateUser(up);
		response.sendRedirect("list");
	}
*/



















/*package com.param.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.param.dao.UserDao;
import com.param.user.User;


@WebServlet("/UserController")
public class UserController extends HttpServlet
{

	
	/**
	 * 
	 */
	/*private static final long serialVersionUID = 1L;
	private UserDao userDAO;
	
	public void init() 
	{
		userDAO = new UserDao();
	}
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		doPost(request, response);
	}
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		String action = request.getServletPath();

		try {
			switch (action)
{
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertUser(request, response);
				break;
			case "/delete":
				deleteUser(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateUser(request, response);
				break;
			default:
				listUser(request, response);
				break;
			}
		} catch (SQLException ex) 
		{
			throw new ServletException(ex);
		}
	}
	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException 
	{
		List<User> listUser = userDAO.selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException 
	{
		int id = Integer.parseInt(request.getParameter("id"));
		User existingUser = userDAO.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);

	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException 
	{
		String username = request.getParameter("username");
		String password =request.getParameter("password");
		String contact= request.getParameter("contact");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		User newUser = new User(username,password,contact,email, address);
		userDAO.insertUser(newUser);
		response.sendRedirect("list");
	}
	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException 
	{
		int id = Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username");
		String password =request.getParameter("password");
		String contact= request.getParameter("contact");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		User up = new User(username,password,contact, email, address);
		userDAO.updateUser(up);
		response.sendRedirect("list");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		userDAO.deleteUser(id);
		response.sendRedirect("list");

	}

	

	
	
	
}

*/
