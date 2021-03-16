<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html>
		<head>
		<title>Welcome</title>
		</head>
<body>
<%
response.setHeader("Cache-Control","no-cache,no-store,must-revalidate"); //for preventing from using back after logout
if(session.getAttribute("Username")==null)
{
	response.sendRedirect("login.jsp");	
}
%>
	
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Users</a></li>
			<li>
			<form action="Logout">
						<p><input type="submit" value="logout"></p>
						</form>
			</li>
			</ul>
			
	<div class="row">
		<div class="container">
			<h3 class="text-center">List of Users</h3>
			<hr>
			<div class="container text-left">
				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add New User</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Username</th>
						<th>Password</th>
						<th>Contact</th>
						<th>Email</th>
						<th>Address</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
						<c:forEach var="user" items="${listUser}">

						<tr>
							<td><c:out value="${user.id}" /></td>
							<td><c:out value="${user.username}" /></td>
							<td><c:out value="${user.password}" /></td>
							<td><c:out value="${user.contact}" /></td>
							<td><c:out value="${user.email}" /></td>
							<td><c:out value="${user.address}" /></td>
							
							
							
							<td><a href="edit?id=<c:out value='${user.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; 
								<a href="delete?id=<c:out value='${user.id}' />">Delete</a></td>
						
						
						
						</tr>
					</c:forEach>
					  
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>
