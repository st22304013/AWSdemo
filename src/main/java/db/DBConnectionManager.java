package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {
    private static final String url = "jdbc:mysql://database-1.ci5ri829smix.us-east-1.rds.amazonaws.com:3306/awsdemo"; // データベースのURL
    private static final String dbUser = "carin"; // データベースのユーザー名
    private static final String dbPassword = "startover"; // データベースのパスワード
	
//	private static final String url = "jdbc:mysql://localhost:3306/mybbbs"; // データベースのURL
//    private static final String dbUser = "root"; // データベースのユーザー名
//    private static final String dbPassword = "Quiz00"; // データベースのパスワード

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, dbUser, dbPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
