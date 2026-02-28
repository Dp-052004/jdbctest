package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import DAO.MySQLStudentDAO;
import Exception.InvalidStudentDataException;

public class App 
{
    public static void main(String[] args) 
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/studentdb?user=root&password=Dip_5326");

            Scanner sc = new Scanner(System.in);
            MySQLStudentDAO student = new MySQLStudentDAO();

            int choice;

            do {
                System.out.println("\n===== STUDENT MANAGEMENT MENU =====");
                System.out.println("1. Add Student");
                System.out.println("2. Display All Students");
                System.out.println("3. Update Student Email");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                choice = sc.nextInt();
                sc.nextLine();  // consume leftover newline

                switch (choice) {

                    case 1:
                        System.out.println("Enter Student id:");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.println("Enter Student name:");
                        String name = sc.nextLine();

                        System.out.println("Enter student email:");
                        String email = sc.nextLine();

                        System.out.println("Enter student age:");
                        int age = sc.nextInt();

                        System.out.println("Enter student mobile number:");
                        long mobileNo = sc.nextLong();

                        student.addStudent(connection, id, name, email, age, mobileNo);
                        break;

                    case 2:
                        student.displayAllStudent(connection);
                        break;

                    case 3:
                        System.out.println("Enter Student mobile number to update:");
                        long updateMobileNo = sc.nextLong();
                        sc.nextLine();

                        System.out.println("Enter new email:");
                        String newEmail = sc.nextLine();

                        student.updateStudent(connection, updateMobileNo, newEmail);
                        break;

                    case 4:
                        System.out.println("Enter Student ID to delete:");
                        int deleteId = sc.nextInt();

                        student.deleteStudent(connection, deleteId);
                        break;

                    case 5:
                        System.out.println("Exiting program...");
                        break;

                    default:
                        System.out.println("Invalid choice! Please try again.");
                }

            } while (choice != 5);

            connection.close();
            sc.close();

        } 
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        } 
        catch (InvalidStudentDataException e) {
            e.printStackTrace();
        }
    }
}