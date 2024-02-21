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

public class Login extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        UserManager userManager = new UserManager();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DBConnectionManager.getConnection();
            System.out.println("接続できてます");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                String user = rs.getString("username");
                System.out.println(user);
                Users loggedInUser = userManager.getUserByUsername(user);
                System.out.println(loggedInUser);
                request.setAttribute("loggedInUser", loggedInUser); // リクエストスコープにセット
             // リクエストをフォワードして welcome.jsp を表示
                request.getRequestDispatcher("jsp/welcome.jsp").forward(request, response);
            } else {
                response.sendRedirect("jsp/login.jsp");
            }
            
            DBConnectionManager.closeConnection(con);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
