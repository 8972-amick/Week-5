package com.user.model.dao;

import com.user.model.User;
import com.utils.DBConnection;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO implements UserInterface {

    @Override
    public boolean registerUser(User user) {
        if (user.getEmail().trim().isEmpty() || user.getPassword().trim().isEmpty()){
            return false;
        }
        String sql = "INSERT INTO user(name,email,password,role) values(?,?,?,?)"; // parameterized query where oinput is coming from the user
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,user.getName());
            ps.setString(2,user.getEmail());
            ps.setString(3,user.getPassword());
            ps.setString(4,user.getRole());

            int row = ps.executeUpdate();
            return row > 0;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User loginUser(String email, String password) {
        String sql = "SELECT * FROM user where email=?";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) { // check if another value exist or not

                    return new User(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("role"));


            }
        }catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }
}
