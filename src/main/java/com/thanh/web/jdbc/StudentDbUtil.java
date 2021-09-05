package com.thanh.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtil {
	private DataSource dataSource;

	public StudentDbUtil(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	public List<Student> getStudents() throws Exception {

		List<Student> students = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myConn = (Connection) dataSource.getConnection();

			String sql = "select * from student order by last_name";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);

			while (myRs.next()) {
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");

				Student tempStudent = new Student(id, firstName, lastName, email);

				students.add(tempStudent);
			}

		} catch (Exception ece) {
			ece.printStackTrace();
		} finally {
			close(myConn, myStmt, myRs);
		}

		return students;
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		// TODO Auto-generated method stub
		try {
			if (myRs != null) {
				myRs.close();
			}
			if (myStmt != null) {
				myStmt.close();
			}
			if (myConn != null) {
				myConn.close();
			}
		} catch (Exception ece) {
			ece.printStackTrace();
		}
	}

	public void addStudent(Student theStudent) {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			myConn = (Connection) dataSource.getConnection();
			String sql = "insert into student " + "(first_name, last_name, email) " + "values (?, ?, ?)";
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getEmail());
			
			myStmt.execute();
		}
		catch(Exception e) {
			close(myConn, myStmt, null);
		}
	}

}
