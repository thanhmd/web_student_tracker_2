<%@ page import="java.util.*, com.thanh.web.jdbc.* " %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Tracker App</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
	<%
		List<Student> theStudents = (List<Student>) request.getAttribute("STUDENT_LIST");
	%>
	<div id="wrapper">
		<div id="header">
			<h2>FooBar University</h2>
		</div>
		
		<div id="container">
			<div id="conent">
				<table>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
					</tr>
					
					<% for (Student tempStudent : theStudents) { %>
						<tr>
							<td> <%= tempStudent.getFirstName() %></td>
							<td> <%= tempStudent.getLastName() %></td>
							<td> <%= tempStudent.getEmail() %></td>
						</tr>
					<% } %>
					
				</table>
			</div>
		</div>
	</div>
</body>
</html>