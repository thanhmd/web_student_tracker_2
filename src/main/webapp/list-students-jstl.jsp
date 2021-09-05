<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
	<div id="wrapper">
		<div id="header">
			<h2>FooBar University</h2>
		</div>

		<div id="container">
			<div id="content">
				<input type="button" value="Add Student"
					onclick="window.location.href='add-student-form.jsp'; return false;"
					class="add-student-button"></input>
				<table>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
					</tr>

					<c:forEach var="tempStudent" items="${STUDENT_LIST}">
						<tr>
							<td>${tempStudent.firstName}</td>
							<td>${tempStudent.lastName}</td>
							<td>${tempStudent.email}</td>
						</tr>
					</c:forEach>

				</table>
			</div>
		</div>
	</div>
</body>
</html>