package com.example.course2;

import com.example.course2.entity.Keep;
import com.example.course2.entity.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Keeps {
    private Connection connection;
    private final String url = "jdbc:postgresql://localhost:5432/couremina";
    private final String username = "postgres";
    private final String password = "12qwaszx";

    public Keeps() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveKeep(Keep keep) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("insert into keeps (title, text) VALUES (?, ?)");
            ps.setString(1, keep.getTitle());
            ps.setString(2, keep.getText());

            ps.executeUpdate();
            System.out.println("DB " + keep.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delKeep(Keep keep) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("DELETE FROM keeps WHERE title = (?);");
            ps.setString(1, keep.getTitle());

            ps.executeUpdate();
            System.out.println("DB " + keep.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void rewriteKeep(Keep keepLasted, Keep keepNew) {

        System.out.println("rewriteKeep - " + keepLasted.getTitle() + " " + keepLasted.getText() + " " + keepNew.getTitle() + " " + keepNew.getText());

        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(" UPDATE keeps SET title = (?), text = (?) WHERE title = (?) and text = (?); ");
            ps.setString(1, keepNew.getTitle());
            ps.setString(2, keepNew.getText());
            ps.setString(3, keepLasted.getTitle());
            ps.setString(4, keepLasted.getText());

            ps.executeUpdate();
            System.out.println("DB rewrite - " + keepLasted.getTitle() + " " + keepLasted.getText() + " " + keepNew.getTitle() + " " + keepNew.getText());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Keep> getKeepList() {
        List<Keep> keepList = new ArrayList<>();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT * FROM keeps");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Keep keep = new Keep();

                keep.setTitle(rs.getString("title"));
                keep.setText(rs.getString("text"));


                keepList.add(keep);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        keepList.stream()
                .forEach(keep -> System.out.println
                        ("DB  " + keep.getTitle() + " " + keep.getText()));

        return keepList;

    }
}
