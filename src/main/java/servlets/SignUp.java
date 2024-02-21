package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Users;
import db.DBConnectionManager;
import manager.UserManager;

public class SignUp extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");;
        
        UserManager userManager = new UserManager();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DBConnectionManager.getConnection();
            System.out.println("データベースに接続しました");

            // ユーザー名がすでに存在するかチェック
            PreparedStatement checkStatement = con.prepareStatement("SELECT * FROM users WHERE username=?");
            checkStatement.setString(1, username);
            ResultSet resultSet = checkStatement.executeQuery();

            if (!resultSet.next()) {
                // ユーザー名が存在しない場合、SignUpを続行
            	Users newUser = new Users(username, password, email);
                userManager.addUser(newUser);
                
                PreparedStatement ps = con.prepareStatement("INSERT INTO users (username, password, email) VALUES (?, ?, ?)");
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, email);
                ps.executeUpdate();

                // SignUp成功後、ログインページにリダイレクト
                response.sendRedirect("loginjsp");
            } else {
                // ユーザー名が既に存在する場合、適切に処理する（ここではサインアップ失敗ページにリダイレクト）
                response.sendRedirect("signup_failure.jsp");
            }
            
            DBConnectionManager.closeConnection(con); // 処理が終わったら接続を閉じる

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
}
