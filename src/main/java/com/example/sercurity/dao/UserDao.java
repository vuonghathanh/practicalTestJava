package com.example.sercurity.dao;

import com.example.sercurity.modal.User;
import com.example.sercurity.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    Connection connection = ConnectionUtils.getConnection();

    public List<User> getUser(String pName, String password) {
        List<User> list = new ArrayList<>();

        String sql = "select * from users where username = '"+pName +"' and password = " +password;
//        String sql = "select * from users where username = ? and password = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pName);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String name = rs.getString("username");
                String pass = rs.getString("password");
                User user = new User(name, pass);
                list.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
