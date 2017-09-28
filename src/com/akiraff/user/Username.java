package com.akiraff.user;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Date;

public class Username {
    private String username;
    private String password;
    private String role;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Login
    public boolean loginUsername(String username, String password) {
        String sql = "SELECT username, password, role FROM UserTable WHERE username=? AND password=?";

        try(Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, hashPassword(password, "testhelloworld"));
            ResultSet rs = pstmt.executeQuery();

            // If there is 1 row, than password matches
            while(rs.next()) {
                if (rs.getRow() == 1) {
                    this.username = username;
                    this.role = rs.getString("role");
                    return true;
                }

                else {
                    System.out.println("Your username and/or password is invalid.");
                    return false;
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }

    // Register account
    public void registerUsername(String username, String password) {
        this.username = username;
        this.password = password;
        Date nowDate = new Date();

        String sql = "INSERT INTO UserTable (username, password, role, registerDate) VALUES (?, ?, ?, ?)";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, this.username);
            pstmt.setString(2, hashPassword(this.password, "testhelloworld"));
            pstmt.setString(3, "DEFAULT");
            pstmt.setString(4, nowDate.toString());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Connection daatabase
    private Connection connect() {
        String url = "jdbc:sqlite:C:\\Users\\User\\IdeaProjects\\project\\test.db";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);
        }

        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // Hashing password
    private String hashPassword(String password, String salt) {
        String genPassword = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes("UTF-8"));
            byte[] bytes = md.digest(password.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < bytes.length; i++ ) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            genPassword = sb.toString();

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return genPassword;
    }
}