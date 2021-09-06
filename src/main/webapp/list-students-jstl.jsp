<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Tracker App</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
<script>
	function confirmDelete(event) {
		const result = confirm('Are you sure want to delete this student?');
		if (!result) event.preventDefault();
	}
</script>
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
						<th>Action</th>
					</tr>

					<c:forEach var="tempStudent" items="${STUDENT_LIST}">
						<!--  set up a link for each student -->
						<c:url var="tempLink" value="StudentControllerServlet">
							<c:param name="command" value="LOAD" />
							<c:param name="studentId" value="${ tempStudent.id }" />
						</c:url>

						<c:url var="deleteLink" value="StudentControllerServlet">
							<c:param name="command" value="DELETE" />
							<c:param name="studentId" value="${ tempStudent.id }" />
						</c:url>
						<tr>
							<td>${tempStudent.firstName}</td>
							<td>${tempStudent.lastName}</td>
							<td>${tempStudent.email}</td>
							<td><a href="${tempLink}">Update</a> | <a
								href="${deleteLink}" onclick="confirmDelete(event)">Delete</a></td>
						</tr>
					</c:forEach>

				</table>
			</div>
		</div>
	</div>
</body>
</html>