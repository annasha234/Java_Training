package org.example;

import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
//        Connection conn=DatabaseConfig.getConnection();
//        PreparedStatement pstmt=conn
//                .prepareStatement("INSERT INTO TASK (title,description) VALUES (?,?)");
//        pstmt.setString(1,"JDBC Testing");
//        pstmt.setString(2,"This is the jdbc testing description");
//        pstmt.executeUpdate();
//
//        System.out.println("Task Added");
//        pstmt.close();
//        conn.close();


         //For dynamic input by user
//        Connection conn = DatabaseConfig.getConnection();
//        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO task (title,description) VALUES (?,?) ");
//        String choice;
//        Scanner sc = new Scanner(System.in);
//        do {
//            System.out.println("Enter Title: ");
//            String title = sc.nextLine();
//            System.out.println("Enter description: ");
//            String description = sc.nextLine();
//            pstmt.setString(1,title);
//            pstmt.setString(2,description);
//            pstmt.executeUpdate();
//            System.out.println("Task Added");
//            System.out.println("Do you want to enter more data? (yes/no)");
//            choice = sc.nextLine();
//        }while (choice.equalsIgnoreCase("yes"));
//
//        pstmt.close();
//        conn.close();
//        sc.close();

        //Now for all CRUD(Crete,Read,Update,Delete) operations
        Connection conn = DatabaseConfig.getConnection();
        Scanner sc = new Scanner(System.in);

        int choice;

        do{
            System.out.println("\n===== TASK MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. Update Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    PreparedStatement insertStmt = conn.prepareStatement(
                            "INSERT INTO task (title, description) VALUES (?, ?)"
                    );
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter Description: ");
                    String desc = sc.nextLine();

                    insertStmt.setString(1, title);
                    insertStmt.setString(2, desc);
                    insertStmt.executeUpdate();

                    System.out.println("Task Added Successfully!");
                    insertStmt.close();
                    break;

                case 2:
                    PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM task");
                    ResultSet rs = pstmt.executeQuery();
                    System.out.println("\n--- TASK LIST ---");
                    while (rs.next()) {
                        System.out.println(
                                "ID: " + rs.getInt("id") +
                                        ", Title: " + rs.getString("title") +
                                        ", Description: " + rs.getString("description")
                        );
                    }

                    rs.close();
                    pstmt.close();
                    break;
                case 3:
                    System.out.print("Enter Task ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();

                    PreparedStatement updateStmt = conn.prepareStatement(
                            "UPDATE task SET title=?, description=? WHERE id=?"
                    );

                    System.out.print("Enter New Title: ");
                    String newTitle = sc.nextLine();

                    System.out.print("Enter New Description: ");
                    String newDesc = sc.nextLine();

                    updateStmt.setString(1, newTitle);
                    updateStmt.setString(2, newDesc);
                    updateStmt.setInt(3, updateId);

                    int rowsUpdated = updateStmt.executeUpdate();

                    if (rowsUpdated > 0)
                        System.out.println("Task Updated Successfully!");
                    else
                        System.out.println("Task ID Not Found!");
                    updateStmt.close();
                    break;

                case 4:
                    System.out.print("Enter Task ID to delete: ");
                    int deleteId = sc.nextInt();
                    sc.nextLine();

                    PreparedStatement deleteStmt = conn.prepareStatement(
                            "DELETE FROM task WHERE id=?"
                    );

                    deleteStmt.setInt(1, deleteId);

                    int rowsDeleted = deleteStmt.executeUpdate();

                    if (rowsDeleted > 0)
                        System.out.println("Task Deleted Successfully!");
                    else
                        System.out.println("Task ID Not Found!");

                    deleteStmt.close();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 5);

        conn.close();
        sc.close();
    }
}
