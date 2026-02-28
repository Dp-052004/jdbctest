package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Exception.InvalidStudentDataException;
import model.Student;

public class MySQLStudentDAO implements StudentDAO{

	@Override
	public void addStudent(Connection connection,int id, String name, String email, int age, long mobileNo) throws InvalidStudentDataException{
//		Scanner sc=new Scanner(System.in);
//		System.out.println("Enter the student id");
//		int id=sc.nextInt();
//		System.out.println("Enter the student name");
//		String name=sc.nextLine();
//		System.out.println("Enter the student email ID");
//		String email=sc.nextLine();
//		System.out.println("Enter the student age");
//		int age=sc.nextInt();
//		System.out.println("Enter the student id");
//		int id=sc.nextInt();
		
		if(name.length()==0) {
			throw new InvalidStudentDataException("name must be not empty");
		}
		if(!email.contains("@")) {
			throw new InvalidStudentDataException("email id should contain @");
		}
		if(age<0) {
			throw new InvalidStudentDataException("age must be positive");
		}
		int cnt=0;
		long n=mobileNo;
		while(n>0) {
			cnt++;
			n/=10;
		}
		if(cnt!=10) throw new InvalidStudentDataException("Mobile no should be 10 digits");
		
		String insert="insert into student_details values (?,?,?,?,?)";
		
		try {
			PreparedStatement ps=connection.prepareStatement(insert);
			ps.setInt(1,id);
			ps.setString(2,name);
			ps.setString(3,email);
			ps.setInt(4, age);
			ps.setLong(5, mobileNo);
			
			ps.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void displayAllStudent(Connection connection) {
		String select="select * from student_details";
		
		try {
			PreparedStatement ps=connection.prepareStatement(select);
			
			ResultSet resultSet=ps.executeQuery();
			while(resultSet.next()) {
				System.out.println("----------------------------------------");
				System.out.println("Student Id:"+resultSet.getInt("id"));
				System.out.println("Student Name:"+resultSet.getString("name"));
				System.out.println("Student Email Id:"+resultSet.getString("email"));
				System.out.println("Student Age:"+resultSet.getInt("age"));
				System.out.println("Student Mobile no:"+resultSet.getLong("mobile_number"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateStudent(Connection connection,long mobileNo, String email) {
		String update="update student_details set email=? where mobile_number=?";
		
		try {
			PreparedStatement ps=connection.prepareStatement(update);
			ps.setString(1,email);
			ps.setLong(2,mobileNo);
			int rows=ps.executeUpdate();
			if (rows > 0) {
	            System.out.println("Student updated successfully!");
	        } else {
	            System.out.println("No student found with this mobile number.");
	        }
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteStudent(Connection connection,int id) {
		String delete="delete from student_details where id=?";
		try {
			PreparedStatement ps=connection.prepareStatement(delete);
			ps.setInt(1,id);
			int r=ps.executeUpdate();
			if(r>0) {
				System.out.println("Student deleted successfully");
			} else {
				System.out.println("No student found with this ID");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
