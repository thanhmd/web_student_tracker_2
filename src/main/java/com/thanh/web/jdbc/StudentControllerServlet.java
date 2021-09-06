package com.thanh.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StudentDbUtil studentDbUtil;

	@Resource(name = "jdbc/web_student_tracker")

	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		super.init();

		try {
			studentDbUtil = new StudentDbUtil(dataSource);
		} catch (Exception ece) {
			throw new ServletException(ece);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String theCommand = request.getParameter("command");
		if (theCommand == null) {
			theCommand = "LIST";
		}
		switch (theCommand) {
		case "LIST": {
			listStudents(request, response);
			break;
		}
//		case "ADD": {
//			addStudent(request, response);
//			break;
//		}
		case "LOAD": {
			loadStudent(request, response);
			break;
		}
		case "UPDATE": {
			updateStudent(request, response);
		}
		case "DELETE": {
			deleteStudent(request, response);
		}
		default: {
			listStudents(request, response);
		}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");

			// route to the appropriate method
			switch (theCommand) {

			case "ADD":
				addStudent(request, response);
				break;

			default:
				listStudents(request, response);
			}

		} catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) {
		String theStudentId = request.getParameter("studentId");

		studentDbUtil.deleteStudent(theStudentId);

		listStudents(request, response);
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("studentId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");

		Student theStudent = new Student(id, firstName, lastName, email);

		studentDbUtil.updateStudent(theStudent);
		listStudents(request, response);

	}

	private void loadStudent(HttpServletRequest request, HttpServletResponse response) {
		try {
			String theStudentId = request.getParameter("studentId");
			Student theStudent = studentDbUtil.getStudents(theStudentId);

			request.setAttribute("THE_STUDENT", theStudent);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student-form.jsp");

			dispatcher.forward(request, response);

		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");

		Student theStudent = new Student(firstName, lastName, email);

		studentDbUtil.addStudent(theStudent);
		//listStudents(request, response);

		// send back to main page (the student list)
		// SEND AS REDIRECT to avoid multiple-browser reload issue
		response.sendRedirect(request.getContextPath() + "/StudentControllerServlet?command=LIST");
	}

	private void listStudents(HttpServletRequest request, HttpServletResponse response) {
		List<Student> students;
		try {
			students = studentDbUtil.getStudents();

			request.setAttribute("STUDENT_LIST", students);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students-jstl.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
