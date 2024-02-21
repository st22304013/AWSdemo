package manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Users;
import db.DBConnectionManager;

public class UserManager {
    private ArrayList<Users> userList;

    public UserManager() {
        userList = new ArrayList<>();
    }

    // 新しいユーザーをリストに追加するメソッド
    public void addUser(Users user) {
        userList.add(user);
    }

 // ユーザー名でデータベースからユーザーを取得
    public Users getUserByUsername(String username) {
        Users user = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnectionManager.getConnection();
            ps = con.prepareStatement("SELECT * FROM users WHERE username=?");
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next()) {
                // 結果セットからユーザーデータを取得してUsersオブジェクトを作成
                String fetchedUsername = rs.getString("username");
                String fetchedPassword = rs.getString("password");
                String fetchedEmail = rs.getString("email");

                // 取得したデータを使ってUsersオブジェクトを作成
                user = new Users(fetchedUsername, fetchedPassword, fetchedEmail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // リソースをクローズ
            DBConnectionManager.closeConnection(con);
        }

        return user; // データベースから取得したUsersオブジェクトを返す
    }

    // ユーザー名がすでに存在するか確認するメソッド
    public boolean doesUsernameExist(String username) {
        for (Users user : userList) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    // ユーザーリストのゲッター
    public ArrayList<Users> getUserList() {
        return userList;
    }
}
