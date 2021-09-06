<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Student</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>FooBar University</h2>
		</div>

		<div id="container">
			<h3>Add Student</h3>
			<form action="StudentControllerServlet" method="GET">
				<input type="hidden" name="command" value="UPDATE" /> <input
					type="hidden" name="studentId" value="${ THE_STUDENT.id }" />
				<table>
					<tbody>
						<tr>
							<td><label>First name:</label></td>
							<td><label><input type="text" name="firstName"
									value="${ THE_STUDENT.firstName }" /></label></td>
						</tr>
						<tr>
							<td><label>Last name:</label></td>
							<td><label><input type="text" name="lastName"
									value="${ THE_STUDENT.lastName }" /></label></td>
						</tr>
						<tr>
							<td><label>Email:</label></td>
							<td><label><input type="text" name="email"
									value="${ THE_STUDENT.email }" /></label></td>
						</tr>
						<tr>
							<td><label></label></td>
							<td><label><input type="submit" value="Save"
									class="save" /></label></td>
						</tr>
					</tbody>
				</table>
			</form>

			<div style="clear: both;"></div>
			<p>
				<a href="StudentControllerServlet">Back to list</a>
			</p>
		</div>
	</div>
</body>
</html>