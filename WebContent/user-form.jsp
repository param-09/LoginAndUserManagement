<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User Management Application</title>

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
</ul>					
				<c:if test="${user != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${user == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${user != null}">
            			Edit User
            		</c:if>
						<c:if test="${user == null}">
            			Add New User
            		</c:if>
					</h2>
				</caption>

				<c:if test="${user != null}">
					<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
				</c:if>

						<label>User Name</label> <input type="text"
						value="<c:out value='${user.username}' />" class="form-control"
						name="username" required="required"> <br>

						<label>Password</label> <input type="password"
						value="<c:out value='${user.password}' />" class="form-control"
						name="password"><br>
						
						<label>Contact</label> <input type="text"
						value="<c:out value='${user.contact}' />" class="form-control"
						name="contact"><br>
				
						<label>email</label> <input type="email"
						value="<c:out value='${user.email}' />" class="form-control"
						name="email"><br>
				
				
						<label>address</label> <input type="text"
						value="<c:out value='${user.address}' />" class="form-control"
						name="address"><br>
								
				<button type="submit" class="btn btn-success">Save</button>
				</form>
	
</body>
</html>
