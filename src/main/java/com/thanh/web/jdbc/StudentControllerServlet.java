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
		case "ADD": {
			addStudent(request, response);
		}
		default: {
			listStudents(request, response);
		}
		}
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response) {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");

		Student theStudent = new Student(firstName, lastName, email);

		studentDbUtil.addStudent(theStudent);

		listStudents(request, response);
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
