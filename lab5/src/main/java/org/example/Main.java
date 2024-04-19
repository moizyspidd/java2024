package org.example;
import java.sql.*;

public class Main {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/students_db";
    static final String USER = "root";
    static final String PASS = "1234";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Підключення до бд");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Підключення успішне");

            System.out.println("Шукаємо студентів, які народилися в червні");
            searchStudents(conn, "06"); // Пошук студентів, які народилися в червні
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Пошук завершено");
    }

    static void searchStudents(Connection conn, String month) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM students WHERE MONTH(birth_date) = '" + month + "'";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            int id = rs.getInt("id");
            String lastName = rs.getString("last_name");
            String firstName = rs.getString("first_name");
            String middleName = rs.getString("middle_name");
            Date birthDate = rs.getDate("birth_date");
            String studentId = rs.getString("student_id");

            System.out.println("Номер в бд: " + id + ", Прізвище: " + lastName + ", Ім'я: " + firstName +
                    ", По-батькові: " + middleName + ", Дата народження: " + birthDate + ", Айді: " + studentId);
        }

        rs.close();
        stmt.close();
    }
}
