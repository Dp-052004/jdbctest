package DAO;

import java.sql.Connection;

import Exception.InvalidStudentDataException;
import model.Student;

public interface StudentDAO {
	void addStudent(Connection connection,int id,String name,String email,int age,long mobileNo) throws InvalidStudentDataException;
	void displayAllStudent(Connection connection);
	void updateStudent(Connection connection,long mobileNo,String email);
	void deleteStudent(Connection connection,int id);
}
